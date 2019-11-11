package org.gnuhpc.bigdata.leetcode;

import com.google.inject.internal.util.$AbstractMapEntry;
import org.junit.Test;

import java.util.*;


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

        for (String word: wordList){
            graph.put(word, new HashSet<>());
            visited.put(word,false);
        }

        for (String word1: wordList){
            for (String word2: wordList){
                if (diffOneChar(word1,word2)){
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
        if (currWord.equals(endWord)){
            if (res.isEmpty()){
                res.add(new ArrayList<>(tmp));
            } else{
                if (res.get(0).size()>tmp.size()) {
                    res.clear();
                    res.add(new ArrayList<>(tmp));
                }
                else if (res.get(0).size() == tmp.size()) res.add(new ArrayList<>(tmp));
                return;
            }
        }

        for (String nWord: graph.get(currWord)){
            if (visited.get(nWord)) continue;
            tmp.add(nWord);
            visited.put(nWord,true);
            dfs(nWord,endWord,res,tmp);
            tmp.remove(tmp.size()-1);
            visited.put(nWord,false);
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

    /*
    Method3 ：BFS + DFS ，先用BFS构建出从beginWord到wordList（beginWord也加在dict中）中各个word的steps
    然后DFS的时候根据步数进行
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
        // 结果集
        List<List<String>> res = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        // 字典中不包含目标单词
        if (!words.contains(endWord)) {
            return res;
        }
        // 存放关系：每个单词可达的下层单词
        Map<String, List<String>> mapTree = new HashMap<>();
        Set<String> begin = new HashSet<>(), end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        if (buildTree(words, begin, end, mapTree, true)) {
            dfs(res, mapTree, beginWord, endWord, new LinkedList<>());
        }
        return res;
    }

    // 双向BFS，构建每个单词的层级对应关系
    private boolean buildTree(Set<String> words,
                              Set<String> begin,
                              Set<String> end,
                              Map<String, List<String>> mapTree,
                              boolean isFront) {
        if (begin.size() == 0) {
            return false;
        }
        // 始终以少的进行探索
        if (begin.size() > end.size()) {
            return buildTree(words, end, begin, mapTree, !isFront);
        }
        // 在已访问的单词集合中去除
        words.removeAll(begin);
        // 标记本层是否已到达目标单词
        boolean isMeet = false;
        // 记录本层所访问的单词
        Set<String> nextLevel = new HashSet<>();
        for (String word : begin) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    String str = String.valueOf(chars);
                    if (words.contains(str)) {
                        nextLevel.add(str);
                        // 根据访问顺序，添加层级对应关系：始终保持从上层到下层的存储存储关系
                        // true: 从上往下探索：word -> str
                        // false: 从下往上探索：str -> word（查找到的 str 是 word 上层的单词）
                        String key = isFront ? word : str;
                        String nextWord = isFront ? str : word;
                        // 判断是否遇见目标单词
                        if (end.contains(str)) {
                            isMeet = true;
                        }
                        if (!mapTree.containsKey(key)) {
                            mapTree.put(key, new ArrayList<>());
                        }
                        mapTree.get(key).add(nextWord);
                    }
                }
                chars[i] = temp;
            }
        }
        if (isMeet) {
            return true;
        }
        return buildTree(words, nextLevel, end, mapTree, isFront);
    }

    // DFS: 组合路径
    private void dfs(List<List<String>> res,
                     Map<String, List<String>> mapTree,
                     String beginWord,
                     String endWord,
                     LinkedList<String> list) {
        list.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            list.removeLast();
            return;
        }
        if (mapTree.containsKey(beginWord)) {
            for (String word : mapTree.get(beginWord)) {
                dfs(res, mapTree, word, endWord, list);
            }
        }
        list.removeLast();
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
        System.out.println(findLadders4("hit", "cog", arr));

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
