package com.haiyang.spring.output.tofile;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 10:10
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.google.gson.Gson;
import com.haiyang.spring.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author: Administrator
 * @Date: 2020/5/8 10:10
 * @Description:
 */
@Slf4j
@Configuration
public class ToFileConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Resource
    private DruidDataSource druidDataSource;

    @Bean
    public Job outputOverviewJob() throws IOException {
        return jobBuilderFactory.get("outputOverviewJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws IOException {
        return stepBuilderFactory.get("step1")
                .allowStartIfComplete(true)
                .<User, User>chunk(10)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader reader() {
        return new JdbcPagingItemReaderBuilder()
                .saveState(true)
                .name("reader")
                .dataSource(druidDataSource)
                .selectClause("id,name,email,age")
                .fromClause("from user")
                .sortKeys(new HashMap<String, Order>(1) {{
                    put("id", Order.ASCENDING);
                }})
                .fetchSize(10)
                .rowMapper((resultSet, i) -> User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getString("age"))
                        .build())
                .build();
    }

    @Bean
    public ItemWriter<User> writer() throws IOException {
        String path = File.createTempFile("userInfo",".data").getAbsolutePath();
        log.info(">> file is created in {}",path);
        return new FlatFileItemWriterBuilder()
                .name("fileWriter")
                .resource(new FileSystemResource(path))
                .lineAggregator(new LineAggregator<User>() {
                    @Override
                    public String aggregate(User user) {
                        return new Gson().toJson(user);
                    }
                })
                .build();
    }

}
