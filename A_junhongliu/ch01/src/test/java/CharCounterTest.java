import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CharCounterTest {
    private Map<Character,Integer> rightMap = new HashMap<Character, Integer>();

    private Map<Character,Integer> resultMap =null;

    private CharCounter counter;

    // 定义测试文件
    private File testFile = null;

    @Before
    public void init() {
        counter = new CharCounter();
        testFile = new File("src\\test\\resources\\testFile.txt");
    }

    /**
     * todo 创建expected用例
     */
    @Test
    public void generateResultMapTest(){
        counter.countFrequency(testFile);
    }
}
