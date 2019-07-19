import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

public class WordsCounter {
    private static Logger logger = Logger.getLogger(LineCounter.class);
    // 用于记录统计结果
    private Set<String> resultSet = new HashSet<String>();
    public void countWords(File file){
        generateResultSet(file);
        displayResult();
    }
    public void generateResultSet(File file){
        // 一行字符串
        String lineString = null;
        // 创建文件输入流
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 创建bufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            while((lineString = reader.readLine()) != null){
                // 去除串中的空白部分
                lineString = lineString.trim();
                // 用正则匹配划分单词
                String[] words = lineString.split("(\\s+\\W+)|[\\s+\\W+]");
                resultSet.addAll(Arrays.asList(words));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        // 去除空串
        resultSet.remove("");
    }
    public void displayResult(){
        logger.info(String.format("本文词汇量: %s",resultSet.size()));
    }

}
