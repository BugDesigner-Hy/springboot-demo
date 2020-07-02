package com.haiyang.spring;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@SpringBootTest
class SpringbootElasticsearchApplicationTests {

    @Resource
    RestHighLevelClient restHighLevelClient;

    @Test
    public void createIndex() throws IOException {

        CreateIndexRequest indexRequest = new CreateIndexRequest("person");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
        log.info("{}",createIndexResponse);

    }

}
