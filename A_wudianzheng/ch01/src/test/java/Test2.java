import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;
@Slf4j
public class Test2 {
    @Test
    public void test(){
        log.info("{},{}","今天的日期是：",new Date());
    }
}
