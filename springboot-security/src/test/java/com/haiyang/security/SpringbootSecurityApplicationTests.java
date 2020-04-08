package com.haiyang.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootSecurityApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    /**
     * 测试登录成功
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        mvc
                .perform(formLogin("/login").user("root").password("123"))
                .andExpect(authenticated());
    }

    /**
     * 测试登录失败
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        mvc
                .perform(formLogin("/login").user("root").password("1234"))
                .andExpect(unauthenticated());
    }

    /**
     * 测试退出登录
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        mvc
                .perform(logout("/logout"))
                .andExpect(unauthenticated());
    }

    /**
     * 测试无权限访问资源情况
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        MvcResult result = mvc
                .perform(get("/private").with(user("root").password("123").roles("ADMIN")))
                .andExpect(status().is(403))
                .andReturn();
        System.out.println("result = " + result.getResponse().getContentAsString());
    }

    /**
     * 测试有权限访问资源情况
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        MvcResult result = mvc
                .perform(get("/private").with(user("root").password("123").roles("USER")))
                .andExpect(status().is(200))
                .andReturn();
        System.out.println("result = " + result.getResponse().getContentAsString());
    }

}
