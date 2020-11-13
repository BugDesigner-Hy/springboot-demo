package com.haiyang.shrio.filter;/**
 * @Author: HaiYang
 * @Date: 2020/11/13 11:41
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Administrator
 * @Date: 2020/11/13 11:41
 * @Description:
 */

@ControllerAdvice
@Slf4j
public class ShrioExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public String ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return "没有通过权限验证！";
    }
}
