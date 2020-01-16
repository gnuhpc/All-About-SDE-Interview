package org.gnuhpc.bigdata.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//bytedance面试题：36进制加法
public class NumberAdd36 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        System.out.println(add(str1,str2));
    }

    public static String add(String str1, String str2){
        Map<Integer, Character> map = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(int i = 0; i <10;i++){
            map.put(i,(char)('0'+i));
            map2.put((char)('0'+i),i);
        }

        for (int i = 0; i<26;i++){
            map.put(i+10,(char)('a'+i));
            map2.put((char)('a'+i),i+10);
        }


        StringBuilder sb = new StringBuilder();
        char[] charA = str1.toCharArray();
        char[] charB = str2.toCharArray();

        int p1 = str1.length() - 1;
        int p2 = str2.length() - 1;
        int carry = 0;

        while(p1>=0 && p2>=0){
            int num1 = map2.get(charA[p1]);
            int num2 = map2.get(charB[p2]);
            int temp = num1 + num2 + carry;
            if(temp>=36){
                carry = 1;
                sb.append(map.get(temp%36));
            } else {
                carry = 0;
                sb.append(map.get(temp));
            }
            p1--;
            p2--;
        }


        while(p1>=0){
            int sum  = map2.get(charA[p1]) + carry;
            if(sum>=36){
                carry = 1;
                sb.append(sum%36);
            } else {
                carry = 0;
                sb.append(sum);
            }
            p1--;
        }

        while(p2>=0){
            int sum = map2.get(charB[p2]) + carry;
            if( sum >= 36){
                carry = 1;
                sb.append(sum%36);
            } else {
                carry = 0;
                sb.append(sum);
            }
            p2--;
        }


        if(carry!=0){
            sb.append("1");
        }
        return sb.reverse().toString();
    }
}
