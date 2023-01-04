package com.wyl.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.wyl.constants.HttpCode;
import com.wyl.entity.Result;
import com.wyl.utils.SecurityUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = 1L;

    // 当认证失败的时候会走到这个方法里面
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        String msg = StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());  // 错误信息
        SecurityUtils.renderJson(response,  Result.error(HttpCode.UNAUTHORIZED, msg));
    }

}
