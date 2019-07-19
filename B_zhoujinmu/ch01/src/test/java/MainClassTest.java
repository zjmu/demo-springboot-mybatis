import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.*;

@Slf4j
public class MainClassTest {
    Long charNum=0L;
    @Test
    public void main() {
        Set<String> set= new HashSet<>();
        //生成map
        Map<String,Integer> map=new HashMap<>();
        //修改比较器
        ValueComparator bvcDESC =  new ValueComparator(map);
        //放入排序树排序
        TreeMap<String,Integer> sorted_map_Desc = new TreeMap<String,Integer>(bvcDESC);

        FileInputStream fileInputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        try{
            String relativelyPath = System.getProperty("user.dir");
            String pathName=relativelyPath+"/src/main/resources/TheOldManAndTheSea.txt";
            fileInputStream=new FileInputStream(new File(pathName));
            inputStreamReader=new InputStreamReader(fileInputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            while((line=bufferedReader.readLine())!=null) {
                if (line.equals("")) {
                    continue;
                }
                //去除空白符
                line=line.toLowerCase();
                String[] ss = line.split("\\s+");
                //去除无关字符
                String[] words = deleteStr(ss);
                for (String word : words) {
                    System.out.println(word);
                    if(set.add(word)){
                        map.put(word,1);
                    }
                    else {
                        Integer time=map.get(word)+1;
                        map.put(word,time);
                    }
                }
                //打印一行单词数
                System.out.println("******************"+words.length+"********************");
            }
            sorted_map_Desc.putAll(map);
            //打印不重复单词个数和字符个数
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Integer number=set.size();
            System.out.println(number);
            System.out.println(charNum);
            for (Map.Entry<String,Integer> entry : sorted_map_Desc.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
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

    @Test
    public void getPath(){
        String relativelyPath = System.getProperty("user.dir");
        String pathName=relativelyPath+"/src/main/resources/TheOldManAndTheSea.txt";
//        System.out.println(pathName);
        log.info(pathName);
        File file=new File(pathName);

    }
}