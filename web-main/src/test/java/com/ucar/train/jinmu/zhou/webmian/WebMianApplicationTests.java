package com.ucar.train.jinmu.zhou.webmian;

import com.ucar.train.jinmu.zhou.webmian.domain.Paragraph;
import com.ucar.train.jinmu.zhou.webmian.domain.Line;
import com.ucar.train.jinmu.zhou.webmian.mapper.ParagraphMapper;
import com.ucar.train.jinmu.zhou.webmian.mapper.LineMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebMianApplicationTests {

    @Autowired
    private ParagraphMapper paragraphMapper;

    @Autowired
    private LineMapper lineMapper;

    @Test
    public void contextLoads(){
        log.info(System.getProperty("user.dir")+"/resources/TheOldManAndTheSea.txt");
    }

    Integer lineNum=0;
    BigInteger paragraphNum=new BigInteger("0");
    Integer lineWordsNum=0;
    Integer paragraphWordsNum=0;
    Long totalWordsNum=0L;
    String paragraphContent="";
    @Test
    public void splitParagraph(){

        List<Line> lines =new ArrayList<>();
        Set<String> set= new HashSet<>();
        Map<String,Integer> map=new HashMap<>();
        ValueComparator bvcDESC =  new ValueComparator(map);
        TreeMap<String,Integer> sorted_map_Desc = new TreeMap<String,Integer>(bvcDESC);

        List<Paragraph> paragraphs =new ArrayList<>();
        Boolean isParagraph=false;

        FileInputStream fileInputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        try{
            String filePath=System.getProperty("user.dir")+"/src/main/resources/TheOldManAndTheSea.txt";
            fileInputStream=new FileInputStream(new File(filePath));
            inputStreamReader=new InputStreamReader(fileInputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            String lineContent;
            while((lineContent=bufferedReader.readLine())!=null) {
                if (lineContent.equals("")) {
                    isParagraph=true;
                    continue;
                }
                lineNum++;
                //段处理
                if(isParagraph){
                    isParagraph=false;
                    dealParagraph(lineContent,paragraphs);
                }
                paragraphContent+=lineContent;
                //处理行
                lineWordsNum=dealLine(lineNum,paragraphNum,lineContent, lines,map,set);
                paragraphWordsNum+=lineWordsNum;
                log.info("每行单词量"+lineWordsNum+"");//每行单词量
            }
            log.info("阅读最低量"+set.size());//阅读最低量
            log.info("总单词量"+totalWordsNum);//总单词量
            sorted_map_Desc.putAll(map);
            log.info("单词频率");
            for (Map.Entry<String,Integer> entry : sorted_map_Desc.entrySet()) {
                log.info(entry.getKey() + " : " + entry.getValue());
            }
            paragraphMapper.insertMore(paragraphs);
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
    @Test
    public void insertMore(){
        List<Paragraph> paragraphs =new ArrayList<>();
        Paragraph paragraph =new Paragraph();
        paragraph.setParagraphId(new BigInteger("1"));
        paragraphs.add(paragraph);
        paragraph.setParagraphId(new BigInteger("2"));
        paragraphs.add(paragraph);
        paragraph.setParagraphId(new BigInteger("4"));
        paragraphs.add(paragraph);
        paragraph.setParagraphId(new BigInteger("5"));
        paragraphs.add(paragraph);

        paragraphMapper.insertMore(paragraphs);
    }

    public void dealParagraph(String lineContent,List<Paragraph>paragraphs){
        for(int i=0;i<lineContent.length();i++){
            //上一行空白且该行第一个字母大写则是一段
            if(('a'<=lineContent.charAt(i)&&lineContent.charAt(i)<='z')||('A'<=lineContent.charAt(i)&&lineContent.charAt(i)<='Z')){
                if('A'<=lineContent.charAt(i)&&lineContent.charAt(i)<='Z'){
                    totalWordsNum+=paragraphWordsNum;
                    log.info("每段单词量"+paragraphWordsNum+"");//每段单词量
                    paragraphWordsNum=0;
                    paragraphNum=paragraphNum.add(new BigInteger("1"));
                    Paragraph paragraph=new Paragraph();
                    Date date=new Date();
                    java.sql.Date createTime=new java.sql.Date(date.getTime());
                    paragraph.setCreateTime(createTime);
                    paragraph.setWordsNum(paragraphWordsNum);
                    paragraph.setParagraphId(paragraphNum);
                    paragraph.setDescription(paragraphContent);
                    paragraphs.add(paragraph);
                    paragraphContent=lineContent;
                }
                break;
            }
        }
    }

    public Integer dealLine(Integer lineNum, BigInteger paragraphNum, String lineContent, List<Line> lines, Map<String,Integer>map, Set<String>set){
        //将行信息存储
        Line line=new Line();
        line.setLineId(lineNum);
        line.setParagraphId(paragraphNum);
        line.setText(lineContent);
        lines.add(line);
        //处理该行单词，放回一行单词量
        return deleteStr(lineContent,map,set);
    }



    public Integer deleteStr(String lineContent,Map<String,Integer> map,Set<String> set){
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
            set.add(word);
            map.put(word, map.containsKey(word) ? map.get(word) + 1 : 1);
        }
        return words.length;
    }

    class ValueComparator implements Comparator<String> {
        Map<String, Integer> base;
        public ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
