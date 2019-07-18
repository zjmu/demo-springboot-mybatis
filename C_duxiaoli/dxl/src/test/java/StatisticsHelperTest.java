import com.ucar.train.week1.xiaoli.du.ch01.StatisticsHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ：DZGodly
 * @date ：Created in 2019/7/17 17:06
 */
@Slf4j
public class StatisticsHelperTest {

    private long start;

    @Before
    public void start() {
        start = System.nanoTime();
    }

    @After
    public void printTime() {
        long end = System.nanoTime();
        log.info("程序执行时长：" + (end - start) / 1000_000 + " 毫秒");
    }

    @Test
    public void testNativeAPI(){
        StatisticsHelper helper = new StatisticsHelper("src/main/resources/TheOldManAndTheSea.txt",false);
        helper.printResult();
    }

    @Test
    public void testCommonsIO(){
        StatisticsHelper helper = new StatisticsHelper("src/main/resources/TheOldManAndTheSea.txt",true);
        helper.printResult();
    }
}
