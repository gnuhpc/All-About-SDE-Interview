package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

public class FindCheapestPrice787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] arr : flights) {
            List<int[]> list = map.getOrDefault(arr[0], new ArrayList<>());
            list.add(new int[]{arr[1],arr[2]});
            map.put(arr[0], list);
        }

        int min = Integer.MAX_VALUE;

        Queue<Node> queue = new  LinkedList<>();
        int deps = 0;
        queue.offer(new Node(0, src));

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (deps <= K+1) {//中转k次，实际上可以到k+1层
                    if (node.index == dst) {
                        min = Math.min(node.price, min);
                    } else {
                        if (deps < K+1 && node.price < min) {
                            if(map.containsKey(node.index))
                                for (int[] arr : map.get(node.index)) {
                                    if (arr[1] + node.price < min) {
                                        queue.offer(new Node(node.price + arr[1], arr[0]));
                                    }
                                }

                        }
                    }
                }
            }
            deps++;

        }

        return min == Integer.MAX_VALUE ? -1 : min;

    }

    private class Node {
        int price;
        int index;
        public Node(int price, int index) {
            this.index = index;
            this.price = price;
        }
    }

}
