import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LineCounterTest {
    private Map<Character,Integer> rightMap = new HashMap<Character, Integer>();

    private Map<Character,Integer> resultMap =null;

    private LineCounter lineCounter;

    // 定义测试文件
    private File testFile = null;

    @Before
    public void init() {
        lineCounter = new LineCounter();
        testFile = new File("src\\test\\resources\\testFile.txt");
    }

    /**
     * 暂时只是输出数据
     * todo 创建expected用例进行自动测试
     *
     */
    @Test
    public void generateResultMapTest(){
        lineCounter.countLine(testFile);
    }

}
