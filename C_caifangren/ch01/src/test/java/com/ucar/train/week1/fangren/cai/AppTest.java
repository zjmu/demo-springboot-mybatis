package com.ucar.train.week1.fangren.cai;


import com.ucar.train.week1.fangren.cai.function.CharNum;
import com.ucar.train.week1.fangren.cai.function.Frequency;
import com.ucar.train.week1.fangren.cai.function.LineWordCount;
import com.ucar.train.week1.fangren.cai.function.WordKind;
import com.ucar.train.week1.fangren.cai.utils.ReadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest 
{
    final String filename = "TheOldManAndTheSea.txt";
    /**
     * Rigorous Test :-)
     */
    @Test
    public void frequency(){
        Frequency.printWordsFrequency(filename);
    }

    @Test
    public void charNumTest(){
        CharNum.charNum(filename);
    }

    @Test
    public void lineWordCount(){
        LineWordCount.lineWordCount(filename);
    }

    @Test
    public void wordKind(){
        WordKind.wordKind(filename);
    }

    @Test
    public void testContent(){
        log.debug(String.valueOf(ReadUtil.fileReader(filename).equals(ReadUtil.commonsFileReader(filename))));
    }
}
