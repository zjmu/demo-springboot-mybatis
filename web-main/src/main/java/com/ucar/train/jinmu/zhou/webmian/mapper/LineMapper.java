package com.ucar.train.jinmu.zhou.webmian.mapper;


import com.ucar.train.jinmu.zhou.webmian.domain.Line;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LineMapper {

    List<Line> getAll();

    int insertMore(List<Line> lines);

    Line getLineOfParagraph(@Param("paragraphId")Integer paragraphId,@Param("lineId")Integer LineId);

    List<Line> getLines(@Param("paragraphId")Integer paragraphId);

    Integer deleteAll();

//    Integer updateLines();

}
