package com.haiyang.spring.input.fromdb;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 10:10
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.haiyang.spring.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/5/8 10:10
 * @Description:
 */
@Slf4j
@Configuration
public class JdbcConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job FromJdbcJob() {
        return jobBuilderFactory.get("readFromJdbcJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .allowStartIfComplete(true)
                .<User, User>chunk(4)
                .listener(new MyChunkListener())
                .reader(reader2())
                .writer(writer())
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<User> reader() {
        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(druidDataSource());
        reader.setFetchSize(4);
        reader.setRowMapper(new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getString("age"))
                        .build();
            }
        });

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id,name,email,age");
        queryProvider.setFromClause("from user");
        HashMap<String, Order> sortKey = new HashMap<>(1);
        sortKey.put("id", Order.ASCENDING);
        queryProvider.setSortKeys(sortKey);

        reader.setQueryProvider(queryProvider);
        return reader;
    }

    /**
     * 通过Builder构造
     * @return
     */
    @Bean
    @StepScope
    public JdbcPagingItemReader reader2() {
        return new JdbcPagingItemReaderBuilder()
                .saveState(true)
                .name("reader2")
                .dataSource(druidDataSource())
                .selectClause("id,name,email,age")
                .fromClause("from user")
                .sortKeys(new HashMap<String, Order>(1) {{
                    put("id", Order.ASCENDING);
                }})
                .fetchSize(4)
                .rowMapper((resultSet, i) -> User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getString("age"))
                        .build())
                .build();
    }

    @Bean
    public ItemWriter<User> writer() {
        return list -> list.forEach(user -> log.info("Writing item:{}", user));
    }

}
