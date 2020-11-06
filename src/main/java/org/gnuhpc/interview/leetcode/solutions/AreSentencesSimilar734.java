package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/10/25
 */
public class AreSentencesSimilar734 {
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) return false;

        Set<String> pairset = new HashSet();
        for (List<String> pair : similarPairs) {
            String str1 = pair.get(0);
            String str2 = pair.get(1);
            pairset.add(str1 + "#" + str2);
            if (!str1.equals(str2))
                pairset.add(str2 + "#" + str1);
        }


        for (int i = 0; i < sentence1.length; ++i) {
            if (!sentence1[i].equals(sentence2[i]) &&
                    !pairset.contains(sentence1[i] + "#" + sentence2[i]))
                return false;
        }
        return true;
    }
}
