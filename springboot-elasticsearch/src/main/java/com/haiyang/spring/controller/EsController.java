package com.haiyang.controller;/**
 * @Author: HaiYang
 * @Date: 2020/5/28 9:59
 */

import com.haiyang.entity.Person;
import com.haiyang.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * @author: Administrator
 * @Date: 2020/5/28 09:59
 * @Description:
 */
@CrossOrigin("*")
@Slf4j
@RestController
public class EsController {

    @Resource
    private PersonRepository repo;

//    @Resource
//    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    RestHighLevelClient restHighLevelClient;

    @GetMapping("/createIndex")
    public void createIndex() throws IOException {

        CreateIndexRequest indexRequest = new CreateIndexRequest("person");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
        log.info("success:{}",createIndexResponse);

    }

    /**
     * 测试新增
     */

    @GetMapping("/save")
    public void save() {


        Person person = new Person(1L, "刘备", "蜀国", 18, new Date(), "刘备（161年－223年6月10日），即汉昭烈帝（221年－223年在位），又称先主，字玄德，东汉末年幽州涿郡涿县（今河北省涿州市）人，西汉中山靖王刘胜之后，三国时期蜀汉开国皇帝、政治家。\n刘备少年时拜卢植为师；早年颠沛流离，备尝艰辛，投靠过多个诸侯，曾参与镇压黄巾起义。先后率军救援北海相孔融、徐州牧陶谦等。陶谦病亡后，将徐州让与刘备。赤壁之战时，刘备与孙权联盟击败曹操，趁势夺取荆州。而后进取益州。于章武元年（221年）在成都称帝，国号汉，史称蜀或蜀汉。《三国志》评刘备的机权干略不及曹操，但其弘毅宽厚，知人待士，百折不挠，终成帝业。刘备也称自己做事“每与操反，事乃成尔”。\n章武三年（223年），刘备病逝于白帝城，终年六十三岁，谥号昭烈皇帝，庙号烈祖，葬惠陵。后世有众多文艺作品以其为主角，在成都武侯祠有昭烈庙为纪念。");
//        Person save = elasticsearchRestTemplate.save(person);
        Person save = repo.save(person);
        log.info("result:{}",save);
    }
}
