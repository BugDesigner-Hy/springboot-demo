package com.haiyang.spring.errorhandler;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 10:10
 */

import com.haiyang.spring.entity.User;
import com.haiyang.spring.input.fromdb.MyChunkListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.jsr.SkipListenerAdapter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/5/8 10:10
 * @Description:
 */
@Slf4j
@Configuration
public class ErrorHandlerConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Resource
    private MyItemProcessor processor;

    @Resource
    private MySkipListener mySkipListener;

    @Bean
    public Job errorHandlerJob() {
        return jobBuilderFactory.get("errorHandlerJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .allowStartIfComplete(true)
                .<String, String>chunk(1)
                .reader(reader())
                .processor(processor)
                .writer(writer())
                .faultTolerant()
                //重试机制
//                .retry(Exception.class)
//                .retryLimit(2)
                //遇到错误跳过 并记录
                .skip(Exception.class)
                .skipLimit(4)
                .listener(mySkipListener)
                .build();
    }

    @Bean
    public ItemReader<String> reader() {
        return new ListItemReader<>(Arrays.asList("one", "two", "three","four","five"));
    }

    @Bean
    public ItemWriter<String> writer() {
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) {
                log.info("Writing item:{}", list.toString());
            }
        };
    }


}
