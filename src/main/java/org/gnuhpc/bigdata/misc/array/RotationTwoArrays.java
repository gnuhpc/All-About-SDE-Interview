package org.gnuhpc.bigdata.misc.array;

public class RotationTwoArrays {
    public static void main(String[] args) {
        int[] array1 = new int[]{
                1,2,3,4,5
        };

        int[] array2 = new int[]{
                6,3,4,5,2
        };

        System.out.println(isRotationTwoArrays(array1,array2));
    }

    private static boolean isRotationTwoArrays(int[] array1, int[] array2) {
        if(array1.length!=array2.length){
            return false;
        }

        int n = array1[0];
        int j = 0;
        for (; j < array2.length; j++ ) {
            if (array2[j]==n){
                break;
            }
        }

        if( j == array2.length ){
            return false;
        }

        for (int i=0;i<array1.length;j=((++j)%array1.length),i++){
            if (array1[i]!=array2[j]){
                return false;
            }
        }

        return true;
    }
}
