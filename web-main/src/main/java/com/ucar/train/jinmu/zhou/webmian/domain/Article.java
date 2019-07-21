package com.ucar.train.jinmu.zhou.webmian.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author zjm
 * @date 2019/7/21
 */
@Data
public class Article {
    private BigInteger id;
    private Integer articleId;
    private String articleName;
    private String author;
    private BigDecimal price;
    private Long totalWordsNum;
    private Long needWordsNum;
    private Date createTime;
    private Timestamp modifyTime;
}
