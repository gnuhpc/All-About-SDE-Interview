package org.gnuhpc.bigdata.leetcode;

import com.google.inject.internal.cglib.core.$CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FullJustify68 {
    private List<String> res = new ArrayList<>();

    public List<String> fullJustify(String[] words, int maxWidth) {
        int count = 0;
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            if (count + str.length() == maxWidth){
                temp.add(str);
                res.add(process(temp,maxWidth,false));
                temp.clear();
                count = 0;
            }
            else if (count + str.length() > maxWidth) {
                res.add(process(temp,maxWidth,false));
                temp.clear();
                count = 0;
                i--;
            } else {
                temp.add(str);
                count += str.length()+1;
            }
        }

        if (!temp.isEmpty()) res.add(process(temp,maxWidth,true));

        return res;
    }

    private String process(List<String> temp, int maxWidth, boolean isEnd) {
        StringBuilder sb = new StringBuilder();
        int len = temp.size();

        if (!isEnd){ // 不是最后一行
            if (len == 1){ //这一行就一个单词
                sb.append(temp.get(0));
                sb.append(makeSpace(maxWidth-temp.get(0).length()));
            } else{
                int padCount = temp.size() - 1;
                int spaceSum = maxWidth-sumLength(temp);

                if (spaceSum%padCount == 0){
                    int spaceCount = spaceSum/padCount;
                    String spaces = makeSpace(spaceCount);

                    for (int i = 0; i < temp.size()-1; i++) {
                        sb.append(temp.get(i));
                        sb.append(spaces);
                    }

                    sb.append(temp.get(temp.size()-1));
                } else {
                    int[] spaces = new int[padCount];
                    int init = spaceSum/padCount;
                    Arrays.fill(spaces,init);

                    int left = spaceSum%padCount;

                    for (int i = 0; left >0 && i < spaces.length ; i++) {
                        spaces[i]++;
                        left--;
                    }

                    int i = 0;
                    for (; i < temp.size() -1; i++) {
                        sb.append(temp.get(i));
                        sb.append(makeSpace(spaces[i]));
                    }

                    sb.append(temp.get(i));
                }
            }
        } else { //是最后一行
            for (int i = 0; i < len; i++) {
                sb.append(temp.get(i));
                sb.append(" ");
            }

            sb.append(makeSpace(maxWidth-sb.length()));
        }
        return sb.toString();
    }

    private int sumLength(List<String> temp) {
        int sum = 0 ;
        for(String s: temp){
            sum += s.length();
        }

        return sum;
    }

    private String makeSpace(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }


    @Test
    public void test(){
        System.out.println(fullJustify(new String[]{
            "This", "is", "an", "example", "of", "text", "justification."
        },16));
//        System.out.println(fullJustify(new String[]{
//                "Listen","to","many,","speak","to","a","few."
//        },6));
    }
}
