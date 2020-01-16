package org.gnuhpc.bigdata.leetcode;

import java.util.ArrayList;
import java.util.List;

public class AddOperators282 {
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multi){
        if(pos == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = pos; i < num.length(); i++){
            if (i != pos && num.charAt(pos) == '0') break;//先导0的数字不是有效数字
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) { //第一个数字不加符号
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            }
            else {
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur, cur);
                helper(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);
                helper(rst, path + "*" + cur, num, target, i + 1, eval - multi + multi * cur, multi * cur);
                //第三种情况 1+2*3 = 3-2 + 2*3
            }
        }
    }
}
