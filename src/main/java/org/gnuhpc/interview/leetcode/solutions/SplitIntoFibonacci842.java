package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SplitIntoFibonacci842 {
    //Brutal force
    /*
     * the first two numbers totally determine the solution
     * pay note to 32-bit integer overflow. Using Long to hold the numbers
     * when splitting, each number should not exceed 10 digits
     * */
    private List<Integer> find(int a, int b, String s) {
        List<Integer> res = new ArrayList<>();
        res.add(a);
        res.add(b);
        long c = a + b;
        if (c > Integer.MAX_VALUE) return new ArrayList<>();
        int cnt;
        while (s.length() > 0) {
            cnt = Integer.toString((int) c).length();

            if (s.startsWith(Integer.toString((int) c))) {
                res.add((int) c);
                s = s.substring(cnt);
            } else {
                return new ArrayList<>();
            }
            a = b;
            b = (int) c;
            c = a + b;
            if (c > Integer.MAX_VALUE) return new ArrayList<>();
        }
        return res;
    }

    public List<Integer> splitIntoFibonacci(String S) {
        int n = S.length();
        for (int i = 1; i < n; i++) {
            String sa = S.substring(0, i);
            Long a = Long.valueOf(sa);
            if (a > Integer.MAX_VALUE) break;
            for (int j = 1; j + i + 1 < n; j++) {
                String sb = S.substring(i, i + j);
                Long b = Long.valueOf(sb);
                if (b > Integer.MAX_VALUE) break;
                if ((a == 0 && sa.length() > 1) || (b == 0 && sb.length() > 1)) continue;
                List<Integer> res = find(a.intValue(), b.intValue(), S.substring(i + j));
                if (res != null && res.size() >= 3)
                    return res;
            }
        }
        return new ArrayList<>();
    }


    //Back tracking
    public List<Integer> splitIntoFibonacci3(String S) {
        List<Integer> ans = new ArrayList<>();
        helper(S, ans, 0);
        return ans;
    }

    public boolean helper(String s, List<Integer> ans, int idx) {
        if (idx == s.length() && ans.size() >= 3) {
            return true;
        }
        for (int i = idx; i < s.length(); i++) {
            if (s.charAt(idx) == '0' && i > idx) {
                break;
            }
            long num = Long.parseLong(s.substring(idx, i + 1));
            if (num > Integer.MAX_VALUE) {
                break;
            }
            int size = ans.size();
            // early termination
            if (size >= 2 && num > ans.get(size - 1) + ans.get(size - 2)) {
                break;
            }
            if (size <= 1 || num == ans.get(size - 1) + ans.get(size - 2)) {
                ans.add((int) num);
                // branch pruning. if one branch has found fib seq, return true to upper call
                if (helper(s, ans, i + 1)) {
                    return true;
                }
                ans.remove(ans.size() - 1);
            }
        }
        return false;
    }

    //Brutal force , find all
    private List<Integer> find2(int a, int b, String s) {
        List<Integer> res = new ArrayList<>();
        res.add(a);
        res.add(b);
        long c = a + b;
        if (c > Integer.MAX_VALUE) return res;
        int cnt;
        while (s.length() > 0) {
            if (c > Integer.MAX_VALUE) continue;
            cnt = Integer.toString((int) c).length();

            if (s.startsWith(Integer.toString((int) c))) {
                res.add((int) c);
                s = s.substring(cnt);
            } else {
                return new ArrayList<>();
            }
            a = b;
            b = (int) c;
            c = a + b;
        }
        return res;
    }

    public List<Integer> splitIntoFibonacci2(String S) {
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 1; i < (S.length() + 1) / 2 + 1; i++) {
            String sa = S.substring(0, i);
            if (sa.length() > 1 && sa.charAt(0) == '0') break;
            Long a = Long.valueOf(sa);
            if (a > Integer.MAX_VALUE) break;
            for (int j = 1; j + i < S.length(); j++) {
                String sb = S.substring(i, i + j);
                if (sb.length() > 1 && sb.charAt(0) == '0') break;
                Long b = Long.valueOf(sb);
                if (b > Integer.MAX_VALUE) break;
                if ((a == 0 && sa.length() > 1) || (b == 0 && sb.length() > 1)) continue;
                List<Integer> temp = find2(a.intValue(), b.intValue(), S.substring(i + j));
                if (!temp.isEmpty()) resultList.add(temp);
            }
        }
        if (resultList.size() > 0)
            return resultList.get(0);
        else
            return new ArrayList<>();
    }

    @Test
    public void test() {
        System.out.println(splitIntoFibonacci2("0123"));
    }
}
