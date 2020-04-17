package com.haiyang.spring.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author haiyang
 * @since 2020-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YyOrder implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发送时间 向合作⽅服务器发送数
据的时间，发送⽅服务器的本地时
间. 以毫秒为单位的 Unix 时间戳
     */
    private Long timestamp;

    /**
     * 约约订单ID
     */
    private String orderId;

    /**
     * 合作⽅订单ID
     */
    private String oid;

    /**
     * 渠道订单ID
     */
    private String cpOrderId;

    /**
     * 乘客下单类型（本⼈下单：600，
代⼈下单：601）
     */
    private Integer passengerOrderType;

    /**
     * 乘客ID
     */
    private Long passengerId;

    /**
     * 乘客姓名
     */
    private String passengerName;

    /**
     * 乘客⼿机号
     */
    private String passengerNumber;

    /**
     * 实际乘车⼈
     */
    private String realPassengerName;

    /**
     * 实际乘车⼈电话
     */
    private String realPassengerNumber;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 司机姓名
     */
    private String driverName;

    /**
     * 司机⼿机号
     */
    private String driverNumber;

    /**
     * 订单状态
     */
    private Integer orderState;

    /**
     * 下单城市代码
     */
    private String cityCode;

    /**
     * 业务类型 （300：快车，301：专
车，302：出租车，303：城际）
     */
    private Integer bizType;

    /**
     * 服务类型 （350：正常，351：接
机，352：送机，353：接站，
354：送站，355：包车，356：
城际拼车，357：城际包车）
     */
    private Integer serviceType;

    /**
     * 时效类型 （200：实时单，201：
预约单）
     */
    private Integer realTimeType;

    /**
     * 订单来源 （etravel：⾼德，
app：客户端）
     */
    private String sourceType;

    /**
     * 取消类型
     */
    private Integer cancelType;

    /**
     * 订单取消时间
     */
    private Long cancelTime;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 产品类型 
     */
    private String productType;

    /**
     * 运⼒类型
     */
    private String rideType;

    /**
     * 车型
     */
    private String carType;

    /**
     * 车型名称
     */
    private String carTypeName;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 起始点经度
     */
    private Double slon;

    /**
     * 起始点纬度
     */
    private Double slat;

    /**
     * 起始点地址
     */
    private String saddress;

    /**
     * ⽬的地经度
     */
    private Double dlon;

    /**
     * ⽬的地纬度
     */
    private Double dlat;

    /**
     * ⽬的地地址
     */
    private String daddress;

    /**
     * 预估价（元）
     */
    private String estimatePrice;

    /**
     * 估⾥程（⽶）
     */
    private Integer estimateMileage;

    /**
     * 预估时间 （分钟）
     */
    private Integer estimateDuration;

    /**
     * 实际⾥程 （⽶）
     */
    private Integer factMileage;

    /**
     * 实际时间 （分钟）
     */
    private Integer factDuration;

    /**
     * 乘客下单时间
     */
    private Long passCreateTime;

    /**
     * 乘客预约时间
     */
    private Long departureTime;

    /**
     * 应答时间
     */
    private Long acceptTime;

    /**
     * 去接乘客时间
     */
    private Long comingPassengerTime;

    /**
     * 到达上车点时间
     */
    private Long arrivedDepartTime;

    /**
     * 乘客上车时间
     */
    private Long passengerAboardTime;

    /**
     * 到达⽬的时间
     */
    private Long arriveDestTime;

    /**
     * 确认费⽤时间
     */
    private Long confirmFareTime;

    /**
     * 订单⽀付完成时间
     */
    private Long orderFinishTime;

    /**
     * 所属车队id
     */
    private Long carTeamId;

    /**
     * 车队名称
     */
    private String carTeamName;


}
