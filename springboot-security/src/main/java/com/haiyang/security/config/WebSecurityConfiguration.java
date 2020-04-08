package com.haiyang.security.config;/**
 * @Author: HaiYang
 * @Date: 2020/4/8 9:43
 */

import com.haiyang.security.config.filter.CustomFilter;
import com.haiyang.security.config.handler.CustomAccessDeniedHandler;
import com.haiyang.security.config.handler.CustomLoginFailHandler;
import com.haiyang.security.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: Administrator
 * @Date: 2020/4/8 09:43
 * @Description:
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(new CustomFilter(), UsernamePasswordAuthenticationFilter.class)//自定义拦截器
                .exceptionHandling(handler -> handler
                        .accessDeniedHandler(new CustomAccessDeniedHandler())//自定义访问拒绝处理器
                )
                .authorizeRequests(req -> req
                        .antMatchers("/private").hasAnyRole("USER")
                        .anyRequest().authenticated()

                )
                .formLogin(form -> form
                        .loginProcessingUrl("/login")//登录请求地址
                        .successForwardUrl("/login/success")//这里的url必须是post请求
                        .failureForwardUrl("/login/fail")//这里的url必须是post请求
                        .failureHandler(new CustomLoginFailHandler())//如果添加handler 前面的failureForwardUrl失效
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("cookieNamesToClear")
                        .logoutSuccessUrl("/logout-success")
                )
                .cors()
                .and().csrf();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    //    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        User.UserBuilder builder = User.withDefaultPasswordEncoder();
//        UserDetails user = builder
//                .username("user")
//                .password("123")
//                .roles("USER")
//                .build();
//        UserDetails admin = builder
//                .username("admin")
//                .password("123")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

}
