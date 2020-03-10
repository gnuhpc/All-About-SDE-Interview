package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

import static org.gnuhpc.interview.leetcode.utils.Utils.getNextWords;


public class FindLadders126 {
    /*
    Method1 : DFS, 超时
     */
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

    Map<String, Set<String>> graph;
    Map<String, Boolean> visited;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        graph = new HashMap<>();
        visited = new HashMap<>();

        wordList.add(beginWord);

        for (String word : wordList) {
            graph.put(word, new HashSet<>());
            visited.put(word, false);
        }

        for (String word1 : wordList) {
            for (String word2 : wordList) {
                if (!word1.equals(word2) && diffOneChar(word1, word2)) {
                    graph.get(word1).add(word2);
                    graph.get(word2).add(word1);
                }
            }
        }

        visited.put(beginWord, true);
        List<String> tmp = new ArrayList<>();
        tmp.add(beginWord);
        dfs(beginWord, endWord, res, tmp);

        return res;
    }

    private void dfs(String currWord, String endWord, List<List<String>> res, List<String> tmp) {
        if (currWord.equals(endWord)) {
            if (res.isEmpty()) {
                res.add(new ArrayList<>(tmp));
            } else {
                if (res.get(0).size() > tmp.size()) {
                    res.clear();
                    res.add(new ArrayList<>(tmp));
                } else if (res.get(0).size() == tmp.size()) res.add(new ArrayList<>(tmp));
                return;
            }
        }

        for (String nWord : graph.get(currWord)) {
            if (visited.get(nWord)) continue;
            tmp.add(nWord);
            visited.put(nWord, true);
            dfs(nWord, endWord, res, tmp);
            tmp.remove(tmp.size() - 1);
            visited.put(nWord, false);
        }
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
                    // 当明确已经到达下一层的时候,上一层不再用了,因此将所有visited里面的节点从unvisited里面去掉
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

    /* 这个方法最推荐
    Method3 ：BFS + DFS ，
    在第一步BFS的过程中, 利用一个hash map记录start到每一个结点的最短距离.

    那么在第二步DFS的过程中, 可以利用那个distance的hash map, 在继续往下之前,
    check下一个结点的最短距离是否是当前结点最短距离加一. 是的话就继续, 不是的话就根本不需要visit那个结点了.
     */
    public List<List<String>> findLadders3(String beginWord, String endWord, List<String> wordList) {
        final List<List<String>> res = new ArrayList<>();
        final Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;
        final Map<String, Set<String>> graph = new HashMap<>();
        final Map<String, Integer> dist = new HashMap<>();
        bfs(beginWord, endWord, dict, graph, dist);
        if (!dist.containsKey(endWord) || dist.get(endWord) == 0) return res;
        List<String> list = new ArrayList<>(Arrays.asList(beginWord));
        dfs(beginWord, endWord, graph, dist, res, list);
        return res;
    }

    private void bfs(final String beginWord, final String endWord, final Set<String> dict,
                     final Map<String, Set<String>> graph, final Map<String, Integer> dist) {
        final Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        dist.put(beginWord, 1);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                graph.put(curr, new HashSet<>());
                if (curr.equals(endWord)) return;
                for (String nextWord : getNextWords(curr, dict)) {
                    graph.putIfAbsent(nextWord, new HashSet<>());
                    graph.get(curr).add(nextWord);
                    if (!dist.containsKey(nextWord)) {
                        dist.put(nextWord, depth + 1);
                        queue.add(nextWord);
                    }
                }
            }
        }
    }

    private void dfs(String beginWord, String endWord, Map<String, Set<String>> graph,
                     Map<String, Integer> dist,
                     List<List<String>> res, List<String> tmp) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        int len = dist.get(beginWord);
        for (String adj : graph.get(beginWord)) {
            if (dist.get(adj) == len + 1) {
                tmp.add(adj);
                dfs(adj, endWord, graph, dist, res, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    /*
    Method4 : bidirectional bfs + dfs 速度最快，25ms.... 双向BFS 重点！
     */
    public List<List<String>> findLadders4(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }
        HashSet<String> beginSet = new HashSet<>();
        HashSet<String> endSet = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        HashMap<String, List<String>> map = new HashMap<>();
        bfs(map, beginSet, endSet, dict, false);

        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(res, path, map, beginWord, endWord);
        return res;
    }

    private void bfs(HashMap<String, List<String>> map,
                     HashSet<String> beginSet, HashSet<String> endSet,
                     HashSet<String> dict, boolean flip) {
        if (beginSet.isEmpty()) {
            return;
        }

        if (beginSet.size() > endSet.size()) {
            bfs(map, endSet, beginSet, dict, !flip);
            return;
        }

        boolean done = false;
        dict.removeAll(beginSet);
        dict.removeAll(endSet);

        HashSet<String> next = new HashSet<>();
        for (String str : beginSet) {
            char[] chs = str.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char temp = chs[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (chs[i] != ch) {
                        chs[i] = ch;
                        String word = new String(chs);

                        String key = flip ? word : str;
                        String val = flip ? str : word;

                        List<String> list = map.get(key) == null ? new ArrayList<>() : map.get(key);

                        if (endSet.contains(word)) {
                            done = true;
                            list.add(val);
                            map.put(key, list);
                        } else {
                            if (dict.contains(word)) {
                                next.add(word);
                                list.add(val);
                                map.put(key, list);
                            }
                        }
                    }
                }
                chs[i] = temp;
            }
        }

        if (!done) {
            bfs(map, endSet, next, dict, !flip);
        }
    }

    private void dfs(List<List<String>> res,
                     List<String> path,
                     HashMap<String, List<String>> map,
                     String start,
                     String end) {
        if (start.equals(end)) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (!map.containsKey(start)) {
            return;
        }

        for (String next : map.get(start)) {
            path.add(next);
            dfs(res, path, map, next, end);
            path.remove(path.size() - 1);
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
