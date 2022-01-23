package org.gnuhpc.interview.leetcode.solutions;

public class Rand10470 {
    public int rand10() {
        while(true) {
            int num = (rand7() - 1) * 7 + rand7(); // 等概率生成[1,49]范围的随机数
            if(num <= 40) return num % 10 + 1; // 拒绝采样，并返回[1,10]范围的随机数
        }
    }

    private int rand7() {
        return 0;
    }
}

/*
GoLang Version
func rand10() int {
    a:=rand5()
    b:=rand2()
    if b==1{
        return a
    }else{
        return 5+rand5()
    }
}
func rand2()int{
    t:=rand7()
    for t>2{
        t=rand7()
    }
    return t
}
func rand5()int{
    t:=rand7()
    for t>5{
        t=rand7()
    }
    return t
}

 */
