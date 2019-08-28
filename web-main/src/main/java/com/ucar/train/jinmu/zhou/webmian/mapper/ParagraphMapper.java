package com.ucar.train.jinmu.zhou.webmian.mapper;

import com.ucar.train.jinmu.zhou.webmian.domain.Paragraph;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParagraphMapper {

    int insertMore(List<Paragraph> paragraphs);

    List<Paragraph> getAll();

    Paragraph getById(Integer paragraphId);

    Integer deleteAll();

}
