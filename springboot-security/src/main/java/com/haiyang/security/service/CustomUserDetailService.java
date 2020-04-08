package com.haiyang.security.service;/**
 * @Author: HaiYang
 * @Date: 2020/4/8 10:39
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: Administrator
 * @Date: 2020/4/8 10:39
 * @Description:
 */
@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //这里自行实现从数据库加载用户信息
        User.UserBuilder builder = User.builder();
        UserDetails user = builder
                .username("root")
                .password(passwordEncoder.encode("123"))
                .roles("ADMIN")
                .build();
        return user;
    }
}
