package org.gnuhpc.bigdata.leetcode;

import java.util.*;

public class ReverseVowels345 {
    private String str;
    private static final Set<Character> vowels = new HashSet<>();

    static {
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
    }

    public ReverseVowels345(String str) {
        this.str = str;
    }

    private boolean isVowel(char c){
        return vowels.contains(Character.toLowerCase(c));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        ReverseVowels345 rv = new ReverseVowels345(str);

        rv.reverseVowel();
        rv.printout();

    }

    private void printout() {
        System.out.println(str);
    }

    private void reverseVowel() {
        char[] strArray = str.toCharArray();

        int head = 0, tail = str.length()-1;

        while(head < tail){
            while(head<tail && !isVowel(str.charAt(head))){
                head++;
            }

            while(head<tail && !isVowel(str.charAt(tail))){
                tail--;
            }

            swapChars(head, tail, strArray);
            head++;
            tail--;
        }
        str = String.valueOf(strArray);
    }

    private void swapChars(int head, int tail, char[] strArray) {
        char temp = strArray[head];
        strArray[head] = strArray[tail];
        strArray[tail] = temp;
    }

}
