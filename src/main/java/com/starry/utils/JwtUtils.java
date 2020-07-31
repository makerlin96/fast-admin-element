package com.starry.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtUtils {
    private String secret;
    private long expire;
    private String header;

    /**
     * 生成token
     * @param userId 用户ID
     * @return 返回token
     */
    public String createToken(String userId) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 根据token获取Claims
     * @param token 用户token
     * @return 返回Claims
     */
    public Claims getClaimsByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取token过期时间
     * @param token 用户token
     * @return 返回过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsByToken(token).getExpiration();
    }

    /**
     * 判断token是否过期
     * @param token 用户token
     * @return 已过期返回true，未过期返回false
     */
    public Boolean tokenIsExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 判断token是否非法
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public Boolean validateToken(String token) {
        return !tokenIsExpired(token);
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
