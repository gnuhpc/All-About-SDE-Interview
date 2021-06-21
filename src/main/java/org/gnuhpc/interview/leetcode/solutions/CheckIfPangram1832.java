package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

public class CheckIfPangram1832 {
    public boolean checkIfPangram(String sentence) {
        if(sentence==null || sentence.length()<26) return false;

        char[] sentenceChars = sentence.toCharArray();
        int[] mappings = new int[26];
        Arrays.fill(mappings, 1);

        for(char c: sentenceChars){
            mappings[c-'a']--;
        }

        for(int n: mappings){
            if(n>0) return false;
        }


        return true;
    }
}
