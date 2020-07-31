package com.starry.config;

import com.starry.shiro.*;
import com.starry.shiro.jwt.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }

    @Bean("securityManager")
    public SecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(redisCacheManager());
        /**
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        // 添加自己的过滤器
        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt", new JwtFilter());
        shiroFilter.setFilters(filters);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/captcha/**", "anon");
        filterMap.put("/**", "jwt");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }
}
