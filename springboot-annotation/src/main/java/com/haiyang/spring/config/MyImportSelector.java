package com.haiyang.spring.config;/**
 * @Author: HaiYang
 * @Date: 2020/6/18 11:20
 */

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

/**
 * @author: Administrator
 * @Date: 2020/6/18 11:20
 * @Description:
 */
public class MyImportSelector implements ImportSelector {

    /**
     * 返回值就是导入到容器中的组件全类名
     * annotationMetadata当前标注@Import注解的类的所有注解信息
     * @param annotationMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("annotationMetadata = " + annotationMetadata);
        return new String[]{"com.haiyang.spring.entity.Book"};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }
}
