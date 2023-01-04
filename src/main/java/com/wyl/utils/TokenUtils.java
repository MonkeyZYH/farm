package com.wyl.utils;

import cn.hutool.core.util.IdUtil;
import com.wyl.constants.RedisConstants;
import com.wyl.entity.LoginUser;
import com.wyl.handler.SystemException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TokenUtils {
    private static final String SECRET = "qwertyuioplkjhgfdsazxcvbnm";
    // redis key过期时间
    private static final Integer EXPIRE_TIME_IN_MS = 120 * 60 * 1000;
    private static final Integer DURATION_IN_MS = 20 * 60 * 1000;
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";


    /**
     * 创建令牌
     *
     * @param loginUser 登录用户信息
     * @return 令牌
     */
    public static String createToken(LoginUser loginUser) {
        String tokenUUID = IdUtil.fastSimpleUUID();
        loginUser.setTokenUUID(tokenUUID);
        refreshToken(loginUser);  // 缓存用户信息到redis，key是tokenUUID
        return createToken(tokenUUID);  // 创建token令牌
    }

    /**
     * 根据HttpServletRequest 获取用户信息
     *
     * @param request http请求对象
     * @return 用户信息
     */
    public static LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request); // 拿到 token字符串
        return getLoginUser(token);
    }

    /**
     * 根据token令牌 获取用户信息
     *
     * @param token 令牌
     * @return 用户信息
     */
    public static LoginUser getLoginUser(String token) {
        try {
            String tokenUUID = getUUIDFromToken(token);
            String tokenKey = getTokenKey(tokenUUID);
            return RedisUtils.getCacheObject(tokenKey);
        } catch (Exception e) {
            log.warn("parseToken error!", e);
            throw new SystemException("get cache error");
        }
    }

    // 解析token 携带的信息
    public static String getUUIDFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (Exception e) {
            log.warn("getUUIDFromToken error", e);
            throw new SystemException("parserToken error");
        }
    }

    /**
     * 验证令牌有效期，相差不足 { DURATION_IN_MS } 分钟，自动刷新缓存
     *
     * @param loginUser 用户对象
     */
    public static void verifyAndRefreshToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= DURATION_IN_MS) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     * 本质上就是刷新redis缓存的有效时间
     *
     * @param loginUser 登录信息
     */
    public static void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + EXPIRE_TIME_IN_MS);
        // 获取redis里的userKey
        String userKey = getTokenKey(loginUser.getTokenUUID());
        try {
            RedisUtils.setCacheObject(userKey, loginUser, EXPIRE_TIME_IN_MS, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.warn("refreshToken error", e);
            throw new SystemException("refreshToken error");
        }
    }

    public static void logout(String tokenUUID) {
        RedisUtils.deleteObject(getTokenKey(tokenUUID));
    }

    private static String createToken(String uuid) {
        Claims claims = new DefaultClaims();
        claims.setSubject(uuid);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    private static String getToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (token != null && token.trim().length() > 0 && token.startsWith(BEARER)) {
            token = token.replace(BEARER, "");
        }
        return token;
    }

    private static String getTokenKey(String uuid) {
        return RedisConstants.REDIS_KEY_LOGIN_TOKEN + uuid;
    }
}
