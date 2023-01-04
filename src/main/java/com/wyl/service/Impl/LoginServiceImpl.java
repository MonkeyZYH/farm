package com.wyl.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyl.entity.Login;
import com.wyl.handler.ServiceException;
import com.wyl.mapper.LoginMapper;
import com.wyl.service.LoginService;
import com.wyl.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author zyh
 * @Date 2022/12/13 17:59
 */
@Service
@Transactional(rollbackFor = Exception.class )
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Login findLoginFunction(Long id) {

        return loginMapper.findLoginFunction(id);
    }

    @Override
    public Boolean updateLogin(Login login) {
        LambdaQueryWrapper<Login> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Login::getId, login.getId());
        LambdaQueryWrapper<Login> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Login::getUsername, login.getUsername());
        LambdaQueryWrapper<Login> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Login::getPhone, login.getPhone());
        LambdaQueryWrapper<Login> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(Login::getEmail, login.getEmail());
        Login selectOne = loginMapper.selectOne(queryWrapper);
        Login selectOne1 = loginMapper.selectOne(queryWrapper1);
        Login selectOne2 = loginMapper.selectOne(queryWrapper2);
        Login selectOne3 = loginMapper.selectOne(queryWrapper3);
        if (!ObjectUtils.isEmpty(selectOne1)) {
            return false;
        } else if (!ObjectUtils.isEmpty(selectOne2)) {
            return false;
        } else if (!ObjectUtils.isEmpty(selectOne3)) {
            return false;
        }
        Login login1 = new Login();
        login1.setId(selectOne.getId());
        login1.setUsername(login.getUsername());
        login1.setPassword(login.getPassword());
        login1.setPhone(login.getPhone());
        login1.setNickname(login.getNickname());
        login1.setSex(login.getSex());
        login1.setEmail(login.getEmail());
        login1.setAddress(login.getAddress());
        login1.setAvatar(login.getAvatar());
//        login1.setCreateTime(LocalDateTime.now());
        login1.setPermission(login.getPermission());
        if (login.getPermission() == true) {
            login1.setFunctionId(2L);
        } else {
            login1.setFunctionId(1L);
        }
        loginMapper.updateById(login1);
        return true;
    }

    @Override
    public Boolean addLogin(Login login) {
        LambdaQueryWrapper<Login> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Login::getUsername, login.getUsername());
        LambdaQueryWrapper<Login> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Login::getPhone, login.getPhone());
        LambdaQueryWrapper<Login> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(Login::getEmail, login.getEmail());
        Login selectOne1 = loginMapper.selectOne(queryWrapper1);
        Login selectOne2 = loginMapper.selectOne(queryWrapper2);
        Login selectOne3 = loginMapper.selectOne(queryWrapper3);
        try {
            if (!ObjectUtils.isEmpty(selectOne1)) {
                throw new ServiceException("用户名重复了");
            } else if (!ObjectUtils.isEmpty(selectOne2)) {
                throw new ServiceException("手机号重复了");
            } else if (!ObjectUtils.isEmpty(selectOne3)) {
                throw new ServiceException("邮箱重复了");
            }
            Login login1 = new Login();
            login1.setUsername(login.getUsername());
            login1.setPassword(SecurityUtils.encryptPassword(login.getPassword()));
            login1.setPhone(login.getPhone());
            login1.setNickname(login.getNickname());
            login1.setSex(login.getSex());
            login1.setEmail(login.getEmail());
            login1.setAddress(login.getAddress());
            login1.setAvatar(login.getAvatar());
            login1.setCreateTime(LocalDateTime.now());
            login1.setPermission(login.getPermission());
            if (login.getPermission() == true) {
                login1.setFunctionId(2L);
            } else {
                login1.setFunctionId(1L);
            }
            loginMapper.insert(login1);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Login getByUsername(String username) {
        // 访问数据库的耗时统计
        long start = System.currentTimeMillis();
        QueryWrapper<Login> queryWrapper = new QueryWrapper<Login>().eq("username", username)
                .or().eq("email", username)
                .or().eq("phone", username);
        Login one = getOne(queryWrapper);
        return one;
    }

    @Override
    public Login getByPhone(String usrername) {
        QueryWrapper<Login> queryWrapper = new QueryWrapper<Login>().eq("phone", usrername);
        return getOne(queryWrapper);
    }
}
