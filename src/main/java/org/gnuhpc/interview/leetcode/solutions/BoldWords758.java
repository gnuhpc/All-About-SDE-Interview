package org.gnuhpc.interview.leetcode.solutions;

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
}

class TrieTree {

    // if the node is an end of a word of [words]
    boolean end = false;
    TrieTree[] next = new TrieTree[26];
}

