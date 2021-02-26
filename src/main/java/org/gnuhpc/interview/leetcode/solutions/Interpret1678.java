package org.gnuhpc.interview.leetcode.solutions;

public class Interpret1678 {
    public String interpret(String command) {
        command = command.replace("()", "o");
        command = command.replace("(al)", "al");
        return command;
    }
}
