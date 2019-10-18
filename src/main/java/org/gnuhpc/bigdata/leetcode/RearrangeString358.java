package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

public class RearrangeString358 {
    /**
     * 统计每个字符个数，每次优先放字符个数多的。
     *
     * 我们需要一个哈希表来建立字符和其出现次数之间的映射，
     * 然后需要一个堆来保存这每一堆映射，按照出现次数来排序。
     * 然后如果堆不为空我们就开始循环，我们找出k和str长度之间的较小值，
     * 然后从0遍历到这个较小值，对于每个遍历到的值，如果此时堆为空了，
     * 说明此位置没法填入字符了，返回空字符串，否则我们从堆顶取出一对映射，
     * 然后把字母加入结果res中，此时映射的个数减1，
     * 如果减1后的个数仍大于0，则我们将此映射加入临时集合v中，
     * 同时str的个数len减1，遍历完一次，
     * 我们把临时集合中的映射对由加入堆中。
     * @param str
     * @param k
     * @return
     */
    public String rearrangeString(String str, int k) {
        if(k==0)
            return str;

        //initialize the counter for each character
        final HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c, 1);
            }
        }

        //sort the chars by frequency
        PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>(){
            public int compare(Character c1, Character c2){
                if(map.get(c2).intValue()!=map.get(c1).intValue()){
                    return map.get(c2)-map.get(c1);
                }else{
                    return c1.compareTo(c2);
                }
            }
        });
        /**为什么要对Integer调用intValue函数
         * Integer i = new Integer(10);
         * Integer j = new Integer(10);
         * if (!(i == j)) {
         *     System.out.println("Surprise, doesn't match!");
         * }
         * if (i.intValue() == j.intValue()) {
         *     System.out.println("Cool, matches now!");
         * }
         */
        for(char c: map.keySet())
            queue.offer(c);

        StringBuilder sb = new StringBuilder();

        int len = str.length();

        while(!queue.isEmpty()){

            int cnt = Math.min(k, len);
            ArrayList<Character> temp = new ArrayList<Character>();

            for(int i=0; i<cnt; i++){
                if(queue.isEmpty())
                    return ""; //需要cnt个不同的数来组成这一轮k间隔数据

                char c = queue.poll();
                sb.append(String.valueOf(c));

                map.put(c, map.get(c)-1);

                if(map.get(c)>0){
                    temp.add(c);
                }

                len--;
            }

            for(char c: temp)
                queue.offer(c);
        }
        return sb.toString();
    }


    public String rearrangeString2(String s, int k) {
        if (s == null || s.length() == 0) return "";
        if (k <= 1) return s;

        char[] arr = s.toCharArray();
        int n = arr.length;

        Map<Character, Integer> map = new HashMap<>();
        //TODO 注意这种表示Key-Value 的方式，不用自定义类
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (int i = 0; i < n; i++) {
            char c = arr[i];
            //TODO 注意这种map 的值累加的方法
            int count = map.getOrDefault(c, 0) + 1;
            map.put(c, count);
        }
        pq.addAll(map.entrySet());

        Deque<Map.Entry<Character, Integer>> queue = new ArrayDeque<>(k);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (pq.size() == 0) {
                return "";
            }
            Map.Entry<Character, Integer> entry = pq.remove();
            sb.append(entry.getKey());
            int newVal = entry.getValue() - 1;
            entry.setValue(newVal);

            //queue是暂存区，先进先出，到了size=k的时候就安全了，就一个一个的放回pq TODO
            queue.offer(entry);
            if (queue.size() == k) {
                Map.Entry<Character, Integer> poll = queue.poll();
                if (poll.getValue() > 0) {
                    pq.add(poll);
                }
            }
        }

        return sb.toString();
    }

    /*
    Method2
     */

    public String rearrangeString3(String s, int k) {
        if (k <=1)
            return s;

        //initialize the counter for each character
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        //sort the chars by frequency TODO
        Queue<Character> queue = new PriorityQueue<>((c1, c2) -> {
            if (map.get(c2).intValue() != map.get(c1).intValue()) {
                return map.get(c2) - map.get(c1);
            } else {
                return c1.compareTo(c2);
            }
        });

        for (char c : map.keySet())
            queue.offer(c);

        StringBuilder sb = new StringBuilder();

        int len = s.length();

        while (!queue.isEmpty()) {
            int cnt = Math.min(k, len);
            ArrayList<Character> temp = new ArrayList<Character>();

            for (int i = 0; i < cnt; i++) {
                if (queue.isEmpty())
                    return "";

                char c = queue.poll();
                sb.append(c);

                map.put(c, map.get(c) - 1);

                if (map.get(c) > 0) {
                    temp.add(c);
                }

                len--;
            }

            for (char c : temp)
                queue.offer(c);
        }

        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(rearrangeString("aaadbbcc", 2));
    }
}
