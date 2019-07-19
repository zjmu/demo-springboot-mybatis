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
        System.out.println("��������:"+statistics.sum);
        System.out.println("��ʹʻ���:"+statistics.minWord);
        //�������Ƶ��
        System.out.println("���ʳ��ֵĴ�����");
        List<Map.Entry<String,Integer>> list = new ArrayList<>(statistics.frequency.entrySet());
        Collections.sort(list,(o1, o2)->o2.getValue().compareTo(o1.getValue()));
        list.forEach((el)->System.out.println(el));
        //���ÿ�еĵ�����
        System.out.println("ÿ�еĵ�����:");
        statistics.numOfLine.forEach((i,j)->System.out.println(i+":"+j));
    }
}
