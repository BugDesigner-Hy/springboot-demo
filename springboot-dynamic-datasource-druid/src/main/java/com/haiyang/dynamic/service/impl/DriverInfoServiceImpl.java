package com.haiyang.dynamic.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiyang.dynamic.entity.DriverInfo;
import com.haiyang.dynamic.mapper.DriverInfoMapper;
import com.haiyang.dynamic.service.IDriverInfoService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 司机车队对应关系 服务实现类
 * </p>
 *
 * @author haiyang
 * @since 2020-11-09
 */
@Service
public class DriverInfoServiceImpl extends ServiceImpl<DriverInfoMapper, DriverInfo> implements IDriverInfoService {

    @Resource
    DriverInfoMapper driverInfoMapper;

    @Override
    public <E extends IPage<DriverInfo>> E page(E page, Wrapper<DriverInfo> queryWrapper) {
        return driverInfoMapper.selectPage(page,queryWrapper);
    }

    @Override
    public DriverInfo getOneByDriverId(String driverId) {
        String tableName = "driver_info";
        return driverInfoMapper.selectOneByDriverId(driverId,tableName);
    }

    @Override
    public List<DriverInfo> getAllByCity(Wrapper wrapper) {
        return driverInfoMapper.getAllByCity(wrapper);
    }

    @Override
    public Page<DriverInfo> getPageByCity(Page page, Wrapper wrapper) {
        return driverInfoMapper.getPageByCity(page,wrapper);
    }


}
