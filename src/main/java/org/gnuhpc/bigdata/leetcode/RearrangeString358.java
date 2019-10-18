package org.gnuhpc.bigdata.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

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

}
