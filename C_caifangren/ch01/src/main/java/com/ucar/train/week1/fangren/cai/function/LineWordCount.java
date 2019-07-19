package com.ucar.train.week1.fangren.cai.function;

import com.ucar.train.week1.fangren.cai.utils.ReadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LineWordCount {
    public static void lineWordCount(String filename){
        /**
         *@Author:Fangren Cai
         *@Description:读取每一行的单词量
         *@params: * @param filename
         *@Date:01:32 2019-07-19
         */
        String content = ReadUtil.fileReader(filename);
        //String content = ReadUtil.commonsFileReader(filename);
        content = ReadUtil.subString(content);
        content = content.replace(" ","");
        String[] text = content.split("\n");//文章以换行符划分为多个字符串
        if (Integer.valueOf(text[0].charAt(0))==65279){//因为编码问题，第一行的ascii码可能为65279
            text[0] = "";
        }


        for (int i = 0;i<text.length;i++){
            String[] word = text[i].split(",");//每行以逗号划分出多个单词
            if (word.length==1&&(word[0].equals(""))){
                log.debug("第"+(i+1)+"行的单词量为:"+0);
            }else {
                log.debug("第"+(i+1)+"行的单词量为:"+word.length+"。");
            }
        }
    }
}
