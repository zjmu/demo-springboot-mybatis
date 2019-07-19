package com.ucar.train.week1.xiaoli.du.ch01;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author ：DZGodly
 * @date ：Created in 2019/7/17 16:10
 */
@Slf4j
public class StatisticsHelper {

    private boolean useCommonIO;

    private long requiredWords = 0;

    private long numberOfChars = 0;

    private LinkedList<Integer> numberOfWordsPerLine = new LinkedList<>();

    private Map<String, Integer> frequency = new HashMap<>();

    private Set<Map.Entry<String, Integer>> sortedWords = new TreeSet<>(new Comparator<Map.Entry>() {
        @Override
        public int compare(Map.Entry o1, Map.Entry o2) {
            return (int) o2.getValue() - (int) o1.getValue();
        }
    });


    public StatisticsHelper(String fileName, boolean useCommonIO) {
        this.useCommonIO = useCommonIO;
        try {
            inputAndCount(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printResult() {
        System.out.println("-----整篇小说的字符数量-----");
        System.out.println(numberOfChars);
        System.out.println("----------------------------");
        System.out.println("-----每一行的单词量-----");
        System.out.println(numberOfWordsPerLine);
        System.out.println("-------------------------");
        System.out.println("-----阅读小说所需最小词汇量-----");
        System.out.println(requiredWords);
        System.out.println("--------------------------------");
        System.out.println("-----词频统计-----");
        for (Map.Entry<String, Integer> one : sortedWords) {
            System.out.println(one.getKey() + ":" + one.getValue());
        }
        System.out.println("----------------------------");
    }

    private void inputAndCount(String fileName) throws IOException {
        if (useCommonIO)
            countWithCommonsIO(fileName);
        else
            countWithNativeAPI(fileName);
        requiredWords = frequency.size();
        sortedWords.addAll(frequency.entrySet());
    }

    /**
     * 使用 Commons-IO 统计
     *
     * @param fileName 文件名
     * @throws IOException in case of an I/O error
     */
    private void countWithCommonsIO(String fileName) throws IOException {
        File file = FileUtils.getFile(fileName);
        List<String> lines = FileUtils.readLines(file, "UTF-8");
        for (String line : lines)
            count(line);
    }

    /**
     * 使用原生 API 统计
     *
     * @param fileName 文件名
     * @throws IOException in case of an I/O error
     */
    private void countWithNativeAPI(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null)
            count(line);
    }

    /**
     * 统计一行
     *
     * @param line 行
     */
    private void count(String line) {
        String[] words = line.split(" ");
        numberOfWordsPerLine.add(words.length);
        numberOfChars += words.length - 1; // 每行有n个单词就有 n-1 个 ' '
        for (String word : words) {
            numberOfChars += word.length();
            word = prune(word);
            if ("".equals(word)) // 不统计空串
                continue;
            int cnt = frequency.getOrDefault(word, 0);
            cnt++;
            frequency.put(word, cnt);
        }
    }

    private String prune(String word) {
        word = word.replaceAll("[\\pP‘’“”]", "") // 不统计标点符号
                .toLowerCase(); // 词频统计不区分大小写
        return word;
    }
}
