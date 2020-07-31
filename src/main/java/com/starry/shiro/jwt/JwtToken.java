package com.starry.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = -2423120088285177053L;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
