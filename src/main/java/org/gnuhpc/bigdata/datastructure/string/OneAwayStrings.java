package org.gnuhpc.bigdata.datastructure.string;

public class OneAwayStrings {
    public static void main(String[] args) {
        String sla = "abcde", slb="abee";

        System.out.println(isOneAway("abcde", "abcd"));  // should return true
        System.out.println(isOneAway("abde", "abcde"));  // should return true
        System.out.println(isOneAway("a", "a"));  // should return true
        System.out.println(isOneAway("abcdef", "abqdef"));  // should return true
        System.out.println(isOneAway("abcdef", "abccef"));  // should return true
        System.out.println(isOneAway("abcdef", "abcde"));  // should return true
        System.out.println(isOneAway("aaa", "abc"));  // should return false
        System.out.println(isOneAway("abcde", "abc"));  // should return false
        System.out.println(isOneAway("abc", "abcde"));  // should return false
        System.out.println(isOneAway("abc", "bcc"));  // should return false

    }

    private static boolean isOneAway(String sla, String slb) {
        String longStr, shortStr;
        if (sla.length()>=slb.length()){
            longStr = sla;
            shortStr = slb;
        } else {
            longStr = slb;
            shortStr = sla;
        }

        if(longStr.length()-shortStr.length() >= 2){
            return false;
        }

        if (longStr.length()-shortStr.length() == 0 ){
            int numOfEdits = 0;

            for (int i = 0; i < longStr.length(); i++) {
                if (sla.charAt(i)!=slb.charAt(i)){
                    numOfEdits++;
                }
            }

            return numOfEdits == 0 || numOfEdits == 1;
        } else{ // l-s = 1
            int i=0, numEdits=0;
            while (i+numEdits< longStr.length() ) {
                if (i == shortStr.length()){
                    numEdits++;
                    break;
                }


                if (longStr.charAt(i+numEdits)!=shortStr.charAt(i)){
                    numEdits++;
                } else{
                    i++;
                }

            }

            return numEdits==1;
        }
    }
}
