package com.haiyang.spring.output.multiple;/**
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
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: Administrator
 * @Date: 2020/5/8 10:10
 * @Description:
 */
@Slf4j
@Configuration
public class MultipleFileConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Resource
    private DruidDataSource druidDataSource;

    @Bean
    public Job multipleJob() throws Exception {
        return jobBuilderFactory.get("multipleJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .allowStartIfComplete(true)
                .<User, User>chunk(10)
                .reader(reader())
//                .writer(compositeItemWriter())
                .writer(classifierCompositeItemWriter())
                .stream(writer1())
                .stream(writer2())
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

    /**
     * 同时写入多个不同的文件
     * @return
     * @throws Exception
     */
    @Bean
    public CompositeItemWriter<User> compositeItemWriter() throws Exception {
        CompositeItemWriter<User> itemWriter = new CompositeItemWriter<>();
        itemWriter.setDelegates(Arrays.asList(writer1(),writer2()));
        itemWriter.afterPropertiesSet();
        return itemWriter;
    }

    /**
     * 按照分类写入不同文件
     * @return
     * @throws IOException
     */
    @Bean
    public ClassifierCompositeItemWriter<User> classifierCompositeItemWriter() throws IOException {
        ClassifierCompositeItemWriter<User> itemWriter = new ClassifierCompositeItemWriter<>();
        itemWriter.setClassifier(new MyClassifier(writer1(),writer2()));
        return itemWriter;
    }

    @Bean
    public FlatFileItemWriter<User> writer1() throws IOException {
        String path = File.createTempFile("userInfo",".tmp").getAbsolutePath();
        log.info(">> file is created in {}",path);
        return new FlatFileItemWriterBuilder()
                .name("fileWriter1")
                .resource(new FileSystemResource(path))
                .lineAggregator(new LineAggregator<User>() {
                    @Override
                    public String aggregate(User user) {
                        return new Gson().toJson(user);
                    }
                })
                .build();
    }

    @Bean
    public FlatFileItemWriter<User> writer2() throws IOException {
        String path = File.createTempFile("userInfo",".data").getAbsolutePath();
        log.info(">> file is created in {}",path);
        return new FlatFileItemWriterBuilder()
                .name("fileWriter2")
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
