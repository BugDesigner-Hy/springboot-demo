package com.haiyang.kmeans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyang.kmeans.entity.SimpleDriver;
import com.haiyang.kmeans.entity.YyOrder;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author haiyang
 * @since 2020-04-17
 */
public interface SimpleDriverMapper extends BaseMapper<SimpleDriver> {

    List<YyOrder> getchengdu();
}
