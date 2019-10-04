package org.gnuhpc.bigdata.leetcode;

public class ReverseWords186 {

    // add by tina, 使用一个额外数组空间
    public void reverseWords(char[] s) {
        if(s == null || s.length == 0 || s.length == 1) return;
        int n = s.length;
        char[] res = new char[n];
        int i = n-1;
        int j = n-1;
        int k = 0;

        for(;i>=0;i--){
            if(s[i] == ' '){
                for(int m = i+1;m<=j;m++) res[k++] = s[m];
                res[k++] = ' ';
                j=i-1;
            }
        }

        for(int m = 0;m<=j;m++) res[k++] = s[m];
        System.arraycopy(res,0,s,0,res.length);
    }

    // 不使用额外空间，翻转再翻转
    // 1）将字符串的每个单词识别出来，并翻转一遍。2）将字符串整个翻转一遍；
    public void reverseWords2(char[] s) {
        int i=0;
        for(int j=0; j<s.length; j++){
            if(s[j]==' '){
                reverse(s, i, j-1);
                i=j+1;
            }
        }

        reverse(s, i, s.length-1);//最后一个string
        reverse(s, 0, s.length-1);
    }

    public void reverse(char[] s, int i, int j){
        while(i<j){
            char temp = s[i];
            s[i]=s[j];
            s[j]=temp;
            i++;
            j--;
        }
    }

}
