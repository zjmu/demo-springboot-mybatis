package com.ucar.train.fuqiang.wu.ch01.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@Slf4j
public class ArticleIndicatorServiceImplTest {

    String path = "C:\\wfq\\work\\idea\\ch01\\src\\main\\resources\\TheOldManAndTheSea.txt";

    ArticleIndicatorService articleIndicatorService = new ArticleIndicatorServiceImpl();

    /*
        字符总数
     */
    @Test
    public void charCount() {
        Integer count = articleIndicatorService.charCount(path);
        log.info("{},{},{}","字符总数",count,new Date());
    }

    /*
        每行单词数
     */
    @Test
    public void wordCountByLine() {
        List<Integer> wordCountByLine = articleIndicatorService.wordCountByLine(path);
        for (int i = 0;i < wordCountByLine.size();i++){
            log.info("{}","第" + (i+1) +"行：" + wordCountByLine.get(i));
        }
    }

    /*
        词频统计
     */
    @Test
    public void wordFrequency() {
        List<Map.Entry<String,Integer>> wordMapSorted = articleIndicatorService.wordFrequency(path);
        for (Map.Entry<String,Integer> entry: wordMapSorted) {
            log.info("{}",entry.getKey() + "：" + entry.getValue());
        }
    }

    /*
        阅读所需最低词汇量
     */
    @Test
    public void wordVolume() {
        int wordVolume = articleIndicatorService.wordVolume(path);
        log.info("{},{}","阅读所需最低词汇量：",wordVolume);
    }
}