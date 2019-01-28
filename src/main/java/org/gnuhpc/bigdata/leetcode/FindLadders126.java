package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;


public class FindLadders126 {
    /*
    Method1 : DFS, 超时
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        dfs(res, temp, beginWord, endWord, wordList);
        return res;
    }

    private void dfs(List<List<String>> res, List<String> tempList,
                     String currWord, String endWord, List<String> wordList) {
        if (currWord.equals(endWord)) {
            if (!res.isEmpty() && res.get(0).size() > tempList.size()) {
                res.clear();
            }
            res.add(new ArrayList<>(tempList));
        }

        if (wordList.isEmpty() || (res.size() > 0 && tempList.size() >= res.get(0).size())) return;
        for (int i = 0; i < wordList.size(); i++) {
            String temp = wordList.get(i);
            if (diffOneChar(currWord, temp)) {
                tempList.add(temp);
                wordList.remove(i);
                dfs(res, tempList, temp, endWord, wordList);
                wordList.add(i, temp);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //字符长度相差1
    private boolean diffOneChar(String curr, String s) {
        char[] currArr = curr.toCharArray();
        char[] sArr = s.toCharArray();
        int num = 0;
        for (int i = 0; i < curr.length(); i++) {
            if (num > 1) return false;
            if (currArr[i] != sArr[i]) num++;
        }

        return (num == 1);
    }

    /*
    Method 2: BFS 修改了判断两个单词距离的方法，采用遍历的方法，另外BFS在获得步数的同时获得结果
     */


    //记录上一个节点
    class WordNode {
        String word;
        int numSteps;
        WordNode pre;

        public WordNode(String word, int numSteps, WordNode pre) {
            this.word = word;
            this.numSteps = numSteps;
            this.pre = pre;
        }
    }

    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        LinkedList<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1, null));

        int minStep = 0;

        HashSet<String> visited = new HashSet<>();
        HashSet<String> unvisited = new HashSet<>(wordList);

        int preNumSteps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            //那么在轮询的时候即按照这个大小来轮询
            for (int i = 0; i < size; i++) {
                WordNode top = queue.poll();
                String word = top.word;
                int currNumSteps = top.numSteps;

                if (word.equals(endWord)) {
                    if (minStep == 0) {
                        minStep = top.numSteps;
                    }

                    if (top.numSteps == minStep && minStep != 0) {
                        //nothing
                        ArrayList<String> t = new ArrayList<>();
                        t.add(top.word);
                        //往回找
                        while (top.pre != null) {
                            t.add(0, top.pre.word);
                            top = top.pre;
                        }
                        result.add(t);
                        continue;
                    }

                }

                if (preNumSteps < currNumSteps) {
                    unvisited.removeAll(visited);
                }

                preNumSteps = currNumSteps;

                char[] arr = word.toCharArray();
                for (int k = 0; k < arr.length; k++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char temp = arr[k];
                        if (arr[k] != c) {
                            arr[k] = c;
                        }

                        String newWord = new String(arr);
                        if (unvisited.contains(newWord)) {
                            queue.add(new WordNode(newWord, top.numSteps + 1, top));
                            visited.add(newWord);
                        }

                        arr[k] = temp;
                    }
                }
            }
        }

        return result;
    }

    /*
    Method3 ：BFS + DFS ，先用BFS构建出从beginWord到wordList（beginWord也加在dict中）中各个word的steps
    然后DFS的时候根据步数进行 ， 偶尔超时
     */
    //word - step
    HashMap<String, Integer> map = new HashMap<>();

    public List<List<String>> findLadders3(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (beginWord == null || beginWord.length() == 0 ||
                wordList.size() == 0 || beginWord.length() != endWord.length() ||
                wordList.indexOf(endWord) == -1)
            return res;

        BFS(beginWord, endWord, wordList);
        if (map.size() == 1)
            return res;

        DFS(beginWord, endWord, new ArrayList<>(), res);

        return res;

    }

    private void BFS(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        map.put(beginWord, 0);

        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (str.equals(endWord))
                break;
            for (int i = 0; i < beginWord.length(); i++) {
                char[] word = str.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    word[i] = ch;
                    String newWord = new String(word);
                    if (wordSet.contains(newWord)) {
                        if (!map.containsKey(newWord)) {
                            map.put(newWord, map.get(str) + 1);
                            queue.add(newWord);
                        }
                    }
                }
            }
        }
    }

    private void DFS(String currWord, String endWord, List<String> ans, List<List<String>> res) {
        if (currWord.equals(endWord)) {
            ans.add(currWord);
            res.add(ans);
            return;
        }
        if (map.get(currWord) == null)
            return;
        ans.add(currWord);
        int nextStep = map.get(currWord) + 1;
        for (int i = 0; i < currWord.length(); i++) {
            char[] word = currWord.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                word[i] = ch;
                String newWord = new String(word);
                if (map.getOrDefault(newWord, -1) == nextStep) {
                    ArrayList<String> ll = new ArrayList<>(ans);
                    DFS(newWord, endWord, ll, res);
                }
            }
        }
    }


    /*
    Method4 : bidirectional bfs + dfs 速度最快，25ms.... TODO 双向BFS 重点！
     */
    public List<List<String>> findLadders4(String beginWord, String endWord, List<String> wordList) {
        //we use bi-directional BFS to find shortest path

        Set<String> s1 = new HashSet<>();
        s1.add(beginWord);

        Set<String> s2 = new HashSet<>();
        s2.add(endWord);

        List<List<String>> res = new ArrayList<>();
        if (wordList.indexOf(endWord)==1) return res;

        Map<String, List<String>> graph = new HashMap<>();
        BFS(s1, s2, new HashSet<>(wordList), false, graph);


        //if two parts cannot be connected, then return empty list
        if (!isConnected) return res;

        //we need to add start node to temp list as there is no other node can get start node
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        DFS(res, temp, beginWord, endWord, graph);

        return res;
    }

    //flag of whether we have connected two parts
    boolean isConnected = false;

    public void BFS(Set<String> s1, Set<String> s2, Set<String> dict, boolean swap, Map<String, List<String>> graph) {
        //do BFS on every node of s1 direction
        //to force our path to be shortest, we will not do BFS if we have found shortest path(isConnected = true)
        while (!isConnected) {
            //boundary check
            if (s1.isEmpty() || s2.isEmpty()) {
                return;
            }

            //we always do BFS on direction with less nodes
            //here we assume s1 set has less nodes, if not, we swap them
            if (s1.size() > s2.size()) {
                Set<String> tmp = s1;
                s1 = s2;
                s2 = tmp;
                swap =!swap;
            }

            //remove all s1/s2 words from dict to avoid duplicate addition
            dict.removeAll(s1);
            dict.removeAll(s2);

            //new set contains all new nodes from s1 set
            Set<String> nextSet = new HashSet<>();

            for (String str : s1) {
                //try to change each char of str
                for (int i = 0; i < str.length(); i++) {
                    //try to replace current char with every chars from a to z
                    char[] ary = str.toCharArray();
                    for (char j = 'a'; j <= 'z'; j++) {
                        ary[i] = j;
                        String newWord = new String(ary);

                        if (newWord.equals(str)) continue;

                        //we skip this string if it is not in dict nor in s2
                        if (!s2.contains(newWord) && !dict.contains(newWord)) {
                            continue;
                        }

                        //we follow s1 direction
                        String key = !swap ? str : newWord;
                        String val = !swap ? newWord : str;

                        if (!graph.containsKey(key)) graph.put(key, new ArrayList<>());

                        //if newWord string is in s2 set, then it will connect two parts
                        if (s2.contains(newWord)) {
                            graph.get(key).add(val);
                            isConnected = true;
                        }

                        //if newWord is in dict, then we can add it to nextSet as new nodes in next layer
                        if (!isConnected && dict.contains(newWord)) {
                            graph.get(key).add(val);
                            nextSet.add(newWord);
                        }
                    }

                }
                s1 = nextSet;
            }
        }
    }

    public void DFS(List<List<String>> result, List<String> temp, String start, String end, Map<String, List<String>> hs) {
        //we will use DFS, more specifically backtracking to build paths

        //boundary case
        if (start.equals(end)) {
            result.add(new ArrayList<String>(temp));
            return;
        }

        //not each node in hs is valid node in shortest path, if we found current node does not have children node,
        //then it means it is not in shortest path
        if (!hs.containsKey(start)) {
            return;
        }

        for (String s : hs.get(start)) {
            temp.add(s);
            DFS(result, temp, s, end, hs);
            temp.remove(temp.size() - 1);

        }
    }

    @Test
    public void test() {
        List<String> arr = new ArrayList<>();
        arr.add("hot");
        arr.add("dot");
        arr.add("dog");
        arr.add("lot");
        arr.add("log");
        arr.add("cog");


//        System.out.println(findLadders("hit", "cog", arr));
//        System.out.println(findLadders2("hit", "cog", arr));
        System.out.println(findLadders3("hit", "cog", arr));

//        arr.clear();
//        arr.add("a");
//        arr.add("b");
//        arr.add("c");
//        System.out.println(findLadders4("a", "b", arr));
////        arr.clear();
////        arr.add("hot");
////        arr.add("dog");
//        System.out.println(findLadders4("hot", "dog", arr));
    }
}
