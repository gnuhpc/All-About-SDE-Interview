package org.gnuhpc.bigdata.misc.array;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@Data
public class FindMultiply20 {
    private static final int SIZE = 6;
    private List<Integer> array = new ArrayList<>();
    public static void main(String[] args) {
        FindMultiply20 fm = new FindMultiply20();
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < SIZE; i++) {
            fm.getArray().add(in.nextInt());
        }

        fm.doFind();
    }

    private void doFind() {
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            int element = array.get(i);
            List<Integer> result = remaining.stream().filter(x -> x * element == 20).collect(Collectors.toList());
            if (result.size()==1){
                System.out.println(String.format("One pair is %d:%d", element, result.get(0)));
            } else{
                remaining.add(element);
            }
        }
    }
}
