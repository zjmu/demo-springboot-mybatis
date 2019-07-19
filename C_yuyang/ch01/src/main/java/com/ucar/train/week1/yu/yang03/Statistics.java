package com.ucar.train.week1.yu.yang03;

import java.io.*;
import java.util.*;

public class Statistics {
    public int sumOfWords; //单词总数
    public int minWords; //总词汇量
    public int numOfRows; //行数
    public HashMap<Integer, Integer> numOfLine; //每行单词量
    public HashMap<String, Integer> numOfWord; //单词词频

    public Statistics() {
        numOfRows = 0;
        minWords = 0;
        sumOfWords = 0;
        numOfLine = new HashMap<>();
        numOfWord = new HashMap<>();
    }

    private ArrayList<String> readFile(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineOfFile; //单行句子
            String[] wordsOfLine; //每行的所有单词
            while ((lineOfFile = bufferedReader.readLine()) != null) {
                lineOfFile = lineOfFile.replaceAll("\\pP|\\pS|\\pC|\\pN", "");
                lineOfFile = lineOfFile.replaceAll("西葡萄wwwwestworldcomcn收集整理", "");
                ++numOfRows;
                if ("".equals(lineOfFile)) {
                    numOfLine.put(numOfRows, 0);
                } else {
                    wordsOfLine = lineOfFile.split("\\s+");
                    sumOfWords = sumOfWords + wordsOfLine.length;
                    numOfLine.put(numOfRows, wordsOfLine.length);
                    for (String s : wordsOfLine) {
                        words.add(s.toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public void statistics(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        words = readFile(filePath);
        for (String word : words) {
            if (numOfWord.containsKey(word)) {
                numOfWord.put(word, numOfWord.get(word) + 1);
            } else if (!word.equals("")) {
                numOfWord.put(word, 1);
            }
        }
        minWords = numOfWord.size();
    }


}
