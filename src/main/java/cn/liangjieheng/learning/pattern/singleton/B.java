package cn.liangjieheng.learning.pattern.singleton;

/**
 * 懒汉式，延迟加载，线程安全
 */
public class B {

    private static volatile B b;

    public static B getB() {
        if (b == null) {
            synchronized (B.class) {
                if (b == null) {
                    b = new B();
                }
            }
        }
        return b;
    }
}
