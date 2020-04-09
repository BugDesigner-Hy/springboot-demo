package com.haiyang.oauth.config;/**
 * @Author: HaiYang
 * @Date: 2020/4/9 9:57
 */

import com.haiyang.oauth.entity.GitHubOAuth2User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

/**
 * @author: Administrator
 * @Date: 2020/4/9 09:57
 * @Description: override the auto-configuration
 */
@Configuration
@EnableWebSecurity
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                以下Java配置会将所有HTTP请求重定向到HTTPS
//                .requiresChannel(channel -> channel
//                        .anyRequest().requiresSecure()
//                )
                .authorizeRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .oauth2Login(login -> login
                                .userInfoEndpoint(userInfo -> userInfo
                                        .customUserType(GitHubOAuth2User.class, "github"))
                                .defaultSuccessUrl("/login/success")
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                )
                //多端登录下线 只允许一个客户端登录
                .sessionManagement(session -> session.maximumSessions(1));
    }


//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
//    }
//
//    private ClientRegistration googleClientRegistration() {
//        return ClientRegistration.withRegistrationId("google")
//                .clientId("google-client-id")
//                .clientSecret("google-client-secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
//                .scope("openid", "profile", "email", "address", "phone")
//                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//                .clientName("Google")
//                .build();
//    }

}
