package com.haiyang.hutool.core;/**
 * @Author: HaiYang
 * @Date: 2020/4/13 14:54
 */

import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author: Administrator
 * @Date: 2020/4/13 14:54
 * @Description:
 */
public class StrTest {
    public static void main(String[] args) {

        String fileName = StrUtil.removeSuffix("pretty_girl.jpg", ".jpg") ; //fileName -> pretty_girl

        String template = "{}爱{}，就像老鼠爱大米";
        String str = StrUtil.format(template, "我", "你"); //str -> 我爱你，就像老鼠爱大米

        String execStr = RuntimeUtil.execForStr("ipconfig");
        System.out.println("exec = " + execStr.toString());

    }

}
