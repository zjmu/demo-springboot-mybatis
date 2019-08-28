package com.ucar.train.jinmu.zhou.webmian.mapper;

import com.ucar.train.jinmu.zhou.webmian.domain.Words;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WordsMapper {

    Integer insertWords(List<Words> words);

    List<Words> getTopN(Integer n);

    Integer deleteAll();
}
