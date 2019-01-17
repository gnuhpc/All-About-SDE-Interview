package org.gnuhpc.bigdata.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by huangpengcheng on 2017/2/18.
 */
public class SparseArrays {
    public static void main(String[] args) {
        String str = null;
        Integer result = null;
        Scanner sc = new Scanner(System.in);
        int numStrs = sc.nextInt();
        sc.nextLine();
        Map<String,Integer> strMap = new HashMap<>();
        for (int i=0;i<numStrs;i++){
            str = sc.nextLine();
            if (strMap.containsKey(str)){
                strMap.put(str, strMap.get(str)+1);
            }else {
                strMap.put(str,1);
            }
        }

        int numQuery = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numQuery; i++) {
            result = strMap.get(sc.nextLine());
            if (result==null){
                System.out.print("0"+" ");
            }else {
                System.out.println(result + " ");
            }
        }

    }
}
