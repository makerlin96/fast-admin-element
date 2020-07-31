package com.starry.shiro.jwt;

import cn.hutool.http.HttpStatus;
import com.google.gson.Gson;
import com.starry.utils.HttpContextUtils;
import com.starry.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return new JwtToken(token);
    }

    private String getRequestToken(HttpServletRequest servletRequest) {
        //从header中获取token
        String token = servletRequest.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = servletRequest.getParameter("token");
        }
        return token;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            String json = new Gson().toJson(R.error(401,  "token不能为空"));
            httpResponse.getWriter().print(json);
            return false;
        }
        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        try {
            // 处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            R r = R.error(HttpStatus.HTTP_UNAUTHORIZED, throwable.getMessage());
            String json = new Gson().toJson(r);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    /**
     * 未通过验证时调用
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            String json = new Gson().toJson(R.error(HttpStatus.HTTP_UNAUTHORIZED, "token不能为空"));
            httpResponse.getWriter().print(json);
            return false;
        }
        return executeLogin(request, response);
    }
}
