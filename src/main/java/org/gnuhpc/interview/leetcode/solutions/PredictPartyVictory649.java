package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class PredictPartyVictory649 {
    public String predictPartyVictory(String senate) {
        // 大会规定肯定有一个人以上来参赛，不用判断是否开赛了

        // 来来来，天辉的站这边
        Queue<Integer> radiantQue  = new LinkedList<>();
        // 来来来，夜魇的站这边
        Queue<Integer> direQue = new LinkedList<>();

        // 取号啦，拿到号的回自己队里去
        for(int i = 0; i < senate.length(); i++){
            if(senate.charAt(i) == 'R'){
                // 天辉，给你号，去吧
                radiantQue.offer(i);
            } else{
                // 夜魇，给你号，去吧
                direQue.offer(i);
            }
        }

        while(true){
            // 天辉这边出个人
            Integer radiant = radiantQue.poll();
            // 夜魇这边出个人
            Integer dire = direQue.poll();
            if(radiant == null){
                // 啥？天辉没人了？我宣布，夜魇胜利
                return "Dire";
            }
            if(dire == null){
                // 啥？夜魇没人了？我宣布，天辉胜利
                return "Radiant";
            }

            if(radiant < dire){
                // 天辉号更靠前，夜魇出来的这个，你被杀了
                // 天辉你再拿个号，去你们队最后等着去吧
                radiantQue.offer(radiant + senate.length());
            } else{
                // 夜魇号更靠前，天辉出来的这个，你被杀了
                // 夜魇你再拿个号，去你们队最后等着去吧
                direQue.offer(dire + senate.length());
            }
        }
    }
}
