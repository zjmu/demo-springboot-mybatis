package com.ucar.train.week1.fangren.cai.function;

import com.ucar.train.week1.fangren.cai.utils.ReadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CharNum {
    public static void charNum(String filename){
        /**
         *@Author:Fangren Cai
         *@Description:文章的所有字符量
         *@params: * @param filename
         *@Date:01:33 2019-07-19
         */
        log.debug("该文章有"+ReadUtil.fileReader(filename).length()+"个字符。");
    }
}
