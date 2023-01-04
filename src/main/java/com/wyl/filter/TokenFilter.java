package com.wyl.filter;

import com.wyl.entity.LoginUser;
import com.wyl.utils.SecurityUtils;
import com.wyl.utils.TokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * token过滤器 验证token有效性
 * 这个过滤器通常被用于继承实现并在每次请求时只执行一次过滤
 * 此token不注入Spring，防止每次请求都走这个Filter
 */
//@Component
public class TokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        Authentication authentication = SecurityUtils.getAuthentication();  // 从后端的上下文去获取用户权限验证信息
        LoginUser loginUser = TokenUtils.getLoginUser(request);
        if (Objects.isNull(authentication) && !Objects.isNull(loginUser)) {
            TokenUtils.verifyAndRefreshToken(loginUser);  // 刷新token的机制，过期时间验证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
