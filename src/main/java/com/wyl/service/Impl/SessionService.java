package com.wyl.service.Impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.wyl.constants.RedisConstants;
import com.wyl.controller.request.LoginRequest;
import com.wyl.entity.Login;
import com.wyl.entity.LoginUser;
import com.wyl.entity.dto.LoginDTO;
import com.wyl.handler.ServiceException;
import com.wyl.service.LoginService;
import com.wyl.utils.RedisUtils;
import com.wyl.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SessionService {

    @Lazy  // 解决循环依赖问题
    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    LoginService loginService;

    public LoginDTO login(LoginRequest request) {
        Authentication authentication = null;
        try {
            // 用户验证
            // 其实就是调用 UserDetailsServiceImpl.loadUserByUsername 方法
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            return createLoginDTO(loginUser);
        } catch (Exception e) {
            String msg = e.getMessage();  // 加入msg已经携带了错误，那么就把错误抛出去
            if (authentication == null && StrUtil.isBlank(msg)) {  // 账号密码验证不通过
                msg = "登录密码错误";
            }
            throw new ServiceException(msg);
        }
    }

    /**
     * 发送短信验证码
     *
     * @param username 手机号
     * @return
     */
    public void sendSms(String username) {
        // 校验账号的合法性
        Login user = loginService.getByPhone(username);
        if (user == null) {
            throw new ServiceException("手机号未注册");
        }
        String code = RandomUtil.randomNumbers(6);// 生成6位的随机码
        log.info("生成的短信验证码是：" + code);
        String key = RedisConstants.REDIS_KEY_LOGIN_SMS_CODE + username;
        RedisUtils.setCacheObject(key, code, 30, TimeUnit.SECONDS);
    }


    /**
     * 短信登录
     *
     * @param request
     * @return
     */
    public LoginDTO loginWithSms(LoginRequest request) {
        try {
            String account = request.getUsername();
            String smsCode = request.getSmsCode();
            String key = RedisConstants.REDIS_KEY_LOGIN_SMS_CODE + account;
            String cacheCode = RedisUtils.getCacheObject(key);
            if (cacheCode == null) {
                throw new ServiceException("验证码过期了");
            }
            if (cacheCode.equalsIgnoreCase(smsCode)) {  // redis验证的登录有效
                Login user = loginService.getByPhone(account);  // 数据库查询
                if (user == null) {
                    throw new ServiceException("账号不存在");
                }
                LoginUser loginUser = new LoginUser(user);
                LoginDTO loginDTO = createLoginDTO(loginUser);
                // 清除redis验证码
                RedisUtils.deleteObject(key); // 验证完成之后清除验证码
                return loginDTO;
            } else {
                throw new ServiceException("验证码错误");
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 封装一个LoginDTO
     *
     * @param loginUser
     * @return
     */
    private LoginDTO createLoginDTO(LoginUser loginUser) {
        long start = System.currentTimeMillis();
        String token = TokenUtils.createToken(loginUser);
        log.info("createToken 耗时统计 {}ms", System.currentTimeMillis() - start);
        return new LoginDTO(token, loginUser.getLogin());
    }
}
