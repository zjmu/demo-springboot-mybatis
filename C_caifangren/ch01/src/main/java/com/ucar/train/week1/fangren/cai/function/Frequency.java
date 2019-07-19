package com.ucar.train.week1.fangren.cai.function;

import com.ucar.train.week1.fangren.cai.utils.ReadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Frequency {
    
    
    public static void printWordsFrequency(String filename){
        /**
         *@Author:Fangren Cai
         *@Description:统计所有词的频次
         *@params: * @param filename
         *@Date:01:33 2019-07-19
         */
        for (Map.Entry<String,Integer> entry: ReadUtil.hashMapSort(filename).entrySet()){
            log.debug("单词 ：" + entry.getKey() + ", 出现次数为：" + entry.getValue());
        }
    }
}
