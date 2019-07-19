package com.ucar.train.fuqiang.wu.ch01.service;

import java.util.List;
import java.util.Map;

public interface ArticleIndicatorService {

    /***.
     * 统计文章字符总数
     * @param path 文件路径
     * @return 字符总数
     */
    public int charCount(String path);

    /***
     * 统计每行单词数
     * @param path 文件路径
     * @return 存储每行单词数的动态数组
     */
    public List<Integer> wordCountByLine(String path);

    /**
     * 统计单词量
     * @param path 文件路径
     * @return 单词量
     */
    public int wordVolume(String path);

    /***
     * 统计词频
     * @param path 文件路径
     * @return 返回排序好的list集合，它存储着包含map集合的所有"映射"的Set集合的所有元素
     */
    public List<Map.Entry<String, Integer>> wordFrequency(String path);
}
