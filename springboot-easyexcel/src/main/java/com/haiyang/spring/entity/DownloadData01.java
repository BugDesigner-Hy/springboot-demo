package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/4/30 11:14
 */

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: Administrator
 * @Date: 2020/4/30 11:14
 * @Description:
 */
@Data
public class DownloadData01 {

    @ExcelProperty("序号")
    private int id;
    @ExcelProperty("姓名")
    private String name;
}
