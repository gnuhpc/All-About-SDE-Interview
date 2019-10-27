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

    public List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        if(words==null || words.length==0){
            return result;
        }

        int count=0;
        int last=0;
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0; i<words.length; i++){
            count = count + words[i].length();

            if(count+i-last>maxWidth){//不易理解在于当没有超过长度时，只跑上面哪一行。
                int wordsLen = count-words[i].length();
                int spaceLen = maxWidth-wordsLen;
                int eachLen = 1;
                int extraLen = 0;

                //关于空格不能平均分布时的做法
                if(i-last-1>0){
                    eachLen = spaceLen/(i-last-1);
                    extraLen = spaceLen%(i-last-1);
                }

                StringBuilder sb = new StringBuilder();

                for(int k=last; k<i-1; k++){
                    sb.append(words[k]);

                    int ce = 0;
                    while(ce<eachLen){
                        sb.append(" ");
                        ce++;
                    }

                    if(extraLen>0){
                        sb.append(" ");
                        extraLen--;
                    }
                }

                sb.append(words[i-1]);//last words in the line
                //if only one word in this line, need to fill left with space
                while(sb.length()<maxWidth){
                    sb.append(" ");
                }

                result.add(sb.toString());

                last = i;
                count=words[i].length();
            }
        }

        //最后一行的处理
        int lastLen = 0;
        StringBuilder sb = new StringBuilder();

        for(int i=last; i<words.length-1; i++){
            count = count+words[i].length();
            sb.append(words[i]+" ");
        }

        sb.append(words[words.length-1]);
        int d=0;
        while(sb.length()<maxWidth){
            sb.append(" ");
        }
        result.add(sb.toString());

        return result;
    }

    /**
     * 这道题关键在于仔细的处理每一个步骤：
     * 1、每一行选择K的单词，K个单词的长度+K-1个空格的长度必须要小于maxWidth，
     * 这里每次选择满足这个条件的最大值就可以
     * 2、对于已经选定了K个单词，首先计算基本空格，
     * 也就是space=（maxWidth-所有单词的长度）/ （k-1），
     * 但是还有多余出一部分空格，那么就在附加空格的时候，从
     * 左边开始每次多加一个，满足题目的左边的空格大于等于右边的（至多多一个）
     * 3、注意只有1个单词的场景
     * 4、最后一行需要调整，最后一行单词之间的空格只有1个，末尾再用空格补足长度“
     * */
    public List<String> fullJustify3(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        int start=0,end=1,n=words.length;
        while(start<n){
            int compulsorySpaces=0; //必须的空格，为当前选中单词数量-1
            int wordLength=words[start].length();//当前单词的数量
            while(end<n && compulsorySpaces+1+wordLength+words[end].length()<=maxWidth){ //试探选择最大的单词数量
                compulsorySpaces++;
                wordLength+=words[end].length();
                end++;
            }
            if(end==n){ //末行特殊处理
                StringBuilder sb=new StringBuilder(words[start]);
                for(int k=start+1;k<end;k++) sb.append(" "+words[k]);
                for(int k=wordLength+compulsorySpaces;k<maxWidth;k++) sb.append(" ");
                result.add(sb.toString());
                break;
            }
            if(end-start==1){ //只选中的一个的特殊处理，因为计算空格未出现除数为0的状况
                StringBuilder sb=new StringBuilder(words[start]);
                for(int k=wordLength;k<maxWidth;k++)
                    sb.append(" ");
                result.add(sb.toString());
            } else{//处理多个空格
                int space = (maxWidth-wordLength)/(end-start-1); //基本的空格
                int remains = maxWidth-wordLength-(end-start-1)*space; //因为整除未能分配的空格数量
                // remains可用求余直接获取
                StringBuilder sb=new StringBuilder(words[start]);
                for(int k=start+1;k<end;k++){
                    for(int l=0;l<space;l++) sb.append(" ");
                    if(remains-->0) sb.append(" "); //在大于0，也就是还需要在左边多加空格的时候，多给一个
                    sb.append(words[k]);

                }
                result.add(sb.toString());
            }
            start=end;
            end=end+1;
        }
        return result;
    }

}
