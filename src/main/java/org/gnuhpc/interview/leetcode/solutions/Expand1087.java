package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class Expand1087 {
    int idx = 0;

    public String[] expand(String S) {
        List<String> res = new ArrayList<>();

        if (S == null) return null;
        if (S.isEmpty()) return new String[0];
        char[] arr = S.toCharArray();

        while (idx < arr.length) {
            if (arr[idx] >= 'a' && arr[idx] <= 'z') {
                if (res.isEmpty()) {
                    res.add(arr[idx] + "");
                } else {
                    List<String> tmp = new ArrayList<>();
                    for (String s : res) {
                        tmp.add(s + arr[idx]);
                    }
                    res.clear();
                    res.addAll(tmp);
                }
            }

            if (arr[idx] == '{') {
                int end = idx + 1;
                while (end < arr.length && arr[end] != '}') end++;
                List<String> tmpRes = handle(S.substring(idx + 1, end));
                if (res.isEmpty()) res.addAll(tmpRes);
                else {
                    List<String> tmp = new ArrayList<>();
                    for (String s1 : res) {
                        for (String s2 : tmpRes) {
                            tmp.add(s1 + s2);
                        }
                    }

                    res.clear();
                    res.addAll(tmp);
                }
                idx = end;
            }
            idx++;
        }

        Collections.sort(res);
        String[] r = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            r[i] = res.get(i);
        }
        return r;
    }

    private List<String> handle(String substring) {
        List<String> res = new ArrayList<>();
        for (String s : substring.split(",")) res.add(s);

        return res;
    }

    Map<Integer, List<Character>> map = new HashMap<>();

    public String[] expand2(String S) {
        int count = 0;
        int i = 0;
        while (i < S.length()) {
            if (S.charAt(i) == ',') {
                i++;
                continue;
            }
            if (S.charAt(i) == '{') {
                List<Character> list = new ArrayList<>();
                int tmp = i + 1;
                while (tmp < S.length() && S.charAt(tmp) != '}') {
                    if (S.charAt(tmp) != ',') {
                        list.add(S.charAt(tmp));
                    }
                    tmp++;
                }
                Collections.sort(list);
                map.put(count++, list);
                i = tmp + 1;
            } else {
                List<Character> list = new ArrayList<>();
                list.add(S.charAt(i));
                map.put(count++, list);
                i++;
            }
        }
        List<String> result = new ArrayList<>();
        dfs(new StringBuilder(), result);
        return result.toArray(new String[result.size()]);
    }

    private void dfs(StringBuilder curSb, List<String> result) {
        if (curSb.length() == map.size()) {
            result.add(curSb.toString());
            return;
        }

        List<Character> currentNumList = map.get(curSb.length());
        for (int i = 0; i < currentNumList.size(); i++) {
            curSb.append(currentNumList.get(i));
            dfs(curSb, result);
            curSb.deleteCharAt(curSb.length() - 1);
        }
    }

    @Test
    public void test() {
        System.out.println(expand2("{a,b}c{d,e}f"));
    }
}
