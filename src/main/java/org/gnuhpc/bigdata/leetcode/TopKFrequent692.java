package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

public class TopKFrequent692 {
    //PQ方法
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> queue = new PriorityQueue<>(k, (s1, s2) -> {
            if (map.get(s1).equals(map.get(s2))) {
                return s2.compareTo(s1);
            }
            return map.get(s1)-map.get(s2);
        });

        for (String key : map.keySet()) {
            if (queue.size() < k) {
                queue.add(key);
            } else if (queue.comparator().compare(key, queue.peek()) > 0) { //注意这个地方
                queue.poll();
                queue.add(key);
            }
        }

        LinkedList<String> res = new LinkedList<>();

        while (!queue.isEmpty()) res.addFirst(queue.poll());

        return res;
    }

    //bucket方法
    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String>[] freqList = new List[words.length+1];


        for (Map.Entry<String,Integer> entry : map.entrySet()){
            int freq = entry.getValue();

            if (freqList[freq] == null) freqList[freq] = new ArrayList<>();
            freqList[freq].add(entry.getKey());
        }

        for (int i = freqList.length-1; i >=0; i--) {
            if (freqList[i]!=null){

                List<String> l = freqList[i];
                Collections.sort(l);
                for(String word:l){
                    if (res.size() <k) res.add(word);
                }

                if(res.size() == k) break;
            }
        }

        return res;
    }

    /*
    Method3 Sort
     */

    /*
    时间复杂度：O(NlogN)
    空间复杂度：O(N)，用来存放候答案的地方

     */
    class Node {
        String word;
        int count;
        public Node(String word) {
            this.word = word;
            this.count = 1;
        }
    }
    public List<String> topKFrequent3(String[] words, int k) {

        HashMap<String, Node> nodes = new HashMap<String, Node>();
        for(String word : words) {
            Node n = nodes.getOrDefault(word, null);
            if ( n == null ) {
                nodes.put(word, new Node(word));
            } else {
                n.count++;
            }
        }

        List<Node> list = new ArrayList<Node>();
        list.addAll(nodes.values());
        list.sort((o1, o2) -> {
            if (o1.count == o2.count){
                return o1.word.compareTo(o2.word);
            } else {
                return o2.count - o1.count;
            }
        });

        List<String> r = new ArrayList<>(k);
        for(int i = 0; i < k; i++)
            r.add(list.get(i).word);
        return r;
    }

    @Test
    public void test(){
        System.out.println(topKFrequent(new String[]{
            "i", "love", "leetcode", "i", "love", "coding"
        },2));
    }
}
