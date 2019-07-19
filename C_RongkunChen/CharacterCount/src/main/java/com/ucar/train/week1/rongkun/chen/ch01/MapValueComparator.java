package com.ucar.train.week1.rongkun.chen.ch01;


import java.util.Comparator;
import java.util.Map;

/**
 * 对Map按照value降序排序
 */
public class MapValueComparator implements Comparator<Map.Entry<String,Integer>> {

    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o2.getValue()-o1.getValue();
    }
}