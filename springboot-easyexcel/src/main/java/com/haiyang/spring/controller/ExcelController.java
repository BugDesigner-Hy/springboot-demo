package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/30 10:00
 */

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.haiyang.spring.entity.DownloadData01;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/4/30 10:00
 * @Description:
 */
@Api(value = "测试excel Api",tags = "测试excel Api")
@Controller
public class ExcelController {

    @ApiOperation(value = "excel下载 通过@ExcelProperty来标识表头",  notes = "excel下载 通过@ExcelProperty来标识表头")
    @GetMapping("/download/byExcelProperty")
    public void test(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<DownloadData01> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DownloadData01 one = new DownloadData01();
            one.setId(i + 1);
            one.setName("data" + i);
            data.add(one);
        }
        EasyExcel.write(response.getOutputStream(), DownloadData01.class).sheet("模板").doWrite(data);
    }

    @ApiOperation(value = "excel下载 通过策略自定义表头",  notes = "excel下载 通过策略自定义表头")
    @GetMapping("/download/custom-head")
    public void customHead(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, (WriteCellStyle) null);
        List<List<String>> head = getHead();
        List<DownloadDataWithCustomHead> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DownloadDataWithCustomHead one = new DownloadDataWithCustomHead();
            one.setId(i + 1);
            one.setName("data" + i);
            data.add(one);
        }
        EasyExcel.write(response.getOutputStream()).sheet("test").head(head).needHead(true).registerWriteHandler(horizontalCellStyleStrategy).doWrite(data);
    }

    private List<List<String>> getHead() {
        //两层标题
//        List<List<String>> head = new ArrayList<>();
//        Arrays.asList("标题1","标题2","标题3","标题4").forEach(subHead ->{
//            List<String> head2 = new ArrayList<>();
//            head2.add("总标题");
//            head2.add(subHead);
//            head.add(head2);
//        });
        //三层嵌套标题
        List<List<String>> head = new ArrayList<>();
        Arrays.asList("标题1","标题2","标题3","标题4").forEach(subHead ->{
            Arrays.asList(subHead+"-01",subHead+"-02").forEach(thirdHead->{
                List<String> head2 = new ArrayList<>();
                head2.add("总标题");
                head2.add(subHead);
                head2.add(thirdHead);
                head.add(head2);
            });
        });

//        Arrays.asList("标题5-1","标题5-2").forEach(subHead ->{
//            List<String> head3 = new ArrayList<>();
//            head3.add("总标题");
//            head3.add("标题5");
//            head3.add(subHead);
//            head.add(head3);
//        });
        return head;
    }


    //将数据抽象类单独作为类 不要放到这里 会出现额外的一列[this$0]
    @Data
    class DownloadData{
        @ExcelProperty("序号")
        private int id;
        @ExcelProperty("姓名")
        private String name;
    }

    @Data
    class DownloadDataWithCustomHead{
        private int id;
        private String name;
    }
}
