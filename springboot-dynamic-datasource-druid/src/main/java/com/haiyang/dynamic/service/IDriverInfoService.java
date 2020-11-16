package com.haiyang.dynamic.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haiyang.dynamic.entity.DriverInfo;

import java.util.List;

/**
 * <p>
 * 司机车队对应关系 服务类
 * </p>
 *
 * @author haiyang
 * @since 2020-11-09
 */
public interface IDriverInfoService extends IService<DriverInfo> {

    DriverInfo getOneByDriverId(String driverId);

    List<DriverInfo> getAllByCity(Wrapper wrapper);

    Page<DriverInfo> getPageByCity(Page page,Wrapper wrapper);
}
