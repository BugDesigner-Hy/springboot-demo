package com.haiyang.spring.input.restart;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 15:19
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/8 15:19
 * @Description:
 */
@Slf4j
@Component
public class ItemStreamReader<User> implements ItemStream, ItemReader {

    private FlatFileItemReader<User> reader;

    private int curLine;

    private boolean restart;

    private ExecutionContext executionContext;

    public ItemStreamReader() {
        reader = new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")
                .resource(new ClassPathResource("file-errorline.csv"))
                .linesToSkip(1)
                .delimited()
                .names(new String[]{"id", "name", "email", "age"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                    setTargetType((Class<? extends User>) com.haiyang.spring.entity.User.class);
                }})
                .build();
    }

    @Override
    public com.haiyang.spring.entity.User read() throws Exception {
        this.curLine++;
        if (restart) {
            reader.setLinesToSkip(this.curLine - 1);
            restart = false;
            log.info("start read from line:", this.curLine);
        }
        reader.open(this.executionContext);

        com.haiyang.spring.entity.User user = null;
        try {
            user = (com.haiyang.spring.entity.User) this.reader.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        this.executionContext = executionContext;
        if (executionContext.containsKey("curLine")) {
            this.curLine = executionContext.getInt("curLine");
            this.restart = true;
        } else {
            this.curLine = 0;
            executionContext.put("curLine", this.curLine);
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        log.info("update curLine:{}", this.curLine);
        executionContext.put("curLine", this.curLine);
    }

    @Override
    public void close() throws ItemStreamException {

    }
}
