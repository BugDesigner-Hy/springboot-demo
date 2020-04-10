package com.haiyang.kmeans.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author haiyang
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Cluster implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private Double x;

    private Double y;

    private Integer pointId;

    @TableField(exist = false)
    private Point clusterPoint;

    @TableField(exist = false)
    private List<Point> data;


}
