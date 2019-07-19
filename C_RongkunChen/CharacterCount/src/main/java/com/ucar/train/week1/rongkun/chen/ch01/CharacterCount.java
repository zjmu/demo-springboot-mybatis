package com.ucar.train.week1.rongkun.chen.ch01;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CharacterCount {
    //测试文件位置
    public String filePath = "src/main/resources/TheOldManAndTheSea.txt";
    //第几行
    private int lineNumber = 1;
    //总的字符数
    private int totalCharacters = 0;
    //存放所有单词的列表
    private List<String> wordsList = new ArrayList<String>();
    //记录每行里有多少单词数的map
    private Map<Integer, Integer> wordsInLineMap = new HashMap<Integer, Integer>();
    //记录每行里有多少单词数的map
    private Map<String, Integer> wordFrenquencyMap = new HashMap<String, Integer>();

    /**
     * 测试启动入口
     */
    public void launch() {
        //统计单词出现频次
        countWordFrenquency();
        //对map利用value排序
        List<Map.Entry<String, Integer>> list = sortMapByValue();
        //打印map信息
        if (list != null) {
            print(list);
        }
    }

    /**
     * 指定读取文件的方式
     *
     * @param filePath 指定文件的绝对路径路径
     * @throws IOException IO异常
     */
    public void readSelectedFile(String filePath, boolean useNativeAPI) throws IOException {
        if (useNativeAPI) {
            readByJdk(filePath);
        } else {
            readByCommonsIO(filePath);
        }
    }

    /**
     * commons-IO读文件
     *
     * @param filePath 指定文件的绝对路径路径
     * @throws IOException IO读异常
     */
    private void readByCommonsIO(String filePath) throws IOException {
        List<String> readLines = IOUtils.readLines(new FileInputStream(filePath), "utf-8");
        for (String line : readLines) {
            //统计：总的字符数/所有单词/每行的数据（单词量）
            countAll(line);
        }
    }

    /**
     * 统计信息：总的字符数/所有单词/每行的数据（单词量）
     *
     * @param line 从小说里读出的一行
     */
    private void countAll(String line) {
        //过滤出只含有字母的
        String[] wordArrs = line.split("[^a-zA-Z]");
        for (String word : wordArrs) {
            //如果单词长度不为0
            if (word.length() != 0) {
                totalCharacters += word.length();
                wordsList.add(word.toLowerCase());
            }
        }
        //把每行的数据（单词量）放入map
        wordsInLineMap.put(lineNumber++, wordArrs.length);
    }

    /**
     * JDK原生读文件
     *
     * @param filePath 指定文件的绝对路径路径
     * @throws IOException IO读异常
     */
    @SuppressWarnings("all")
    private void readByJdk(String filePath) throws IOException {
        String readLine = null;
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while ((readLine = br.readLine()) != null) {
            //统计：总的字符数/所有单词/每行的数据（单词量）
            countAll(readLine);
        }
        br.close();
    }

    /**
     * 统计每个单词出现的频次
     */
    private void countWordFrenquency() {
        for (String word : wordsList) {
            if (wordFrenquencyMap.get(word) == null) {
                wordFrenquencyMap.put(word, 1);
            } else {
                wordFrenquencyMap.put(word, wordFrenquencyMap.get(word) + 1);
            }
        }
    }


    /**
     * 打印统计信息
     *
     * @param list 按照频次降序排序的单词
     */
    private void print(List<Map.Entry<String, Integer>> list) {
        System.out.println("整篇小说的所有字符数量：" + totalCharacters);
        System.out.println("阅读这篇小说需要的最低词汇量：" + wordsList.size());

        System.out.println("========================每一行的数据统计如下==========================");
        for (Integer entry : wordsInLineMap.keySet()) {
            System.out.println("第" + entry + "行里有：" + wordsInLineMap.get(entry) + "个单词");
        }
        System.out.println("========================每一行的数据统计结束==========================");
        System.out.println("========================单词词频统计如下==========================");
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("========================单词词频统计结束==========================");
    }

    /**
     * 实现按词频降序排序
     *
     * @return 按单词频次降序排序list
     */
    private List<Map.Entry<String, Integer>> sortMapByValue() {
        if (wordFrenquencyMap.isEmpty()) {
            return null;
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordFrenquencyMap.entrySet());
        Collections.sort(list, new MapValueComparator());
        return list;
    }
}




