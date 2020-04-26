package com.haiyang.spring.collection;/**
 * @Author: HaiYang
 * @Date: 2020/4/24 14:26
 */

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static cn.hutool.core.lang.Console.log;

/**
 * @author: Administrator
 * @Date: 2020/4/24 14:26
 * @Description: 解决类似Map<FirstName, Map < LastName, Person>>的实现
 */
public class Table_demo {

    public static void main(String[] args) {
        HashBasedTable<String, String, Integer> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put("haiyang", "chinese", 100);
        hashBasedTable.put("haiyang", "PE", 20);
        hashBasedTable.put("haiyang", "english", 80);
        hashBasedTable.put("bsj", "chinese", 80);
        hashBasedTable.put("bsj", "PE", 45);
        hashBasedTable.put("ltt", "PE", 90);
        boolean contains = hashBasedTable.contains("haiyang", "chinese");
        log("contains(haiyang,chinese):{}", contains);
        boolean containsColumn = hashBasedTable.containsColumn("art");
        log("containsCloumn(art):{}", containsColumn);
        boolean containsRow = hashBasedTable.containsRow("bsj");
        log("containsRow(bsj):{}", containsRow);
        boolean containsValue = hashBasedTable.containsValue(90);
        log("containsValue(90):{}", containsValue);
        Integer score = hashBasedTable.get("ltt", "PE");
        log("get(ltt,PE):{}", score);
        Set<Table.Cell<String, String, Integer>> cellSet = hashBasedTable.cellSet();
        log("cellSet:{}", cellSet);
        Map<String, Integer> column = hashBasedTable.column("PE");
        log("PE->{}", column);
        Set<String> classSet = hashBasedTable.columnKeySet();
        log("classSet:{}", classSet);
        Map<String, Map<String, Integer>> columnMap = hashBasedTable.columnMap();
        log("columnMap:{}", columnMap);
        Map<String, Integer> haiyang = hashBasedTable.row("haiyang");
        log("haiyang->{}", haiyang);
        Set<String> nameSet = hashBasedTable.rowKeySet();
        log("nameSet:{}", nameSet);
        Map<String, Map<String, Integer>> rowMap = hashBasedTable.rowMap();
        log("rowMap:{}", rowMap);
        Collection<Integer> values = hashBasedTable.values();
        log("value:{}", values);
        int size = hashBasedTable.size();
        log("size:{}", size);

    }
}
