package com.ucar.train.week1.jinmu.zhou.ch01;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MainClass {

    public static void main(String[] args) {
        CommonsIoClass commonsIoClass= new CommonsIoClass();
        JavaClass javaClass=new JavaClass();
       log.info("输入1，按CommonsIO方式处理，输入其他数字按java方式处理");
        Scanner sc=new Scanner(System.in);
        int type=sc.nextInt();
        switch (type){
            case 1:commonsIoClass.readFile();break;
            default:javaClass.readFile();break;
        }

    }

}
