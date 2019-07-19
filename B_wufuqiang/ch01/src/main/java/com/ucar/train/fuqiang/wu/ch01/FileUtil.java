package com.ucar.train.fuqiang.wu.ch01;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    /*
        统计字符数量
        path：文件路径
        return：返回字符数量
     */
    public int charCount(String path) {
        File file = new File(path);
        int count = 0;      //字符个数
        try {
            String content = FileUtils.readFileToString(file);
            String temp = content.replaceAll("(\\r\\n|\\r|\\n|\\n\\r|\\s*)", "");
            count = temp.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /*
        统计每行单词数
        path：文件路径
        return：返回一个包含每行单词数的动态数组
     */
    public static List<Integer> wordCountByLine(String path) {
        File file = new File(path);
        //存放每行单词数的动态数组
        List<Integer> worldCountList = new ArrayList<>();

        try {
            //按行读取文件
            List<String> lineList = FileUtils.readLines(file, "utf-8");

            List<String[]> list = new ArrayList<>();
            for (String line : lineList) {
                //设置正则表达式，用来判断是否为空行
                String regex = "[a-zA-Z]";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);
                //判断是否为空行
                if (!matcher.find()) {
                    continue;
                }
                //对字符串进行处理，将  ’ 替换为空格，并过滤特殊字符
                String lineTemp = line.replaceAll("'", " ").
                        replaceAll("[`~!@#-_$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】《》‘；：”“’。，、？]", "");
                //以空格为分隔符，分割出单词
                String[] words = lineTemp.trim().split("\\s+");
                list.add(words);
                //将每行单词数存入worldCountList
                worldCountList.add(words.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return worldCountList;
    }

    public static List<Map.Entry<String, Integer>> wordFrequency(String path) {
        //wordMap存储单词及出现的次数
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        File file = new File(path);
        try {
            //读取文件，将文件内容转换为字符串
            String strContent = FileUtils.readFileToString(file, "utf-8");
            //对字符串进行处理，将  ’ 替换为空格，并过滤特殊字符
            String content = strContent.replaceAll("'", " ").
                    replaceAll("[`~!@#-_$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】《》‘；：”“’。，、？]", "");
            //分割出单词
            String[] words = content.trim().split("\\s+");
            for (String word : words) {
                //判断wordMap中是否存在该单词，不存在，则存入map中
                if (wordMap.get(word) == null) {
                    wordMap.put(word, new Integer(1));
                }
                //单词以存在，则将数量 +1
                else {
                    Integer temp = (Integer) wordMap.get(word);
                    temp = new Integer(temp.intValue() + 1);
                    wordMap.put(word, temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //排序
        List<Map.Entry<String, Integer>> wordMapSorted =new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());
        Collections.sort(wordMapSorted, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o2.getValue() - o1.getValue()==0)
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                return (o2.getValue() - o1.getValue());
            }
        });
        return wordMapSorted;
    }


    public static void main(String args[]) {
        List<Map.Entry<String, Integer>> wordMapSorted = FileUtil.wordFrequency("C:\\Users\\wfq\\Desktop\\b.txt");
        for (Map.Entry<String, Integer> entry : wordMapSorted) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
    }
}

