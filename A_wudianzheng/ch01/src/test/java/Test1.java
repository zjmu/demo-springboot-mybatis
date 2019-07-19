import com.ucar.train.week1.dianzheng.wu.ch01.Ansj_seg;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Test1 {
    @Test
    public void test() throws IOException {
        Ansj_seg s = new Ansj_seg();
        File file = new File(s.getClass().getResource("/TheOldManAndTheSea.txt").getPath());
        String str = s.readAndReturnString(file);
        HashMap<String,Integer> hashMap = s.getMap(str);
        System.out.println("需要的词汇量为------------------------>"+hashMap.size());
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(hashMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for(Map.Entry<String,Integer> entry:list){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
