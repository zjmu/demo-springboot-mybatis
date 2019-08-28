package com.ucar.train.jinmu.zhou.webmian.controller;


import com.ucar.train.jinmu.zhou.util.BasicResponse;
import com.ucar.train.jinmu.zhou.util.BusinessWrapper;
import com.ucar.train.jinmu.zhou.util.ResponseUtil;
import com.ucar.train.jinmu.zhou.webmian.domain.Line;
import com.ucar.train.jinmu.zhou.webmian.mapper.ParagraphMapper;
import com.ucar.train.jinmu.zhou.webmian.mapper.LineMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zjm
 * @date 2019/7/20
 */
@Slf4j
@Data
@RestController
public class LineController {

    @Autowired
    private ParagraphMapper paragraphMapper;

    @Autowired
    private LineMapper lineMapper;

    @GetMapping("/getAllText")
    public BasicResponse<List<Line>> getALL() {

        return BusinessWrapper.wrap(response -> {
            List<Line> textLine = lineMapper.getAll();
            ResponseUtil.set(response, 0, "查询", textLine);
        }, log);
    }

    @GetMapping("/lineOfParagraph")
    public BasicResponse<Line> getlineOfParagraph(@RequestParam("paragraphId")Integer paragraphId,@RequestParam("lineId")Integer lineId) {

        return BusinessWrapper.wrap(response -> {
            Line line = lineMapper.getLineOfParagraph(paragraphId,lineId);
            ResponseUtil.set(response, 0, "查询", line);
        }, log);
    }

}

