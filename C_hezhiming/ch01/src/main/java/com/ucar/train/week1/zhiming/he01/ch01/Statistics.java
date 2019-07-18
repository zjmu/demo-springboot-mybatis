package com.ucar.train.week1.zhiming.he01.ch01;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Statistics {
    public int sum;  //�ַ�����
    public TreeMap<Integer,Integer> numOfLine; //ÿ�еĵ�����
    public int minWord; //��ʹʻ���
    public TreeMap<String,Integer> frequency; //���ʳ���Ƶ��
    public int row; //����

    public Statistics(){
        numOfLine = new TreeMap<>();
        frequency = new TreeMap<>();
        row=1;
    }
    //���ļ��ж�ȡ����
    private List<String> readWordFromFile(String path){
        List<String> list = new ArrayList<String>();
        FileInputStream is = null;
        BufferedReader br = null;
        try {
            is = new FileInputStream(path);
            br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String str;
            while ((str = br.readLine()) != null) {
                if("".equals(str)){
                    numOfLine.put(row++,0);
                    continue;
                }else {
                    String[] num = str.split("\\s");
                    numOfLine.put(row++,num.length);
                }
                list.add(str);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    //ͳ��
    public void statistics(String path){
        List<String> lines = readWordFromFile(path);
        lines.forEach((line)->{
            StringTokenizer stringTokenizer = new StringTokenizer(line,",!' \"��.;(��-)123456789��?0���������ռ���:����?");
            while (stringTokenizer.hasMoreElements()){
                String str = stringTokenizer.nextToken();
                sum++;
                frequency.put(str,(frequency.get(str)==null?0:frequency.get(str))+1);
            }
        });
        //ͳ����С������
        minWord = frequency.size();
    }
}
