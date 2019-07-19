import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class CommonsIoClassTest {

    Long charNum=0L;
    @Test
    public void readFile() {
        Long lineNum=0L;
        Set<String> set= new HashSet<>();
        //生成map
        Map<String,Integer> map=new HashMap<>();
        //修改比较器
        ValueComparator bvcDESC =  new ValueComparator(map);
        //放入排序树排序
        TreeMap<String,Integer> sorted_map_Desc = new TreeMap<String,Integer>(bvcDESC);
        File file= new File("D:/实习文件/week1_file/TheOldManAndTheSea.txt");
        LineIterator lineIterator=null;
        try{
            lineIterator= FileUtils.lineIterator(file,"UTF-8");
            while(lineIterator.hasNext()){
                lineNum++;
//                System.out.println(lineIterator.nextLine());
                String line=lineIterator.nextLine();
                if (line.equals("")) {
                    System.out.println("*********************第"+lineNum+"行单词量：0************************");
                    continue;
                }
                //去除空白符
                line=line.toLowerCase();
                String[] ss = line.split("\\s+");
                //去除无关字符
                String[] words = deleteStr(ss);
                for (String word : words) {
//                    System.out.println(word);
                    if (set.add(word)) {
                        map.put(word, 1);
                    } else {
                        Integer time = map.get(word) + 1;
                        map.put(word, time);
                    }
                }
                System.out.println("*********************第"+lineNum+"行单词量："+words.length+"************************");
            }
            sorted_map_Desc.putAll(map);
            //打印不重复单词个数和字符个数
//            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Integer number=set.size();
            System.out.println("阅读文章的最少单词量是：");
            System.out.println(number);
            System.out.println("文章字符总数是：");
            System.out.println(charNum);
            System.out.println("出现的单词及个数：");
            for (Map.Entry<String,Integer> entry : sorted_map_Desc.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
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
                if ((str[j].charAt(i) >= 'a' && str[j].charAt(i) <= 'z')|| str[j].charAt(i) == '-' || str[j].charAt(i) == 39) {
                    words[j] += str[j].charAt(i);
                }
            }
        }
        return words;
    }
}