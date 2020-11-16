package com.haiyang.dynamic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyang.dynamic.entity.DriverInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * <p>
 * 司机车队对应关系 Mapper 接口
 * </p>
 *
 * @author haiyang
 * @since 2020-11-09
 */
@Mapper
public interface DriverInfoMapper extends BaseMapper<DriverInfo> {

    DriverInfo selectOneByDriverId(@Param(value = "driverId") String driverId,@Param(value = "tableName") String tableName);

    @Select("select * from driver_info ${ew.customSqlSegment}")
    List<DriverInfo> getAllByCity(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select * from driver_info ${ew.customSqlSegment}")
    Page<DriverInfo> getPageByCity(Page<DriverInfo> page,@Param(Constants.WRAPPER) Wrapper wrapper);
}
