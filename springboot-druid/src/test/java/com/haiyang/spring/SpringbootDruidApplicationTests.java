package com.haiyang.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.alibaba.druid.sql.ast.SQLPartitionValue.Operator.List;

@SpringBootTest
class SpringbootDruidApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testDruid(){
        Map users = jdbcTemplate.queryForMap("select * from sys_role");
        users.forEach((k,v) -> System.out.println(k+":"+v));
    }

}
