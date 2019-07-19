
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WordsFrequencyCounterTest {

    private Map<String,Integer> rightMap = new HashMap<String, Integer>();

    private Map<String,Integer> resultMap = null;

    private WordsFrequencyCounter counter = null;

    private List<Map.Entry<String, Integer>> rightLinkedList = null;

    private List<Map.Entry<String, Integer>> resultLinkedList = null;

    // 定义测试文件
    private File testFile = null;

    @Before
    public void init() {
        rightLinkedList = new LinkedList<>();
        counter = new WordsFrequencyCounter();
        testFile = new File("src\\test\\resources\\testFile.txt");
        // 方法执行
        counter.generateResultMap(testFile);
        counter.sortTheMap();
        counter.displayResult();
        // expected 结果编写
        rightMap.put("student",3);
        rightMap.put("HUAQIAO",2);
        rightMap.put("graduated",2);
        rightMap.put("UNIVERSITY",2);
        rightMap.put("Michael",1);
        rightMap.put("is",1);
        rightMap.put("who",1);
        rightMap.put("from",1);
        rightMap.put("at",1);
        rightLinkedList.addAll(rightMap.entrySet());
        // 获取执行结果
        resultMap = counter.resultMap;
        resultLinkedList = counter.resultLinkedList;
    }

    /**
     * 对获取到的 单词-频率 对map的正确性进行测试
     */
    @Test
    public void generateResultMapTest() {
        // 获取分析结果
        assertThat(rightMap, is(resultMap));
    }

    /**
     * todo 验证排序的正确性
     */
    @Test
    public void sortTheMapTest(){
//        counter.generateResultMap(testFile);
//        counter.sortTheMap();
//        assertThat(rightLinkedList,is(resultLinkedList));
    }


}
