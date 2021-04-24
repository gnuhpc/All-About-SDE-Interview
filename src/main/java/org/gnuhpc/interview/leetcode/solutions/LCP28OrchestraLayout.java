package org.gnuhpc.interview.leetcode.solutions;

public class LCP28OrchestraLayout {
    public int orchestraLayout(int num, int xPos, int yPos) {
        long t = 0;
        //找到第几圈
        int x = Math.min(xPos +1,num - xPos);//2
        int y = Math.min(yPos +1,num - yPos);//2
        int c = Math.min(x,y);//第几圈
        for (int i = 1; i < c; i++) {
            //第一圈 num - 1 - (i-1) * 2;
            //第二圈 num - 2;
            t+=(long)(num - 1 - (i - 1) * 2) * 4;
        }
        int l = c -1;
        int r = num - c;
        int u = c -1;
        int d = num - c;
        //根据xPos,yPos来获取在第几象相
        if(xPos==u && yPos< r){
            t+=yPos - l + 1;
        }else if(yPos==r && xPos<d){
            t+= r - l;
            t+=xPos-u + 1;
        }else if(xPos==d && yPos>l){
            t+=(r-l) * 2;
            t+= r - yPos + 1;
        }else{
            t+=(r-l) * 3;
            t+= d - xPos + 1;
        }
        return (int) ((t-1) % 9 +1);
    }

}
