package org.gnuhpc.bigdata.misc.array;

import java.util.ArrayList;
import java.util.List;

//快慢指针
public class CommonElements {
    public static void main(String[] args) {
        int[] array1 = new int[]{

        };

        int[] array2 = new int[]{
                1,2,4,5,9,10,11
        };

        List<Integer> result = commonElements(array1,array2);

        result.forEach(System.out::println);
    }

    private static List<Integer> commonElements(int[] array1, int[] array2) {
        List<Integer> result = new ArrayList<>();

        for(int i=0,j=0; i< array1.length && j<array2.length;){
            if(array1[i]==array2[j]){
                result.add(array1[i]);
                i++;
                j++;
            } else if (array1[i]>array2[j]){
                j++;
            } else{
                i++;
            }
        }

        return result;
    }
}
