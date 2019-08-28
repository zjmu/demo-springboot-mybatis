package com.ucar.train.jinmu.zhou.webmian.service;

import com.google.common.annotations.VisibleForTesting;
import com.ucar.train.jinmu.zhou.webmian.domain.Article;
import com.ucar.train.jinmu.zhou.webmian.domain.Line;
import com.ucar.train.jinmu.zhou.webmian.domain.Paragraph;
import com.ucar.train.jinmu.zhou.webmian.domain.Words;
import com.ucar.train.jinmu.zhou.webmian.mapper.ArticleMapper;
import com.ucar.train.jinmu.zhou.webmian.mapper.LineMapper;
import com.ucar.train.jinmu.zhou.webmian.mapper.ParagraphMapper;
import com.ucar.train.jinmu.zhou.webmian.mapper.WordsMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author zjm
 * @date 2019/7/20
 */
@Service
@Slf4j
@Data
public class ParagraphService {

    @Autowired
    private ParagraphMapper paragraphMapper;

    @Autowired
    private LineMapper lineMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private WordsMapper wordsMapper;

    Integer lineNum=1;
    BigInteger paragraphNum=new BigInteger("1");
    Integer paragraphWordsNum=0;
    Long totalWordsNum=0L;
    String paragraphContent="";
    public void splitParagraph(){
        Integer lineWordsNum=0;//一行单词数
        Map<String,Integer> map=new HashMap<>();
        List<Paragraph> paragraphs =new ArrayList<>();
        List<Line> lines =new ArrayList<>();
        Boolean isParagraph=false;

        FileInputStream fileInputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        try{
            String filePath=System.getProperty("user.dir")+"/web-main/src/main/resources/TheOldManAndTheSea.txt";
            fileInputStream=new FileInputStream(new File(filePath));
            inputStreamReader=new InputStreamReader(fileInputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            String lineContent="";
            while((lineContent=bufferedReader.readLine())!=null) {
                if (lineContent.equals("")) {
                    isParagraph=true;
                    continue;
                }
                //段处理
                if(isParagraph){
                    isParagraph=false;
                    dealParagraph(lineContent,paragraphs);
                }
                paragraphContent+=lineContent;
                //处理行
                lineWordsNum=dealLine(lineNum,paragraphNum,lineContent, lines,map);
                paragraphWordsNum+=lineWordsNum;
                lineNum++;
            }

            Article article=new Article();
            article.setArticleId(1);
            article.setArticleName(lines.get(0).getText());
            article.setAuthor("海明威");
            article.setTotalWordsNum(totalWordsNum);
            article.setNeedWordsNum((long)map.size());
            Date date=new Date();
            java.sql.Date createTime=new java.sql.Date(date.getTime());
            article.setCreateTime(createTime);
            article.setPrice(new BigDecimal("120.2"));
            articleMapper.deleteAll();
            articleMapper.insertArticle(article);

            List<Words> words=new ArrayList<>();
            for (Map.Entry<String,Integer> entry : map.entrySet()) {
                Words word=new Words();
                word.setWord(entry.getKey());
                word.setTime(entry.getValue());
                word.setCreateTime(createTime);
                words.add(word);
            }
            wordsMapper.deleteAll();
            wordsMapper.insertWords(words);
            paragraphMapper.deleteAll();
            paragraphMapper.insertMore(paragraphs);
            lineMapper.deleteAll();
            lineMapper.insertMore(lines);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void dealParagraph(String lineContent,List<Paragraph>paragraphs){
        for(int i=0;i<lineContent.length();i++){
            //上一行空白且该行第一个字母大写则是一段
            if(('a'<=lineContent.charAt(i)&&lineContent.charAt(i)<='z')||('A'<=lineContent.charAt(i)&&lineContent.charAt(i)<='Z')){
                if('A'<=lineContent.charAt(i)&&lineContent.charAt(i)<='Z'){
                    lineNum=1;
                    totalWordsNum+=paragraphWordsNum;
                    Paragraph paragraph=new Paragraph();
                    Date date=new Date();
                    java.sql.Date createTime=new java.sql.Date(date.getTime());
                    paragraph.setCreateTime(createTime);
                    paragraph.setArticleId(1);
                    paragraph.setWordsNum(paragraphWordsNum);
                    paragraph.setParagraphId(paragraphNum);
                    paragraph.setDescription(paragraphContent);
                    paragraph.setParagraphId(new BigInteger("1"));
                    paragraphs.add(paragraph);
                    paragraphContent=lineContent;
                    paragraphWordsNum=0;
                    paragraphNum=paragraphNum.add(new BigInteger("1"));
                }
                break;
            }
        }
    }

    public Integer dealLine(Integer lineNum, BigInteger paragraphNum, String lineContent, List<Line> lines, Map<String,Integer>map){
        //处理改行单词
        Integer newWordsNum=deleteStr(lineContent,map);
        //处理该行信息
        Line line=new Line();
        line.setLineId(lineNum);
        line.setParagraphId(paragraphNum);
        line.setText(lineContent);
        line.setWordsNum(newWordsNum);
        Date date=new Date();
        java.sql.Date createTime=new java.sql.Date(date.getTime());
        line.setCreateTime(createTime);
        lines.add(line);
        return newWordsNum;
    }


    public Integer deleteStr(String lineContent,Map<String,Integer> map){
        Map<String,Integer> lineMap=new HashMap<>();
        lineContent=lineContent.toLowerCase();
        String[] ss = lineContent.split("\\s+");
        //去除无关字符
        String[] words=new String[ss.length];
        for (int j=0;j<ss.length;j++){
            words[j]="";
            for (int i=0;i<ss[j].length();i++) {
                if ((ss[j].charAt(i) >= 'a' && ss[j].charAt(i) <= 'z')|| ss[j].charAt(i) == '-' || ss[j].charAt(i) == '\'') {
                    words[j] += ss[j].charAt(i);
                }
            }
        }
        for (String word : words) {
            map.put(word, map.containsKey(word) ? map.get(word) + 1 : 1);
            lineMap.put(word, lineMap.containsKey(word) ? lineMap.get(word) + 1 : 1);
        }

        for(Map.Entry<String,Integer> entry:lineMap.entrySet()){
            log.info(entry.getKey() +"  :  "+  entry.getValue());
        }
        return words.length;
    }

//    //内部类
//    class ValueComparator implements Comparator<String> {
//        Map<String, Integer> base;
//        public ValueComparator(Map<String, Integer> base) {
//            this.base = base;
//        }
//
//        public int compare(String a, String b) {
//            if (base.get(a) >= base.get(b)) {
//                return -1;
//            } else {
//                return 1;
//            }
//        }
//    }

}
