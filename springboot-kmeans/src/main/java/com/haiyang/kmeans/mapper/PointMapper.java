package com.haiyang.kmeans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyang.kmeans.entity.Point;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author haiyang
 * @since 2020-04-10
 */
@Mapper
public interface PointMapper extends BaseMapper<Point> {

}
