package cn.liangjieheng.learning.thread;


/**
 * 测试一个实例在两个线程环境下，实例变量不安全
 */
public class Test implements Runnable {

    int i;

    public void add(int a) {
        i = a;
    }

    private void show() {
        System.out.println(i);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        show();
    }
}

class A {
    public static void main(String[] args) {
        Test t = new Test();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t.add(1);
        t1.start();
        t.add(2);
        t2.start();
    }
}