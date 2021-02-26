package org.gnuhpc.interview.leetcode.solutions;

public class DefangIPaddr1108 {
    public String defangIPaddr(String address) {
        return address.replaceAll("\\.", "[.]");
    }
}
