package org.gnuhpc.interview.misc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
//评测题目: Find the Attackers' IP as possible as you could.

//  有一份日志记录被部分写坏了，但是里面可能存放着所有攻击者的IP地址。
//  我们需要在文件中尽可能的找到最多的可能的IPv4地址，请写程序，要求
//  请使用熟悉的语言编程，不要使用正则表达式和网络库函数
//
//  提示：如果文件中内容是：101.43.228.113.12.abcdegf.34，应该输出的结果是
//      101.43.228.1
//      101.43.228.11
//      101.43.228.113
//        1.43.228.1
//        1.43.228.11
//        1.43.228.113
//        43.228.113.1
//        43.228.113.12
//        3.228.113.1
//        3.228.113.12
 */

public class RestoreIP {
    @Test
    public void test() {
        //Set<String> res = restoreIP("101.43.228.113.12.abcdegf.334.267");
        Set<String> res = restoreIP("520.110.26.288.12");
        for(String ip: res) System.out.println(ip);
    }

    private Set<String> restoreIP(String ipFile) {
        Set<String> res = new HashSet<>();
        if (ipFile == null || ipFile.length() == 0) {
            return res;
        }

        String[] ipFields = ipFile.split("\\.");
        if(ipFields.length<4) return res;
        StringBuilder sb = new StringBuilder();
        int validFields = 0;
        for (String ipField : ipFields) {
            try{
                int num = Integer.valueOf(ipField);
                sb.append(num).append(".");
                validFields++;
            } catch (NumberFormatException e){}
        }
        if(sb.length() == 0 || validFields < 4) return res;

        splitIP(sb.deleteCharAt(sb.length()-1).toString().split("\\."), res);
        return res;
    }

    private void splitIP(String[] ipFields, Set<String> res) {
        for(int i = 0;i<ipFields.length-3;i++){
            String first = ipFields[i];
            String second = ipFields[i+1];
            String third = ipFields[i+2];
            String fourth = ipFields[i+3];
            int num2 = Integer.valueOf(second);
            if(num2<0 || num2 > 255) continue;

            int num3 = Integer.valueOf(third);
            if(num3<0 || num3 > 255) continue;

            Set<String> firsts = splitFirstField(first);
            Set<String> fourths = splitFourthField(fourth);

            for (String firstF: firsts){
                for (String fourthF: fourths){
                    StringBuilder sb = new StringBuilder();
                    sb.append(firstF).append(".");
                    sb.append(second).append(".");
                    sb.append(third).append(".");
                    sb.append(fourthF);
                    res.add(sb.toString());
                }
            }
        }
    }

    private Set<String> splitFirstField(String field) {
        Set<String> res = new HashSet<>();
        for (int i = 0; i < field.length(); i++) {
                String temp = field.substring(i);
                int tempNum = Integer.valueOf(temp);
                if(!temp.startsWith("0") && tempNum >=0 && tempNum<=255)
                    res.add(temp);
        }

        return res;
    }

    private Set<String> splitFourthField(String field) {
        Set<String> res = new HashSet<>();
        for (int i = 1; i <= field.length(); i++) {
            String temp = field.substring(0,i);
            int tempNum = Integer.valueOf(temp);
            if(!temp.startsWith("0") && tempNum >=0 && tempNum<=255)
                res.add(temp);
        }

        return res;
    }
}
