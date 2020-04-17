package com.haiyang.spring.test.service.impl;

import com.haiyang.spring.test.entity.YyOrder;
import com.haiyang.spring.test.mapper.YyOrderMapper;
import com.haiyang.spring.test.service.IYyOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haiyang
 * @since 2020-04-17
 */
@Service
public class YyOrderServiceImpl extends ServiceImpl<YyOrderMapper, YyOrder> implements IYyOrderService {

}
