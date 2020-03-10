package org.gnuhpc.interview.datastructure.string;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FindInserted {
    private String str1;
    private String str2;

    private FindInserted(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    private void findInsertedChar() {
        Map<String, Long> countTable1 = Arrays.stream(
                str1.split("")).collect(
                Collectors.groupingBy(c -> c, Collectors.counting()));
        Map<String, Long> countTable2 = Arrays.stream(
                str2.split("")).collect(
                Collectors.groupingBy(c -> c, Collectors.counting()));

        countTable2.keySet().forEach(c -> {
            if (countTable1.getOrDefault(c, 0l) != 0l) {
                if (countTable1.get(c) != countTable2.get(c)) {
                    for (int i = 0; i < Math.abs(countTable2.get(c) - countTable1.get(c)); i++) {
                        System.out.println(c);
                    }
                }
            } else {
                System.out.println(c);
            }
        });
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        String str2 = in.nextLine();

        FindInserted fi = new FindInserted(str1, str2);
        fi.findInsertedChar();
    }
}
