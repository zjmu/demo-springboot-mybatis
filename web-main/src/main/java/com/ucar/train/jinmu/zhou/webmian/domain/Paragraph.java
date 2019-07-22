package com.ucar.train.jinmu.zhou.webmian.domain;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zjm
 * @date 2019/7/20
 */
@Data
public class Paragraph {
    private BigInteger id;
    private Integer articleId;
    private BigInteger paragraphId;
    private String description;
    private Integer wordsNum;
    private Date createTime;
    private Timestamp modifyTime;
    private List<Line> lines;
}
