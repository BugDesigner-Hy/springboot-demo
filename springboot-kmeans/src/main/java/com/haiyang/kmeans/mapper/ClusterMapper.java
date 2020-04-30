package com.haiyang.kmeans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyang.kmeans.entity.Cluster;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author haiyang
 * @since 2020-04-10
 */
@Repository
public interface ClusterMapper extends BaseMapper<Cluster> {

    List<Cluster> getDistinctData();

}
