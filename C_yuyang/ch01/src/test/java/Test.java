import com.ucar.train.week1.yu.yang03.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.*;

public class Test {
    @org.junit.Test
    public void test() {
        Statistics s = new Statistics();
//        String filePath = Statistics.class.getResource("/").getPath() + "TheOldManAndTheSea.txt";
        String filePath = "src/main/resources/TheOldManAndTheSea.txt";
        s.statistics(filePath);
        System.out.println(String.format("%-15s", "单词量") + s.sumOfWords);
        System.out.println(String.format("%-15s", "词汇量") + s.minWords);
        System.out.println("单词在文中出现的次数：");
        List<Map.Entry<String, Integer>> list = new ArrayList<>(s.numOfWord.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (Map.Entry<String, Integer> stringIntegerEntry : list) {
            System.out.println(String.format("%-15s", (stringIntegerEntry.getKey())) + "   " + stringIntegerEntry.getValue());
        }
        System.out.println("每行的单词数:");
        s.numOfLine.forEach((i, j) -> System.out.println(i + ":" + j));

    }

}
