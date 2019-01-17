package org.gnuhpc.bigdata.misc;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by huangpengcheng on 2017/2/19.
 */
public class MakingAnagrams {
    private static boolean flag = false;
    private static int B,H;

    static {
        Scanner sc = new Scanner(System.in);
        B =  sc.nextInt();
        H =  sc.nextInt();

        if(B>0 && H>0){
            flag = true;
        } else{
            System.out.println("java.lang.Exception: Breadth and height must be positive");
        }
    }
    public static int numberNeeded(String first, String second) {
        int result = 0;
        Map<Integer,Integer> firstMap = new HashMap();
        Map<Integer,Integer> secondMap = new HashMap();

        for(int i=97;i<123;i++){
            firstMap.put(i,0);
            secondMap.put(i,0);
        }

        first.chars().forEach(c->{
            if(firstMap.containsKey(c)){
                firstMap.put(c,firstMap.get(c)+1);
            }else{
                firstMap.put(c,1);
            }
        });

        second.chars().forEach(c->{
            if(secondMap.containsKey(c)){
                secondMap.put(c,secondMap.get(c)+1);
            }else{
                secondMap.put(c,1);
            }
        });


        for(int key:firstMap.keySet()){
            result += Math.abs(firstMap.get(key)-secondMap.get(key));
        }

        return result;
    }

    public static void main(String[] args) {
//        "z".chars().forEach(System.out::print);
//        Scanner in = new Scanner(System.in);
//        String a = in.next();
//        String b = in.next();
//        System.out.println(numberNeeded(a, b));

        if(flag){
            int area=B*H;
            System.out.print(area);
        }

    }//end of main



}
