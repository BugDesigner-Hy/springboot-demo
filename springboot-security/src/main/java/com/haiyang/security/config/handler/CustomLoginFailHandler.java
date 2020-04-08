package com.haiyang.security.config.handler;/**
 * @Author: HaiYang
 * @Date: 2020/4/8 14:49
 */

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Administrator
 * @Date: 2020/4/8 14:49
 * @Description:
 */
@Slf4j
@Component
public class CustomLoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        //以Json格式返回
        Map<String, String> map = new HashMap<>();
        map.put("code", "400");
        map.put("msg", e.getMessage());
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        Gson gson = new Gson();
        httpServletResponse.getWriter().write(gson.toJson(map));
        httpServletResponse.getWriter().flush();

    }
}
