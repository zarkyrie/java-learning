package cn.liangjieheng.learning.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSample {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        Fib fib = new Fib(6);
        Integer result = pool.invoke(fib);
        System.out.println(result);
    }

    static class Fib extends RecursiveTask<Integer> {

        private int n;

        Fib(int param) {
            this.n = param;
        }

        @Override
        protected Integer compute() {
            if (n == 1 || n == 2) {
                return 1;
            }
            Fib fib1 = new Fib(n - 1);
            Fib fib2 = new Fib(n - 2);
            //把fib2交给worker执行
            fib2.fork();
            return fib1.compute() + fib2.join();
        }
    }
}
