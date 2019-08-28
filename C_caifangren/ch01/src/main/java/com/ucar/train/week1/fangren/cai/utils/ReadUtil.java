package com.ucar.train.week1.fangren.cai.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;

public class ReadUtil {
    public static String fileReader(String filename) {
        /**
         *@Author:Fangren Cai
         *@Description:读取文件
         *@params: * @param filename
         *@Date:01:31 2019-07-19
         */
        //读取resource下的文件
        File file = new File(Thread.currentThread().getContextClassLoader().getResource(filename).getPath());
        BufferedReader bufferedReader = null;
        StringBuilder builder = null;
        try {
            FileReader reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);//用来将文件读取到缓存
            builder = new StringBuilder();
            String str = "";
            while ((str = bufferedReader.readLine())!= null){//逐行读取，不读取换行和末尾空格
                builder.append(str+"\n");//每行添加换行
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String string = builder.toString();
        return string;
    }

    public static String commonsFileReader(String filename){
        /**
         *@Author:Fangren Cai
         *@Description:封装commons-io的读取文件API
         *@params: * @param filename
         *@Date:01:31 2019-07-19
         */
        String string = "";
        InputStream stream = ReadUtil.class.getClassLoader().getResourceAsStream(filename);
        try {
            string = IOUtils.toString(stream,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        string = string + "\n";
        return string;
    }



    public static String subString(String content){
        /**
         *@Author:Fangren Cai
         *@Description:将除了"，"以外的标点换为"，"。
         *@params: * @param content
         *@Date: 2019-07-19
         */
        String[] array = {" ",".","!","?",":","(",")","\"","”",";","“","-"};
        for (int i = 0; i < array.length; i++){

            content = content.replace(array[i],",");
        }
        return content;
    }

    public static HashMap<String,Integer> hashMapSort(String filename){
        /**
         *@Author:Fangren Cai
         *@Description:得到每个单词的频次并将其降序
         *@params: * @param filename
         *@Date:01:34 2019-07-19
         */
        //String content = ReadUtil.fileReader(filename);
        String content = ReadUtil.commonsFileReader(filename);
        content = content.replace("\n","");//取消文章换行
        content = ReadUtil.subString(content);//所有标点替换为","
        String[] text = content.split(",");//将","之间的字符串逐个装进字符串数组text
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0;i<text.length;i++){
            String temp = text[i].toLowerCase();//换为小写容易比较相同的单词
            //单词过滤，如果一个单词长度大于1或者单词为"i"，并且不是纯数字，则认为是一个英文单词
            if ((temp.length() > 1 || temp.equals("i")) &&(!temp.matches("^[0-9]*$"))) {
                String key = temp;
                if (!"".equals(key)) {
                    Integer num = map.get(key);
                    if (num == null || num == 0) {
                        map.put(key, 1);
                    } else {
                        map.put(key, num + 1);
                    }
                }
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();//降序排列
            }
        });
        HashMap<String ,Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String,Integer> entry:list){
            result.put(entry.getKey(),entry.getValue());
        }
        return result;
    }

}
