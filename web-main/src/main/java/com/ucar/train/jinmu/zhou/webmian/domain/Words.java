package com.ucar.train.jinmu.zhou.webmian.domain;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author zjm
 * @date 2019/7/21
 */
@Data
public class Words {
    private BigInteger id;
    private String word;
    private Integer time;
    private Date createTime;
    private Timestamp modifyTime;
}
