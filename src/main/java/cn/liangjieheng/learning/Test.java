package cn.liangjieheng.learning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Women extends Human {

    }

    public void show(Human human) {
        System.out.println("hello human");
    }

    public void show(Man man) {
        System.out.println("hello man");
    }

    public void show(Women women) {
        System.out.println("hello women");
    }

    public static void main(String[] args) {
//        Human man = new Man();
//        Human women = new Women();
//
//        Test t = new Test();
//        t.show(man);
//        t.show(women);

//        Map<String,String> map = new HashMap<>();
//        map.put("11","1");
//        System.out.println(map.toString());

        Integer i = 1000;
        Integer x = 1000;

        System.out.println(i == x);
    }


}




