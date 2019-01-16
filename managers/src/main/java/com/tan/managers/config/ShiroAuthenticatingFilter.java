package com.tan.managers.config;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.tan.managers.model.ResponseObject;
import com.tan.managers.utils.Constant;

public class ShiroAuthenticatingFilter extends AuthenticatingFilter {

    //创建shiro认证的token
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if(org.springframework.util.StringUtils.isEmpty(token)){
            return null;
        }

        return new ShiroToken(token);
    }

    //是否允许访问
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    //拒绝访问的出来
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if(StringUtils.isEmpty(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            ResponseObject<Object> res = new ResponseObject<>();
            res.setCode("401");
            res.setMessage("token outTime");
            String jsonString = JSON.toJSONString(res);
            httpResponse.getWriter().print(jsonString);

            return false;
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            ResponseObject<Object> res = new ResponseObject<>();
            res.setCode("401");
            res.setMessage(throwable.getMessage());
            String jsonString = JSON.toJSONString(res);
            httpResponse.getWriter().print(jsonString);
        } catch (IOException e1) {

        }

        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        //从header中获取token
        String token = httpRequest.getHeader(Constant.X_TOKEN);

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isEmpty(token)){
            token = httpRequest.getParameter(Constant.X_TOKEN);
        }

        return token;
    }

}

