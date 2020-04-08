package com.haiyang.security.config.handler;/**
 * @Author: HaiYang
 * @Date: 2019/10/31 14:13
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Administrator
 * @Date: 2019/10/31 14:13
 * @Description:
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {

        //以Json格式返回
        Map<String, String> map = new HashMap<>();
        map.put("code", "403");
        map.put("msg", "您没有权限访问" + httpServletRequest.getRequestURL());
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        Gson gson = new Gson();
        httpServletResponse.getWriter().write(gson.toJson(map));
        httpServletResponse.getWriter().flush();
    }
}
