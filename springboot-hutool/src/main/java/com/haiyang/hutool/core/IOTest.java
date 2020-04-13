package com.haiyang.hutool.core;/**
 * @Author: HaiYang
 * @Date: 2020/4/13 14:09
 */

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import cn.hutool.core.util.StrUtil;
import sun.reflect.misc.FieldUtil;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Properties;

/**
 * @author: Administrator
 * @Date: 2020/4/13 14:09
 * @Description:
 */
public class IOTest implements Closeable {


    public static void main(String[] args) throws IOException {
        BufferedInputStream inputStream =
                FileUtil.getInputStream("E:\\workspace-spring\\springboot-demo\\springboot-hutool\\src\\main\\resources\\test.txt");

        BufferedOutputStream outputStream =
                FileUtil.getOutputStream("E:\\workspace-spring\\springboot-demo\\springboot-hutool\\src\\main\\resources\\test2.txt");

        long copySize = IoUtil.copy(inputStream, outputStream, IoUtil.DEFAULT_BUFFER_SIZE);
        System.out.println("copySize = " + copySize);

        File touch = FileUtil.touch("E:\\workspace-spring\\springboot-demo\\springboot-hutool\\src\\main\\resources\\touch.txt");
        File imgfile = FileUtil.file("E:\\workspace-spring\\springboot-demo\\springboot-hutool\\src\\main\\resources\\test.jpg");
        System.out.println("FileUtil.getType(imgfile) = " + FileUtil.getType(imgfile));//gif

        //文件监听事件
        WatchMonitor watchMonitor = WatchMonitor.create(touch, WatchMonitor.ENTRY_MODIFY);
        watchMonitor.setWatcher(new SimpleWatcher() {
            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                Object context = event.context();
                System.out.println("context = " + context);
                System.out.println("currentPath = " + currentPath);

            }
        });

        ClassPathResource resource = new ClassPathResource("test.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        System.out.println("properties = " + properties);




//        IOTest ioTest = new IOTest();
//        IoUtil.close(ioTest);



    }

    @Override
    public void close() throws IOException {

    }
}
