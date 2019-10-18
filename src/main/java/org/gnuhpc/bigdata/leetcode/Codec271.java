package org.gnuhpc.bigdata.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Codec271 {
    // To encode the string,
    // we can add some symbols before it.
    // For example, for string “abc”,
    // we can add the length 3 before it.
    // However, for string starting from numbers,
    // like 123abc. This could result in 6123abc,
    // which is not acceptable.
    // Then we add an ‘#’ between the length and the string.
    // So 3#abc is the decoded string.

    //then the question is strs will contain #?
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String s : strs){
            sb.append(s.length());
            sb.append("#");
            sb.append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int k = 0;
        while(k < s.length()){
            int pound = s.indexOf("#", k);
            int size = Integer.parseInt(s.substring(k, pound));
            res.add(s.substring(pound+1, pound+1+size));
            k = pound+1+size;
        }
        return res;
    }
}
