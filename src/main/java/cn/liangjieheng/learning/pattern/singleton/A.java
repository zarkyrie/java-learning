package cn.liangjieheng.learning.pattern.singleton;

/**
 * 饿汉式，线程安全
 */
public class A {

    private static final A a = new A();

    public static A getA() {
        return a;
    }
}
