package com.ucar.train.week1.jinmu.zhou.ch01;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Slf4j
public class CommonsIoClass{

    Long charNum=0L;
    public void readFile(){
        Long lineNum=0L;
        Set<String> set= new HashSet<>();
        Map<String,Integer> map=new HashMap<>();
        ValueComparator bvcDESC =  new ValueComparator(map);
        TreeMap<String,Integer> sorted_map_Desc = new TreeMap<String,Integer>(bvcDESC);
        //获取文件相对路径
        String relativelyPath = System.getProperty("user.dir");
        String pathName=relativelyPath+"/src/main/resources/TheOldManAndTheSea.txt";
        File file= new File(pathName);
        LineIterator lineIterator=null;
        try{
            lineIterator= FileUtils.lineIterator(file,"UTF-8");
            while(lineIterator.hasNext()){
                lineNum++;
                String line=lineIterator.nextLine();
                if (line.equals("")) {
                    log.info("*********************第"+lineNum+"行单词量：0************************");
                    continue;
                }
                line=line.toLowerCase();
                String[] ss = line.split("\\s+");
                String[] words = deleteStr(ss);
                for (String word : words) {
                    if (set.add(word)) {
                        map.put(word, 1);
                    } else {
                        Integer time = map.get(word) + 1;
                        map.put(word, time);
                    }
                }
                log.info("*********************第"+lineNum+"行单词量："+words.length+"************************");
            }
            sorted_map_Desc.putAll(map);
            Integer number=set.size();
            log.info("阅读文章的最少单词量是：");
            log.info(number.toString());
            log.info("文章字符总数是：");
            log.info(charNum.toString());
            log.info("出现的单词及个数：");
            for (Map.Entry<String,Integer> entry : sorted_map_Desc.entrySet()) {
                log.info(entry.getKey() + " : " + entry.getValue());
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                lineIterator.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    public String[] deleteStr(String[] str){
        String[] words=new String[str.length];
        for (int j=0;j<str.length;j++){
            words[j]="";
            charNum+=str[j].length();
            for (int i=0;i<str[j].length();i++) {
                if ((str[j].charAt(i) >= 'a' && str[j].charAt(i) <= 'z')|| str[j].charAt(i) == '-' || str[j].charAt(i) == '\'') {
                    words[j] += str[j].charAt(i);
                }
            }
        }
        return words;
    }

}
