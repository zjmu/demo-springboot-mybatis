import com.ucar.train.week1.zhiming.he01.ch01.Statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
public class Test {
    @org.junit.Test
    public void test(){
        String path = Statistics.class.getResource("/").getPath()+"TheOldManAndTheSea.txt";
        Statistics statistics = new Statistics();
        statistics.statistics(path);
        System.out.println("单词总量:"+statistics.sum);
        System.out.println("最低词汇量:"+statistics.minWord);
        //输出单词频数
        System.out.println("单词出现的次数：");
        List<Map.Entry<String,Integer>> list = new ArrayList<>(statistics.frequency.entrySet());
        Collections.sort(list,(o1, o2)->o2.getValue().compareTo(o1.getValue()));
        list.forEach((el)->System.out.println(el));
        //输出每行的单词数
        System.out.println("每行的单词数:");
        statistics.numOfLine.forEach((i,j)->System.out.println(i+":"+j));
    }
}
