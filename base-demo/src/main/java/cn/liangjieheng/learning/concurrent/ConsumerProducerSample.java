package cn.liangjieheng.learning.concurrent;

import java.util.concurrent.ArrayBlockingQueue;

public class ConsumerProducerSample {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        Thread t1 = new Thread(new Producer(arrayBlockingQueue));
        t1.start();
        Thread t2 = new Thread(new Consumer(arrayBlockingQueue));
        t2.start();
    }

    static class Producer implements Runnable {

        ArrayBlockingQueue<String> arrayBlockingQueue;

        public Producer(ArrayBlockingQueue<String> arrayBlockingQueue) {
            this.arrayBlockingQueue = arrayBlockingQueue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    arrayBlockingQueue.put("message:" + i);
                }
                arrayBlockingQueue.put("byebye");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }


        }
    }

    static class Consumer implements Runnable {

        ArrayBlockingQueue<String> arrayBlockingQueue;

        public Consumer(ArrayBlockingQueue<String> arrayBlockingQueue) {
            this.arrayBlockingQueue = arrayBlockingQueue;
        }

        @Override
        public void run() {
            try {
                String msg;
                while (!"byebye".equalsIgnoreCase(msg = arrayBlockingQueue.take())) {
                    System.out.println(msg);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }
}
