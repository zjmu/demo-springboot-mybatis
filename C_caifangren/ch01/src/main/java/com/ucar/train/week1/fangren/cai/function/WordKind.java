package com.ucar.train.week1.fangren.cai.function;

import com.ucar.train.week1.fangren.cai.utils.ReadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class WordKind {
    public static void wordKind(String filename){
        /**
         *@Author:Fangren Cai
         *@Description:文章最低需要词汇量
         *@params: * @param filename
         *@Date:01:33 2019-07-19
         */
        int count = 0;
        for (Map.Entry<String,Integer> entry: ReadUtil.hashMapSort(filename).entrySet()){
            count++;
        }
        log.debug("阅读这篇小说最低需要"+count+"词汇量。");
    }
}
