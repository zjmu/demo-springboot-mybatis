package com.ucar.train.jinmu.zhou.webmian.mapper;

import com.ucar.train.jinmu.zhou.webmian.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {

    Article getById(@Param("articleId")Integer articleId);

    Integer updateArticle(@Param("article")Article article);

    Integer insertArticle(@Param("article")Article article);

    Integer deleteAll();
}
