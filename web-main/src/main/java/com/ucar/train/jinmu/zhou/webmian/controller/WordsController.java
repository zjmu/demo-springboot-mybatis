package com.ucar.train.jinmu.zhou.webmian.controller;

import com.ucar.train.jinmu.zhou.util.BasicResponse;
import com.ucar.train.jinmu.zhou.util.BusinessWrapper;
import com.ucar.train.jinmu.zhou.util.ResponseUtil;
import com.ucar.train.jinmu.zhou.webmian.domain.Paragraph;
import com.ucar.train.jinmu.zhou.webmian.domain.Words;
import com.ucar.train.jinmu.zhou.webmian.mapper.WordsMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjm
 * @date 2019/7/21
 */
@RestController
@Slf4j
@Data
public class WordsController {

    @Autowired
    private WordsMapper wordsMapper;

    @GetMapping("/getTopNWords")
    public BasicResponse<List<Words>> getById(@RequestParam("topN")Integer topN){

        return BusinessWrapper.wrap(response -> {
            List<Words> words=wordsMapper.getTopN(topN);
            ResponseUtil.set(response, 0, "查询", words);
        }, log);
    }
}
