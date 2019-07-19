import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统计每种字符
 */
public class CharCounter extends Counter{
    // 用于记录统计结果
    Map<Character,Integer> resultMap = new HashMap<Character, Integer>();
    public void countFrequency(File file){
        generateResultMap(file);
        displayResult();
    }

    public void generateResultMap(File file){
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
                char[] characters = lineString.toCharArray();
                for(char currChar : characters){
                    if(resultMap.containsKey(currChar)){
                        // 如果当前字符在map中则频次+1
                        resultMap.put(currChar,resultMap.get(currChar)+1);
                    }else{
                        // 否则初始化频次为1
                        resultMap.put(currChar,1);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        // 去除空串
        resultMap.remove("");
    }

    public void displayResult(){
        Iterator<Map.Entry<Character, Integer>> ite = resultMap.entrySet().iterator();
        while(ite.hasNext()) {
            Map.Entry<Character, Integer> maps = ite.next();
            System.out.println("word: "+maps.getKey() + "\t" +"frequency: " + maps.getValue());
        }
    }
}
