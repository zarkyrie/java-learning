package cn.liangjieheng.learning.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogTest {

    private static final Logger LOGGER = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        LOGGER.error("test info");

        List<String> list = Arrays.asList("a","b","c");

        List<String> list1 = new ArrayList<>(list);

        list1.remove(1);

        System.out.println(list);
        System.out.println(list1);

        System.out.println(test());
    }

    public static int test(){
        try{
            return 1;
        }finally {
            System.out.println("hello");
        }
    }
}
