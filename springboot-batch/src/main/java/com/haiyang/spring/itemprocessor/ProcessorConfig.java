package com.haiyang.spring.itemprocessor;/**
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
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author: Administrator
 * @Date: 2020/5/8 10:10
 * @Description:
 */
@Slf4j
@Configuration
public class ProcessorConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Resource
    private ItemProcessor1 itemProcessor1;

    @Resource
    private ItemProcessor2 itemProcessor2;

    @Bean
    public Job itemProcessorJob() {
        return jobBuilderFactory.get("itemProcessorJob")
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
                .processor(compositeItemProcessor())
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<User> reader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("user.csv"));
        reader.setLinesToSkip(1);
        reader.setSkippedLinesCallback(myLineHandler());

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id","name","email","age"});

        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new FieldSetMapper<User>() {
            @Override
            public User mapFieldSet(FieldSet fieldSet) throws BindException {
                return User.builder()
                        .id(fieldSet.readInt("id"))
                        .name(fieldSet.readString("name"))
                        .email(fieldSet.readString("email"))
                        .age(fieldSet.readString("age"))
                        .build();
            }
        });
        lineMapper.afterPropertiesSet();
        reader.setLineMapper(lineMapper);

        return reader;
    }

    /**
     * 通过Builder构造
     * @return
     */
    @Bean
    public FlatFileItemReader<User> reader2() {
        return new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")
                .resource(new ClassPathResource("user.csv"))
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
    public CompositeItemProcessor compositeItemProcessor(){
        CompositeItemProcessor<User, User> processor = new CompositeItemProcessor<>();
        processor.setDelegates(Arrays.asList(itemProcessor1,itemProcessor2));
        return processor;
    }

    @Bean
    public LineCallbackHandler myLineHandler(){
        return s -> log.info("skip line:{}",s);
    }

}
