import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.apache.commons.io.FileUtils;
public class Ch01 {
    public static void main(String[] args) throws IOException {

        String Path=Ch01.class.getResource("/").getPath()+"TheOldManAndTheSea.txt";
        File file=new File(Path);
        List<Word> wordList= new ArrayList<Word>();
        Set<String> hashSet=new HashSet();

        //采用Jdk原生API方式
        new Ch01().readFileByJdkIO(Path);
        //采用common-io方式
        new Ch01().readFileByComonIO(file,wordList,hashSet);
    }
    public void readFileByComonIO(File file,List<Word> wordList,Set<String> hashSet) throws IOException {
        String string = FileUtils.readFileToString(file, "UTF-8");
        String cutting = string.replaceAll("\\pP|\\pS|\\pC|\\pN", "");
        String[] split = cutting.toLowerCase().trim().split("\\s+");

        getWordList(wordList,hashSet,split);
        needLeastVocabulary(wordList);
        numberOfAllChar(wordList);
        sortWithTimes(wordList);
        printWithDescendingWordTimes(wordList);
    }
    public String[] replaceAllChar(String string)
    {
        /*
         * 替换所有特殊字符
         *小写 p 是 property 的意思，表示 Unicode 属性，用于 Unicode 正表达式的前缀
         *  P表示标点字符 S表示符号  C表示其他字符  N表示数字
         * */
        String cutting = string.replaceAll("\\pP|\\pS|\\pC|\\pN", "");
        String[] split = cutting.toLowerCase().trim().split("\\s+");
        return split;
    }
    public void readFileByJdkIO(String fileName) throws IOException {

        String line;
        //统计行数
        Integer LineNumber=1;
        List<Word> wordList= new ArrayList<Word>();
        Set<String> hashSet=new HashSet();
        BufferedReader br=new BufferedReader(new FileReader(fileName));
        try {
            while ((line=br.readLine())!=null){
                //按行读取
                String[] split = replaceAllChar(line);
                System.out.println("第"+LineNumber+"行的数据单词量为："+split.length);
                getWordList(wordList,hashSet,split);
                LineNumber++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            br.close();
        }
        needLeastVocabulary(wordList);
        numberOfAllChar(wordList);
        sortWithTimes(wordList);
        printWithDescendingWordTimes(wordList);
    }
    public List<Word> getWordList(List<Word> wordList,Set<String> hashSet,String[]split)
    {
        for (int index=0,leng=split.length;index<leng;index++){
            if (hashSet.contains(split[index].trim())==false)
            {
                //word不存在的时候,创建一个word对象，times初始化为0
                //把word对象加入arraylist，把该单词加入hashset
                Word word=new Word(split[index],0);
                wordList.add(word);
                hashSet.add(split[index].trim());
            }
            //存在的时候，遍历wordList，找到split[index]=word.name相等的对象，times+1
            for (int subscript=0,length=wordList.size();subscript<length;subscript++)
            {
                Word word=wordList.get(subscript);
                if(word.getName().equals(split[index])) {
                    word.setTimes(word.getTimes()+1);
                }
            }
        }
        return wordList;
    }
    public void needLeastVocabulary(List<Word> wordList)
    {
        System.out.println("阅读这篇小说的最低的词汇量是:"+wordList.size());
    }
    public void numberOfAllChar(List<Word> wordList)
    {
        long number=0;
        for (Word word : wordList) {
            number+=word.getTimes()*word.getName().length();
        }
        System.out.println("整篇小说的所有的字符数量为:"+number);
    }
    public void printWithDescendingWordTimes(List<Word> wordList)
    {
        System.out.println("单词"+"          "+"本篇文章出现次数");
        for (Word word : wordList) {
            System.out.println(word.getName()+"         \t     "+word.getTimes());
        }
    }
    public void sortWithTimes(List<Word> wordList)
    {
        //按单词出现频率降序打印
        Collections.sort(wordList, new Comparator<Word>() {
            @Override
            public int compare(Word word1, Word word2) {
                return word1.getTimes()>word2.getTimes()?-1:(word1.getTimes().equals(word2.getTimes())?0:1);
            }
        });
    }
}
