package com.haiyang.spring.input.readmultiplefile;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 10:10
 */

import com.haiyang.spring.entity.User;
import com.haiyang.spring.input.fromdb.MyChunkListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import javax.annotation.Resource;

/**
 * @author: Administrator
 * @Date: 2020/5/8 10:10
 * @Description:
 */
@Slf4j
@Configuration
public class ReadMultipleFileConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Value("classpath*:/file*.csv")
    private org.springframework.core.io.Resource[] resources;

    @Bean
    public Job readMultipleFileJob() {
        return jobBuilderFactory.get("readMultipleFileJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .allowStartIfComplete(true)
                .<User, User>chunk(10)
                .listener(new MyChunkListener())
                .reader(multipleReader())
                .writer(writer())
                .build();
    }

    @Bean
    public MultiResourceItemReader<User> multipleReader() {
        MultiResourceItemReader<User> multipleReader = new MultiResourceItemReader<>();
        multipleReader.setDelegate(reader());
        multipleReader.setResources(resources);

        return multipleReader;
    }

    /**
     * 通过Builder构造
     * @return
     */
    @Bean
    public FlatFileItemReader<User> reader() {
        return new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")
                .linesToSkip(1)
                .skippedLinesCallback(myLineHandler())
                .delimited()
                .names(new String[]{"id", "name", "email", "age"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                    setTargetType(User.class);
                }})
                .build();
    }

    @Bean
    public ItemWriter<User> writer() {
        return list -> list.forEach(user -> log.info("Writing item:{}", user));
    }

    @Bean
    public LineCallbackHandler myLineHandler(){
        return s -> log.info("skip line:{}",s);
    }

}
