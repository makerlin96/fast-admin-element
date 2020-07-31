package com.starry.shiro;

import com.starry.entity.UserEntity;
import com.starry.service.RoleService;
import com.starry.service.UserService;
import com.starry.shiro.jwt.JwtToken;
import com.starry.utils.JwtUtils;
import com.starry.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("\n=====================校验用户权限==============");
        UserEntity userEntity = (UserEntity) principals.getPrimaryPrincipal();
        Long userId = userEntity.getUserId();
        // 用户角色集合
        Set<String> userRoles = roleService.getUserRoles(userId);
        // 获取用户权限
        Set<String> userPermissions = userService.getUserPermissions(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(userRoles);
        info.setStringPermissions(userPermissions);
        log.debug("\n=====================校验权限完成===============");
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        // 校验token是否有限
        Claims claims = jwtUtils.getClaimsByToken(token);
        if (claims == null || !jwtUtils.validateToken(token)) {
            throw new AuthenticationException(jwtUtils.getHeader() + "非法无效");
        }
        String userId = claims.getSubject();
        // 校验token是否过期
        if (!jwtTokenRefresh(userId)) {
            throw new AuthenticationException(jwtUtils.getHeader() + "过期，请重新登录!");
        }
        // 查询用户信息
        UserEntity userEntity = userService.getById(userId);
        if (userEntity == null) {
            throw new AuthenticationException("用户不存在!");
        }
        // 判断用户状态
        if (userEntity.getStatus().equals(1)) {
            throw new AuthenticationException("账号已被锁定,请联系管理员!");
        }
        return new SimpleAuthenticationInfo(userEntity, token, getName());
    }

    /**
     * JwtToken刷新生命周期 （解决用户一直在线操作，突然token失效问题）
     * 1、登录成功后将用户的JWT生成的token存储到redis中
     * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
     * 3、当用户请求时，token值还在生命周期内，过期时间重新计算
     * 4、当用户请求时，token值已经超时，但该token对应redis中的key还存在，则表示该用户一直在操作只是token失效了，则会重新生成token并覆盖redis中的值，过期时间重新计算
     * 5、当用户请求时，token值已经超时，并在redis中不存在对应的key，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     * 7、注：当前端接收到Response的Header中的token值会存储起来，作为以后请求token使用
     *
     * @param userId
     * @return
     */
    public boolean jwtTokenRefresh(String userId) {
        // 从redis中获取token
        String redisToken = (String) redisUtils.get("admin:loginUser:" + userId);
        if (StringUtils.isNotBlank(redisToken)) {
            // 校验token是否过期
            if (jwtUtils.tokenIsExpired(redisToken)) {
                // 生成新的token
                String newToken = jwtUtils.createToken(userId);
                redisUtils.set("admin:loginUser:" + userId, newToken, jwtUtils.getExpire());
            } else {
                // 更新token的过期时间
                redisUtils.expire("admin:loginUser:" + userId, jwtUtils.getExpire());
            }
            return true;
        }
        return false;
    }
}
