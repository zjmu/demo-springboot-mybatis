import java.io.File;


public class Main {


    public static void main(String[] args){
        // todo 文件读取方式有多种可以进行尝试 总结一下
        File file = new File("src\\main\\resources\\TheOldManAndTheSea.txt");
        if(file==null){
            System.out.println("file == null");
        }
        CharCounter charCounter = new CharCounter();
        WordsCounter wordsCounter = new WordsCounter();
        LineCounter lineCounter = new LineCounter();
        WordsFrequencyCounter frequencyCounter = new WordsFrequencyCounter();
        frequencyCounter.countFrequency(file);

    }
}
