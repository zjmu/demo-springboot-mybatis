package com.ucar.train.jinmu.zhou.webmian.controller;

import com.ucar.train.jinmu.zhou.util.BasicResponse;
import com.ucar.train.jinmu.zhou.util.BusinessWrapper;
import com.ucar.train.jinmu.zhou.util.ResponseUtil;
import com.ucar.train.jinmu.zhou.webmian.domain.Paragraph;
import com.ucar.train.jinmu.zhou.webmian.mapper.ParagraphMapper;
import com.ucar.train.jinmu.zhou.webmian.service.ParagraphService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjm
 * @date 2019/7/20
 */
@RestController
@Slf4j
@Data
public class ParagraphController {
    @Autowired
    private ParagraphMapper paragraphMapper;

    @Autowired
    private ParagraphService paragraphService;

    @GetMapping("/getAllParagraph")
    public BasicResponse<List<Paragraph>> getALL(){

        return BusinessWrapper.wrap(response -> {
            List<Paragraph> paragraphs = paragraphMapper.getAll();
            ResponseUtil.set(response, 0, "查询", paragraphs);
        }, log);
    }

    @GetMapping("/countWords")
    public BasicResponse<String> insertMore(){

        return BusinessWrapper.wrap(response -> {
            paragraphService.splitParagraph();
            ResponseUtil.set(response, 0, "查询", "统计成功！");
        }, log);
    }

    @GetMapping("/getParagraphById")
    public BasicResponse<Paragraph> getById(@RequestParam("paragraphId")Integer paragraphId){

        return BusinessWrapper.wrap(response -> {
            Paragraph paragraph=paragraphMapper.getById(paragraphId);
            ResponseUtil.set(response, 0, "查询", paragraph);
        }, log);
    }
}
