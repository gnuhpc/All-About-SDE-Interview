package org.gnuhpc.interview.leetcode.solutions;

public class MasterMind1615 {
    public int[] masterMind(String solution, String guess) {
        char[] sc = solution.toCharArray();
        char[] gc = guess.toCharArray();
        int []result = new int[2];
        result[0] = 0;
        result[1] = 0;
        for(int i=0;i<sc.length;i++){
            if(sc[i] == gc[i]){
                result[0]++;
                sc[i] = '0';
                gc[i] = '1';
            }
        }
        for(int i=0;i<sc.length;i++){
            for(int j = 0;j<gc.length;j++){
                if(sc[i] == gc[j]){
                    result[1]++;
                    gc[j] ='2';
                    break;
                }
            }
        }
        return result;
    }
}
