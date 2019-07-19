import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ucar.train.week1.rongkun.chen.ch01.CharacterCount;
import org.junit.Test;

public class CharacterCountTest {
    CharacterCount tar = new CharacterCount();

    @Test
    public void testNavativeAPI() throws IOException {
        //以NativeAPI方式读文件所有出现的单词、字符总数
        tar.readSelectedFile(tar.filePath, true);
        tar.launch();
    }

    @Test
    public void testCommosIO() throws IOException {
        //以NativeAPI方式读文件所有出现的单词、字符总数
        tar.readSelectedFile(tar.filePath, false);
        tar.launch();
    }
}
