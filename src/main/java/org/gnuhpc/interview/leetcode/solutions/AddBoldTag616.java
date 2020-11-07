package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class AddBoldTag616 {
    public String addBoldTag(String s, String[] dict) {
        if(dict.length == 0)
            return s;

        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < s.length(); i++)
        {
            for(String dic: dict)
            {
                if(s.startsWith(dic, i))
                {
                    int[] temp = new int[]{i, i + dic.length() - 1};
                    list.add(temp);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int index = 0;
        while(i < list.size())
        {
            int left = list.get(i)[0];
            int right = list.get(i)[1];
            sb.append(s.substring(index, left));
            while(i < list.size() && list.get(i)[0] - 1 <= right)
            {
                right = Math.max(right, list.get(i)[1]);
                i++;
            }
            sb.append("<b>" + s.substring(left, right + 1) + "</b>");
            index = right + 1;
        }
        if(index <= s.length() - 1)
            sb.append(s.substring(index));
        return sb.toString();
    }
}
