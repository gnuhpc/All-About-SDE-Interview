package org.gnuhpc.bigdata.misc;

import java.util.Scanner;

/*

Given a string which is RLE encoded, for example 'AAA' is encoded as '3A',
Implement a comparison function which operate on RLE encoded string directly:
e.g.
int compare(const RLEString& lhs, const RLEString& rhs)

Note that: "31A2A" IS ALSO legal RLE string.
E.g.
compare('31A2A1B', '33A1B') -> 0
compare('1A2B', '1A') -> 1
compare('1A2A1B', '2B') -> -1
 */
public class RLECompare {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        System.out.println(compare(str1,str2));
    }

    private static int compare(String lhs, String rhs){
        int p1 = 0, p2 = 0;
        int countl = 0, countr = 0;
        char[] l = lhs.toCharArray();
        char[] r = rhs.toCharArray();

        while(p1< lhs.length() && p2 < rhs.length()){
            if(countl==0){
                int start1 = p1;
                while(p1<lhs.length() && Character.isDigit(l[p1])){
                    p1++;
                }

                countl = Integer.parseInt(lhs.substring(start1,p1));
            }

            if(countr==0){
                int start2 = p2;
                while(p2<rhs.length() && Character.isDigit(r[p2])){
                    p2++;
                }

                countr = Integer.parseInt(rhs.substring(start2,p2));
            }


            if(l[p1]!=r[p2]){
                return Character.compare(l[p1],r[p2]);
            }

            int min = Math.min(countl,countr);
            countl -= min;
            countr -= min;

            if(countl == 0){
                p1++;
            }

            if(countr == 0){
                p2++;
            }
        }

        if(p1<lhs.length() && p2 == rhs.length()) return 1;
        else if(p2<rhs.length() && p1 == lhs.length()) return -1;
        else return 0;
    }
}
