package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Copyright gnuhpc 2020/8/7
 */
public class BoldWords758 {
    public String boldWords(String[] words, String S) {
        boolean[] isBold = new boolean[S.length()];
        for (String word : words) {
            int n = S.indexOf(word);
            while (n != -1) {
                for (int i = n; i < n + word.length(); i++) {
                    isBold[i] = true;
                }
                n = S.indexOf(word, n + 1);
            }
        }
        StringBuilder s = new StringBuilder();
        if (isBold[0]) {
            s.append("<b>");
        }
        int i = 0;
        for (; i < isBold.length - 1; i++) {
            s.append(S.charAt(i));
            if (isBold[i] && !isBold[i + 1]) {
                s.append("</b>");
            }
            if (!isBold[i] && isBold[i + 1]) {
                s.append("<b>");
            }
        }
        if (i == isBold.length - 1) {
            s.append(S.charAt(i));
            if (isBold[i]) {
                s.append("</b>");
            }
        }
        return s.toString();
    }

    /*
    Method2: Trie
     */
    private static final String open = "<b>";
    private static final String close = "</b>";

    public String boldWords2(String[] words, String S) {
        TrieTree root = new TrieTree();

        // Init TrieTree with the words in [words]
        for (String word : words) {
            TrieTree node = root;
            char[] w = word.toCharArray();
            for (char c : w) {
                if (node.next[c - 'a'] == null)
                    node.next[c - 'a'] = new TrieTree();
                node = node.next[c - 'a'];
            }
            node.end = true;
        }

        // Process String S with the Trie Tree
        char[] ss = S.toCharArray();
        StringBuilder sb = new StringBuilder();

        // Current max end index
        int maxEnd = -1;

        // if there has been an "<b>"
        boolean isOpen = false;
        for (int i = 0; i < ss.length; i++) {
            TrieTree node = root;
            int j = i;
            while (j < ss.length && node.next[ss[j] - 'a'] != null) {
                node = node.next[ss[j] - 'a'];
                j++;

                // if the node is an end --> update the maxEnd (if needed)
                if (node.end) {
                    if (!isOpen) {
                        sb.append(open);
                        isOpen = true;
                    }
                    maxEnd = Math.max(maxEnd, j);
                }
            }

            // if current ptr is the maxEnd -> we need a </b> there
            if (i == maxEnd) {
                sb.append(close);
                isOpen = false;
            }

            // append the character into sb after we dont need it
            sb.append(ss[i]);
        }

        // the last </b> (if needed)
        if (isOpen) sb.append(close);

        return sb.toString();
    }

    public String boldWords3(String[] words, String S) {
        if (words == null || words.length == 0) {
            return S;
        }

        // step 1: find start and end pos of the substring
        //
        List<Interval> intervals = new ArrayList<>();
        for (String t : words) {
            strStr(S, t, intervals);
        }

        if (intervals.isEmpty()) {
            return S;
        }

        // step 2: sort the intervals based on the start index
        //
        Collections.sort(intervals, Comparator.comparingInt(a -> a.start));

        // step 3: merge intervals
        //
        List<Interval> mergedIntervals = mergeIntervals(intervals);

        // step 4: compose the result based on the merged intervals
        //
        StringBuilder sb = new StringBuilder();
        int prev = 0;

        for (int i = 0; i < mergedIntervals.size(); i++) {
            Interval curr = mergedIntervals.get(i);
            // prev seg
            //
            sb.append(S.substring(prev, curr.start));
            sb.append("<b>");

            // curr seg
            //
            sb.append(S.substring(curr.start, curr.end + 1));
            sb.append("</b>");

            prev = curr.end + 1;
        }

        // trailing substring
        //
        if (prev < S.length()) {
            sb.append(S.substring(prev));
        }

        return sb.toString();
    }

    private void strStr(String s, String t, List<Interval> list) {
        for (int i = 0; i < s.length() - t.length() + 1; i++) {
            int j = 0;
            while (j < t.length()) {
                if (s.charAt(i + j) == t.charAt(j)) {
                    j++;
                } else {
                    break;
                }
            }

            if (j == t.length()) {
                Interval interval = new Interval(i, i + j - 1);
                list.add(interval);
            }
        }
    }

    private List<Interval> mergeIntervals(List<Interval> intervals) {
        List<Interval> ans = new ArrayList<>();

        if (intervals == null || intervals.isEmpty()) {
            return ans;
        }

        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (prev.end >= curr.start || prev.end + 1 == curr.start) {
                prev.end = Math.max(prev.end, curr.end);
            } else {
                ans.add(new Interval(prev.start, prev.end));
                prev = curr;
            }
        }

        ans.add(prev);

        return ans;
    }
}

class TrieTree {

    // if the node is an end of a word of [words]
    boolean end = false;
    TrieTree[] next = new TrieTree[26];
}

