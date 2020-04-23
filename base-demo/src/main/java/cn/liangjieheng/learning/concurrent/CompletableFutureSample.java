package cn.liangjieheng.learning.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureSample {

    public static void main(String[] args) {
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1：洗水壶");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T1：烧开水");
            sleep(10, TimeUnit.SECONDS);
        });
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2：洗茶壶");
            return null;
        });
        CompletableFuture<Void> task3 = task1.thenCombine(task2, (x, y) -> {
            System.out.println("T3：冲茶");
            return null;
        });
        System.out.println(task3.join());
    }

    private static void sleep(long t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
