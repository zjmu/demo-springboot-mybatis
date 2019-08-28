package com.ucar.train.jinmu.zhou.webmian.domain;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author zjm
 * @date 2019/7/20
 */
@Data
public class Line {
    private BigInteger id;
    private Integer lineId;
    private BigInteger paragraphId;
    private String text;
    private Integer wordsNum;
    private Date createTime;
    private Timestamp modifyTime;
}
