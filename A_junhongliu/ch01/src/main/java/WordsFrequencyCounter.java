import java.io.*;
import java.util.*;

public class WordsFrequencyCounter {
    // 用于记录统计结果
    Map<String,Integer> resultMap = new HashMap<String, Integer>();
    // LinkedList用于排序
    List<Map.Entry<String, Integer>> resultLinkedList = null;
    public void countFrequency(File file){
        generateResultMap(file);
        sortTheMap();
        displayResult();
    }

    public void sortTheMap(){
        // 创建LinkedList
        this.resultLinkedList = new LinkedList<Map.Entry<String, Integer>>();
        resultLinkedList.addAll(resultMap.entrySet());
        // 利用Collections的sort方法对linkedList进行排序
        Collections.sort(resultLinkedList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry obj1, Map.Entry obj2) {
                // 按照频次从高到低排序
                if (Integer.parseInt(obj1.getValue().toString()) < Integer
                        .parseInt(obj2.getValue().toString()))
                    return 1;
                if (Integer.parseInt(obj1.getValue().toString()) == Integer
                        .parseInt(obj2.getValue().toString()))
                    return 0;
                else
                    return -1;
            }
        });
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
                // 用正则匹配划分单词
                String[] str = lineString.split("(\\s+\\W+)|[\\s+\\W+]");
                for (int i = 0; i < str.length; i++) {
                    // 如果map中已有该词则将出现频次+1
                    if (resultMap.containsKey(str[i])) {
                        resultMap.put(str[i], resultMap.get(str[i]) + 1);
                    } else {
                        // 第一次出现则将单词出现频次设为1加入map
                        resultMap.put(str[i], 1);
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
        Iterator<Map.Entry<String, Integer>> ite = resultLinkedList.iterator();
        while(ite.hasNext()) {
            Map.Entry<String, Integer> maps = ite.next();
            System.out.println("word: "+maps.getKey() + "\t\t" +"frequency: " + maps.getValue());
        }
    }
}
