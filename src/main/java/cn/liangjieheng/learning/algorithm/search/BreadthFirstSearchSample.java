package cn.liangjieheng.learning.algorithm.search;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 广度优先搜索算法实现
 * 搜索距离最短路径的目标
 */
public class BreadthFirstSearchSample {

    private static void breadthFirstSearch(Map<String, String[]> target) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(target.keySet().size());
        putIntoQueue(queue, target.get("you"));
        List<String> searched = new ArrayList<>();
        while (!queue.isEmpty()) {
            String person = queue.poll();
            if (!searched.contains(person)) {
                if (personIsSeller(person)) {
                    System.out.println(person + " is a mango seller");
                } else {
                    putIntoQueue(queue, target.get(person));
                    searched.add(person);
                }
            }
        }
    }

    private static Map<String, String[]> buildMap() {
        Map<String, String[]> map = new HashMap<>();
        map.put("you", new String[]{"alice", "bob", "claire"});
        map.put("alice", new String[]{"peggy"});
        map.put("claire", new String[]{"thom", "jonny"});
        map.put("anuj", new String[]{});
        map.put("peggy", new String[]{});
        map.put("thom", new String[]{});
        map.put("jonny", new String[]{});
        return map;
    }

    private static void putIntoQueue(ArrayBlockingQueue<String> queue, String[] strs) {
        if (strs != null) {
            queue.addAll(Arrays.asList(strs));
        }
    }

    private static boolean personIsSeller(String name) {
        return name.endsWith("m");
    }

    public static void main(String[] args) {
        Map<String, String[]> map = buildMap();
        breadthFirstSearch(map);
    }
}
