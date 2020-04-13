package com.haiyang.hutool.core;/**
 * @Author: HaiYang
 * @Date: 2020/4/13 15:46
 */


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.util.Assert;


import javax.print.attribute.standard.NumberUp;
import java.math.BigDecimal;

/**
 * @author: Administrator
 * @Date: 2020/4/13 15:46
 * @Description:
 */
public class NumTest {

    public static void main(String[] args) {


        double tel = 123456.123456;
        BigDecimal round = NumberUtil.round(tel, 4);
        System.out.println("round = " + round);

        int[] randoms = NumberUtil.generateRandomNumber(1, 10, 1);
        System.out.println("randoms = " + randoms[0]);

        String ID_18 = "321083197812162119";
        String ID_15 = "150102880730303";

        //是否有效
        boolean valid = IdcardUtil.isValidCard(ID_18);
        boolean valid15 = IdcardUtil.isValidCard(ID_15);

        //转换
        String convert15To18 = IdcardUtil.convert15To18(ID_15);

        //年龄
        DateTime date = DateUtil.parse("2017-04-10");

        int age = IdcardUtil.getAgeByIdCard(ID_18, date);//28

        //生日
        String birth = IdcardUtil.getBirthByIdCard(ID_18);//19880730

        //省份
        String province = IdcardUtil.getProvinceByIdCard(ID_18);//江苏

    }
}
