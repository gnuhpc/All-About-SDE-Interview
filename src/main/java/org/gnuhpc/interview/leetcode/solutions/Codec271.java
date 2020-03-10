package org.gnuhpc.interview.leetcode.solutions;

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

    //then the question is strs will contain #? 在处理的时候不能直接进行split，而是需要一段一段的跳过去做
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
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
        while (k < s.length()) {
            int pound = s.indexOf("#", k);
            int size = Integer.parseInt(s.substring(k, pound));
            res.add(s.substring(pound + 1, pound + 1 + size));
            k = pound + 1 + size;
        }
        return res;
    }

    /*
    Solution 1. Application of escape sequence

A straightforward idea is to add ";" between two strings as the divide symbol. But the problem here is,

what about if the input strings contain ";" already? How do we distinguish between the divide symbol ";"

and the ";" that are part of the input strings?



Consider how escape sequence works. \ represents the start of an escape sequence, like \n is a newline

character. Since \ is already reserved as the start of any escape sequence, \\ is used to represent the

backslash literal. We can simply borrow this idea and use it in this problem.



Let's define : as the start of escape sequence, so :; is our divide symbol now.

Encode:  Replace all : with :: in each string, so :: represents the original : literal;

　　　　   Connect each string with divide symbol :;

Decode: Scan from left to right until a : is met.

 　　　　 If the next character is ;, it means we have a :; divide symbol;

　　　　  If not, since we've already replaced all : with :: in encoding, so

　　　　  we know that we just saw a : literal.
     */
    public String encode2(List<String> strs) {
        if (strs == null) {
            return null;
        }
        if (strs.size() == 0) {
            return ":;";
        }
        StringBuffer sb = new StringBuffer();
        for (String s : strs) {
            String newStr = s.replaceAll(":", "::");
            sb.append(newStr);
            sb.append(":;");
        }
        return sb.toString();
    }

    public List<String> decode2(String str) {
        if (str == null) {
            return null;
        }
        List<String> strs = new ArrayList<>();
        int idx = 0;
        StringBuffer sb = new StringBuffer();
        while (idx < str.length()) {
            if (str.charAt(idx) != ':') {
                sb.append(str.charAt(idx));
                idx++;
            } else if (str.charAt(idx + 1) == ';') {
                strs.add(sb.toString());
                idx += 2;
                sb = new StringBuffer();
            } else {
                sb.append(":");
                idx += 2;
            }
        }
        return strs;
    }
}
