package cn.liangjieheng.learning.pattern.singleton;

/**
 * 静态内部类实现
 */
public class C {

    private static class CHolder {
        private static C CHolder = new C();
    }

    public static C getC() {
        return CHolder.CHolder;
    }
}
