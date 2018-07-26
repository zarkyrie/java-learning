package cn.liangjieheng.learning.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSortSample {

    private static List<Integer> quickSort(List<Integer> list) {
        if (list.size() < 2) {
            return list;
        } else {
            Integer pivot = list.get(0);
            List<Integer> less = new ArrayList<>();
            List<Integer> greater = new ArrayList<>();
            for (Integer i : list) {
                if (i < pivot) {
                    less.add(i);
                } else if (i > pivot) {
                    greater.add(i);
                }
            }
            List<Integer> results = new ArrayList<>();
            results.addAll(quickSort(less));
            results.add(pivot);
            results.addAll(quickSort(greater));
            return results;
        }
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>(Arrays.asList(3, 1, 5, 9, 11, 7));
        List<Integer> list = quickSort(l);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
