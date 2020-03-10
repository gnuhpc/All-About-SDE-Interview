package org.gnuhpc.interview.leetcode.solutions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Copyright gnuhpc 2020/1/10
 */
/*
Use the approach of “320. Generalized Abbreviation” to generate all abbreviations of “target”;
Put all the abbreviations into a PriorityQueue according to the length of the abbreviations;
With each abbreviation, check whether it’s an abbreviation of any word in the dictionary using the approach of “408. Valid Word Abbreviation”.
 */
public class MinAbbreviation411 {
    class Abbreviation {
        public String abbr;
        public int len;

        public Abbreviation(String abbr, int len) {
            this.abbr = abbr;
            this.len = len;
        }
    }

    public String minAbbreviation(String target, String[] dictionary) {
        if (dictionary.length == 0) return Integer.toString(target.length());
        PriorityQueue<Abbreviation> q = new PriorityQueue<Abbreviation>((a1, a2) -> a1.len - a2.len);
        generateAbbrs(q, target, "", 0, 0, false);
        while (!q.isEmpty()) {
            String abbr = q.poll().abbr;
            boolean notMatch = true;
            for (int i = 0; i < dictionary.length; i++) {
                if (isValidAbbr(dictionary[i], abbr)) {
                    notMatch = false;
                    break;
                }
            }
            if (notMatch) return abbr;
        }
        return "";
    }

    private void generateAbbrs(PriorityQueue<Abbreviation> q, String target, String path,
                               int start, int len, boolean prevAbbr) {
        if (start == target.length()) {
            q.offer(new Abbreviation(path, len));
            return;
        }
        generateAbbrs(q, target, path + target.charAt(start), start + 1, len + 1, false);
        if (!prevAbbr) {
            for (int i = start; i < target.length(); i++) {
                String str = target.substring(start, i + 1);
                generateAbbrs(q, target, path + str.length(),
                        i + 1, len + 1, true);
            }
        }
    }

    private boolean isValidAbbr(String word, String abbr) {
        int index = 0, i = 0;
        while (i < abbr.length()) {
            if (!Character.isDigit(abbr.charAt(i))) {
                if (index >= word.length() || word.charAt(index) != abbr.charAt(i)) return false;
                index++;
                i++;
            } else {
                int start = i;
                while (i < abbr.length() && Character.isDigit(abbr.charAt(i))) i++;
                int number = Integer.parseInt(abbr.substring(start, i));
                index += number;
            }
        }
        return index == word.length();
    }
}
