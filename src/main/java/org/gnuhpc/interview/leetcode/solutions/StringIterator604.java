package org.gnuhpc.interview.leetcode.solutions;

public class StringIterator604 {
    private int num = 0;
    private char currC;
    private int tIdx = 0;
    private int idx = 0;
    private String str;

    public StringIterator604(String compressedString) {
        this.str = compressedString;
    }

    public char next() {
        if(hasNext()){
            if(num == 0){
                currC = str.charAt(idx);
                idx++;

                while(idx<str.length() && Character.isDigit(str.charAt(idx))){
                    num = num*10 + str.charAt(idx)-'0';
                    idx++;
                }
            }

            num--;
            return currC;

        } else {
            return ' ';
        }
    }

    public boolean hasNext() {
        return !(num ==0 && idx == str.length());
    }

    public static void main(String[] args) {
        StringIterator604 iterator = new StringIterator604("x6");

        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }
}
