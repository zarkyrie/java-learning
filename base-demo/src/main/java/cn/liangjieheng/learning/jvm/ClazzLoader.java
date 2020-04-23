package cn.liangjieheng.learning.jvm;

import java.io.File;
import java.net.URL;

public class ClazzLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(classLoader.toString());
    }

}
