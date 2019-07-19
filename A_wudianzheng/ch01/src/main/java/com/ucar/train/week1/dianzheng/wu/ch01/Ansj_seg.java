package com.ucar.train.week1.dianzheng.wu.ch01;

import lombok.extern.slf4j.Slf4j;
import sun.management.snmp.util.MibLogger;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
    public class Ansj_seg {
        Pattern p = Pattern.compile("[, . ; ! ? ]");

    public String readAndReturnString(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        HashMap<String,Integer> hashMap = null;
        StringBuilder str = new StringBuilder();
        String read_Message;
        int sum = 0;
        int line = 1;
        while((read_Message =bufferedReader.readLine())!=null){
            Matcher m = p.matcher(read_Message);
            hashMap = getMap(read_Message);
            printMap(hashMap);
            System.out.println("第"+line+"行共有"+hashMap.size()+"个单词");
            str.append(read_Message);
            sum+=read_Message.length();
            line++;
        }
        System.out.println("----------------------->共有"+sum+"字符");
        return str.toString();
    }
    public HashMap<String,Integer> getMap(String str){
        String[] strings = p.split(str);
        HashMap<String,Integer> hashMap = new HashMap<>();
        for(String a:strings){
            for(int i = 0;i<a.length();i++){
                char c = a.charAt(i);
                if(c<48||(c>57&&c<65)||(c>90&&c<97)||(c>122)){
                    a = a.substring(0,i)+a.substring(i+1,a.length());
                }
            }
            if(!hashMap.containsKey(a)&& !a.equals(" ")){
                hashMap.put(a,1);
            }else{
                hashMap.put(a,hashMap.get(a)+1);
            }
        }
        return hashMap;
    }
    public void printMap(HashMap<String,Integer> hashMap){
        for(Map.Entry<String,Integer> entry:hashMap.entrySet()){
            System.out.println(entry.getKey()+"--------->"+entry.getValue());
        }
    }

}
