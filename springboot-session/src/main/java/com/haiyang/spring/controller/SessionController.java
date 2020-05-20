package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/5/19 15:55
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Administrator
 * @Date: 2020/5/19 15:55
 * @Description:
 */
@Slf4j
@RestController
public class SessionController {

    private HttpSession cacheSession;

    @GetMapping("/saveSession")
    public Map<String,Object> saveSession(HttpSession session){
        String username = (String)session.getAttribute("username");
        session.setAttribute("username","admin");
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("sessionId",session.getId());
        cacheSession = session;
        return map;
    }

    @GetMapping("getSession")
    public String getSession(){

        String username;
        try {
            username = (String)cacheSession.getAttribute("username");
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
        log.info("session:{}",username);
        return username;
    }

    @GetMapping("removeSession")
    public String removeSession(){
        try {
            cacheSession.invalidate();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "remove success";
    }

}
