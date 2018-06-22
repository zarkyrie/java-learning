package cn.liangjieheng.learning.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchSample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new FirstWorker(countDownLatch));
            t.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new SecondWorker(countDownLatch));
            t.start();
        }

        while (countDownLatch.getCount() != 1) {
            Thread.sleep(10000);
        }

        System.out.println("main execute:"+countDownLatch.getCount());
        countDownLatch.countDown();
    }


}

class FirstWorker implements Runnable {

    private CountDownLatch countDownLatch;

    public FirstWorker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("FirstWorker execute:" + countDownLatch.getCount());
        countDownLatch.countDown();
    }
}

class SecondWorker implements Runnable {

    private CountDownLatch countDownLatch;

    public SecondWorker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println("SecondWorker execute:" + countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}