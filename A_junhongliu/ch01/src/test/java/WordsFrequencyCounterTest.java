
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WordsFrequencyCounterTest {

    private Map<String,Integer> rightMap = new HashMap<String, Integer>();

    private Map<String,Integer> resultMap =null;

    private WordsFrequencyCounter counter;

    // 定义测试文件
    private File testFile = null;

    @Before
    public void init() {
        counter = new WordsFrequencyCounter();
        testFile = new File("src\\test\\resources\\testFile.txt");

    }

    /**
     * 对获取到的 单词-频率 对map的正确性进行测试
     */
    @Test
    public void generateResultMapTest() {
        counter.generateResultMap(testFile);
        rightMap.put("Michael",1);
        rightMap.put("is",1);
        rightMap.put("student",3);
        rightMap.put("who",1);
        rightMap.put("graduated",2);
        rightMap.put("from",1);
        rightMap.put("HUAQIAO",2);
        rightMap.put("UNIVERSITY",2);
        rightMap.put("at",1);
        // 获取分析结果
        resultMap = counter.resultMap;
        assertThat(rightMap, is(resultMap));
    }

    /**
     * todo 验证排序的正确性
     */
    @Test
    public void sortTheMapTest(){

    }


}
