package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组合＋表达的问题
 * 先是一个C10k的问题，
 * 然后将这个list组合成可以表示时间的字符串，不符合要求的pass
 */
public class ReadBinaryWatch401 {
    // from lc disscussion, 这个方法的优越之处在于第一个
    // 1、把问题拆解成时和分2个问题，如此递归时输入的数组比较小，效率高
    // 2、不用map，用数组取代，在递归时，完成加法运算
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] hourDigits = new int[]{8, 4, 2, 1}, minDigits = new int[]{32, 16, 8, 4, 2, 1};
        for (int i = 0; i <= num; i++) {
            List<Integer> hours = generateDigit(hourDigits, i);
            List<Integer> mins = generateDigit(minDigits, num - i);
            for (int h : hours) {// 2轮循环，完成字符串表达
                if (h >= 12)
                    continue;
                for (int m : mins) {
                    if (m >= 60) continue;
                    res.add(h + ":" + (m < 10 ? "0" + m : m));
                }
            }
        }
        return res;
    }

    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }

    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if (count == 0) {//递归结束条件
            res.add(sum);
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);
        }
    }

    @Test
    public void test() {
        readBinaryWatch(2);
    }

}

/* Golang version

func readBinaryWatch(turnedOn int) []string {
    //组合问题，从下面数组中选取turnedOn个数来进行组合合成一个时间
    temp := []int{1,2,4,8,1,2,4,8,16,32}
    //返回数组
    ans := make([]string,0)
    //暂存组合数结果
    res := make([]int,0)
    //start开始遍历的地方，
    var dfs func(start int)
    dfs = func(start int){
        //递归终止条件
        if len(res) == turnedOn{
            //时钟数//分钟数
            hour,minute := 0,0

            for i:=0;i<turnedOn;i++{
                //大于等于3说明是分钟数灯泡，否则就是时钟数
                if res[i] >= 4{
                    minute += temp[res[i]]
                }else{
                    hour += temp[res[i]]
                }
            }
            //时间合理性
            if minute < 60 && hour < 12{
                ans = append(ans,fmt.Sprintf("%d:%02d",hour,minute))
            }
            return
        }



        //递归细节
        for i:=start;i<10;i++{
            res = append(res,i)
            //递归
            dfs(i+1)
            //回溯
            res = res[:len(res)-1]
        }

        return
    }

    dfs(0)

    return ans
}



 */
