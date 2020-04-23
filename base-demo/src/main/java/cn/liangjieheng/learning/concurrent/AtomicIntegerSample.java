package cn.liangjieheng.learning.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerSample {

    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger();
        System.out.println(a.incrementAndGet());
    }
}
