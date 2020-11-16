package com.haiyang.dynamic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 司机车队对应关系
 * </p>
 *
 * @author haiyang
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DriverInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 司机编号
     */
    private String driverId;

    /**
     * 业务类型
     */
    private String type;

    /**
     * 城市
     */
    private String city;

    /**
     * 运力公司
     */
    private String team;

    /**
     * 司机分组
     */
    private String dGroup;

    /**
     * 司机姓名
     */
    private String driverName;

    /**
     * 司机电话
     */
    private String driverTel;

    /**
     * 身份证号码
     */
    private String driverCert;

    /**
     * 性别
     */
    private String driverSex;

    /**
     * 招募类型（带车加盟/自营）
     */
    private String driverType;

    /**
     * 家庭住址
     */
    private String familyAddress;

    /**
     * 驾驶证号
     */
    private String licenceNum;

    /**
     * 车辆品牌
     */
    private String carBrand;

    /**
     * 车牌
     */
    private String carPlate;

    /**
     * 车辆型号
     */
    private String carType;

    /**
     * 核定人数
     */
    private Integer checkPeople;

    /**
     * 车辆id
     */
    private String carId;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    /**
     * 激活时间
     */
    private LocalDateTime activeTime;

    /**
     * 渠道服务分
     */
    private BigDecimal serviceScore;

    /**
     * 平台调度封禁状态
     */
    private String closeStatus;

    /**
     * 渠道调度封禁状态
     */
    private String channelCloseStatus;

    /**
     * 身份证地址
     */
    private String idCardAddress;

    /**
     * 网络预约出租汽车驾驶员证照片	网络预约出租汽车驾驶员证照片	网络预约出租汽车驾驶员证照片
     */
    private String driverCertPic;

    /**
     * 监督卡照片
     */
    private String monitorCardPic;

    /**
     * 网约预约出租汽车运输证照片
     */
    private String carCertPic;

    /**
     * 标签
     */
    private String label;


}
