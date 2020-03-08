package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class GroupAnagrams49 {
    // 先对word排序，再考虑
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] sc = s.toCharArray();
            Arrays.sort(sc);//Arrays.sort(sc)返回void
            String key = String.valueOf(sc);
            map.putIfAbsent(key, new ArrayList<>()); //可代替对map containsKey的判断
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    @Test
    public void test() {
        String[] testArray = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] testArray2 = new String[]{""};

        groupAnagrams(testArray);
//        groupAnagrams(testArray2);
    }

    // add by tina，暴力解法
    // 注意对[""],["",""]的处理
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        if (strs.length == 1 && strs[0].equals("")) { //不能用==
            res.add(new ArrayList<String>(Arrays.asList("")));
            return res;
        }
        int n = strs.length;

        int[][] cnts = new int[n][26];
        Map<Integer, ArrayList<String>> hmap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String cur = strs[i];
            if (cur.equals("")) {
                if (hmap.containsKey(-1)) hmap.get(-1).add("");
                else hmap.put(-1, new ArrayList<String>(Arrays.asList("")));
                continue;
            }

            // cnt cur
            int[] cnt = strCount(cur);
            // find cnt in cnts
            int idx = find(cnts, cnt);
            if (idx != -1) {//找到同组字谜
                //ArrayList<String> st = hmap.get(idx); //map里的value是引用吗
                //st.add(cur);
                //hmap.put(idx,st);
                hmap.get(idx).add(cur);
            }
            else {//一个新的no anagram word
                cnts[i] = cnt;
                hmap.put(i, new ArrayList<String>(Arrays.asList(cur)));
            }
        }
        return new ArrayList(hmap.values());
    }

    public int[] strCount(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        return cnt;
    }

    public int find(int[][] cnts, int[] cnt) {
        for (int i = 0; i < cnts.length; i++) {
            if (comp(cnts[i], cnt)) return i;
        }
        return -1;
    }

    public boolean comp(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    // 在方法二的基础上进行优化
    // 我们可以将计数数组转换成字符串序列，放入map中，这样每次只用比较map的key即可
    // 不用每次去比较
    public List<List<String>> groupAnagrams3(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        int n = strs.length;

        int[][] cnts = new int[n][26];
        Map<String, ArrayList<String>> hmap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String cur = strs[i];
            int[] cnt = new int[26];
            String cs = "";
            for (int j = 0; j < cur.length(); j++) cnt[cur.charAt(j) - 'a']++;
            for (int j = 0; j < 26; j++) cs += String.valueOf(cnt[j]) + "/";
            if (hmap.containsKey(cs)) hmap.get(cs).add(cur); //map.get要求key必须存在
            else hmap.put(cs, new ArrayList<>(Arrays.asList(cur)));
        }
        return new ArrayList(hmap.values());
    }
}
