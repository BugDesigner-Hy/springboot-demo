package com.haiyang.kmeans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyang.kmeans.entity.Point;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author haiyang
 * @since 2020-04-10
 */
@Repository
public interface PointMapper extends BaseMapper<Point> {

}
