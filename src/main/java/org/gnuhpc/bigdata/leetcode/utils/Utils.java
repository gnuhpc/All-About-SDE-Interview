package org.gnuhpc.bigdata.leetcode.utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Utils {
    private static Random random = new Random();

    public static <T> List<List<T>> copy(List<List<T>> list) {
        List<List<T>> copy = new ArrayList<>(list.size());

        for (List<T> element : list) {
            copy.add(new ArrayList<>(element));
        }

        return copy;
    }

    public static boolean[][] copy(boolean[][] m) {
        boolean[][] copy = new boolean[m.length][];

        for (int i = 0; i < m.length; i++) {
            copy[i] = Arrays.copyOf(m[i], m[i].length);
        }

        return copy;
    }

    public static int[][] copy(int[][] m) {
        int[][] copy = new int[m.length][];

        for (int i = 0; i < m.length; i++) {
            copy[i] = Arrays.copyOf(m[i], m[i].length);
        }

        return copy;
    }

    public static void copy(int[] a1, int from, int to, int[] a2, int start) {
        for (int i = 0; i < to - from; i++) {
            if (start + i < a2.length) {
                a2[start + i] = a1[from + i];
            }
        }
    }

    public static void shuffle(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        Random rnd = random;
        if (rnd == null) {
            random = rnd = new Random();
        }

        for (int i = array.length; i > 1; i--) {
            int newIndex = rnd.nextInt(i);
            int tmp = array[i - 1];
            array[i - 1] = array[newIndex];
            array[newIndex] = tmp;
        }
    }

    /**
     * Fills the range [from, to) with sequentially increasing values, starting
     * with val and repetitively evaluating ++val.
     */
    public static void iota(int[] array, int from, int to, int value) {
        for (int i = from; i < to; ++i) {
            array[i] = value++;
        }
    }

    /**
     * Fills the list with sequentially increasing values, starting with val and
     * repetitively evaluating ++val.
     */
    public static void iota(List<Integer> list, int numOfElements, int value) {
        for (int i = 1; i <= numOfElements; ++i) {
            list.add(value++);
        }
    }

    /**
     * Fills the list with sequentially increasing values, starting with val and
     * repetitively evaluating ++val.
     */
    public static void iota(List<Double> list, int numOfElements, double value) {
        for (int i = 1; i <= numOfElements; ++i) {
            list.add(value++);
        }
    }

    public static void simplePrint(boolean[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(" ");
            }
        }
    }

    public static void simplePrint(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(" ");
            }
        }
    }

    public static <T> void simplePrint(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }

        for (Iterator<T> iterator = collection.iterator(); iterator.hasNext(); ) {
            T t = iterator.next();
            System.out.print(t);
            if (iterator.hasNext()) {
                System.out.print(" ");
            }
        }
    }

    public static <T> ObjectInputStream objectInputStreamFromList(List<T> list) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream sin = null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            for (T s : list) {
                oos.writeObject(s);
            }
            oos.close();
            sin = new ByteArrayInputStream(baos.toByteArray());
            return new ObjectInputStream(sin);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return null;
    }

    public static void closeSilently(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Exception e) {
                        // We dont' care.
                    }
                }
            }
        }
    }

    public static void print2DArray(int[][] array) {
        for (int[] row : array) {
            for (int item: row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static int[] generateRandomArray(int n, int rangeL, int rangeR){
        int[] arr = new int[n];

        for (int i = 0; i<n ;i++){
            arr[i] = ThreadLocalRandom.current().nextInt(rangeL, rangeR + 1);
        }

        return arr;
    }

    public static int[] generateNearlyOrderArray(int n, int swapTimes){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
           arr[i] = i;
        }

        for (int i = 0; i < swapTimes; i++) {
            int x = random.nextInt(n);
            int y = random.nextInt(n);

            swap(arr,x,y);
        }

        return arr;
    }

    public static void swap(int[] arr, int a, int b){
        if (a==b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void swap(char[] arr, int a, int b){
        if (a==b) return;
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i : arr){
            System.out.print(i + ",");
        }
        System.out.println();
    }

    public static boolean evaluateSort(Function<int[], Void> someSortFunction, int[] array){
        long beforeTime = System.currentTimeMillis();
        someSortFunction.apply(array);
        long afterTime = System.currentTimeMillis();
        long diffInMilliSeconds = afterTime- beforeTime;
        System.out.println(someSortFunction.getClass().getSimpleName().split("\\$")[0]
                + " Time cost is " + diffInMilliSeconds*1.0/1000.0 + " (s)");
        return true;
    }

    public static boolean isSorted(int[] a){
        boolean isSorted = true;
        boolean isAscending = a[1] > a[0];
        if(isAscending) {
            for (int i = 0; i < a.length-1; i++) {
                if(a[i] > a[i+1]) {
                    isSorted = false;
                    break;
                }
            }
        } else {//descending
            for (int i = 0; i < a.length-1; i++) {
                if(a[i] < a[i+1]) {
                    isSorted = false;
                    break;
                }
            }
        }
        return isSorted;
    }

    public static int find(int[] a, int target)
    {
        return IntStream.range(0, a.length)
                .filter(i -> target == a[i])
                .findFirst()
                .orElse(-1);	// return -1 if target is not found
    }

    public static boolean isKthBitSet(int n, int k)
    {
        return (n & 1 << k) != 0;
    }

    public static int setBit(int n, int k) {
        // Create mask
        int mask = 1 << k;
        // Set bit
        return n | mask;
    }

    //reverse the byte, cached aside by the way
    public static int reverseBytes(byte n) {
        int result = 0;
        for (int i = 0; i < 8; ++i) {
            result = (result << 1) + (n&1);
            n >>= 1;
        }
        return result;
    }

    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    public static String reverseStr(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String intToBinary(int n){
        return Integer.toBinaryString(n);
    }

    public static int binaryToInt(String str){
        return Integer.parseInt(str,2);
    }

    public static int count_one(int n) {
        int count = 0;
        while(n!=0) {
            n = n&(n-1);
            count++;
        }
        return count;
    }

    public static int min(int... numbers) {
        int result = Integer.MAX_VALUE;
        for (int each : numbers) {
            result = Math.min(result, each);
        }
        return result;
    }

    public static int max(int... numbers) {
        int result = Integer.MIN_VALUE;
        for (int each : numbers) {
            result = Math.max(result, each);
        }
        return result;
    }


    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        }
        catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }


    public static boolean isLong(String s) {
        try {
            Long.parseLong(s);
        }
        catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    // replace character of a string at given index to a given character
    // return a new string
    public static String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    // get connections with given word.
    // for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
    // it will return ['hit', 'hog']
    public static List<String> getNextWords(String word, Set<String> wordSet) {
        List<String> nextWords = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                if (wordSet.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }

    public List<String> reverseList(List<String> list){
        String[] res = new String[list.size()];

        for (int i = list.size() - 1; i >= 0; i--) {
            res[i] = list.get(list.size()-i-1);
        }

        return Arrays.asList(res);
    }

    @Test
    public void test(){
        System.out.println(reverseList(Arrays.asList("abcd","efg","lmn","xyz")));
    }
}
