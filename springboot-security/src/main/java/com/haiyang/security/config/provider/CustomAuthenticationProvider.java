package com.haiyang.security.config.provider;/**
 * @Author: HaiYang
 * @Date: 2019/10/30 16:14
 */


import com.haiyang.security.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author: Administrator
 * @Date: 2019/10/30 16:14
 * @Description:
 */
@Slf4j
//@Component("authenticationProvider")
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Resource
    @Qualifier("customUserDetailService")
    UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        log.info("登录用户:{} , 登录密码:{},加密：{}", username, password,passwordEncoder.encode(password));
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (!user.getUsername().equals(username)) {
            throw new UsernameNotFoundException("用户名错误");
        }
        log.info("数据库中的用户名:{}，密码:{}", user.getUsername(), user.getPassword());

        if (!passwordEncoder.matches(password,user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        log.info("用户权限：{}", authorities.toString());
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
