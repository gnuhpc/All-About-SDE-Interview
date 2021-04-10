package org.gnuhpc.interview.leetcode.utils;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;
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

    public static Set<String> getAllSubStrings(String input) {
        Set<String> res = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        int length = input.length();
        //start point
        for (int start = 0; start < length; start++) {
            //end point (左闭右开)
            for (int end = start + 1; end <= length; end++) {
                //if start point = 1 then
                //grp size = 1 , print 1
                //grp size = 2, print 1 2
                //grp size = 3, print 1 2 3 ans so on
                for (int j = start; j < end; j++) {
                    sb.append(input.charAt(j));
                }
                System.out.println(sb);//或者直接用String test = s.substring(start, end);
                res.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }

        return res;
    }

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
            for (int item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static int[] generateRandomArray(int n, int rangeL, int rangeR) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(rangeL, rangeR + 1);
        }

        return arr;
    }

    public static int[] generateNearlyOrderArray(int n, int swapTimes) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < swapTimes; i++) {
            int x = random.nextInt(n);
            int y = random.nextInt(n);

            swap(arr, x, y);
        }

        return arr;
    }

    public static void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

        /*
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
         */
    }

    public static void swap(char[] arr, int a, int b) {
        if (a == b) return;
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println();
    }

    public static boolean evaluateSort(Function<int[], Void> someSortFunction, int[] array) {
        long beforeTime = System.currentTimeMillis();
        someSortFunction.apply(array);
        long afterTime = System.currentTimeMillis();
        long diffInMilliSeconds = afterTime - beforeTime;
        System.out.println(someSortFunction.getClass().getSimpleName().split("\\$")[0]
                + " Time cost is " + diffInMilliSeconds * 1.0 / 1000.0 + " (s)");
        return true;
    }

    public static boolean isSorted(int[] a) {
        boolean isSorted = true;
        boolean isAscending = a[1] > a[0];
        if (isAscending) {
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    isSorted = false;
                    break;
                }
            }
        } else {//descending
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] < a[i + 1]) {
                    isSorted = false;
                    break;
                }
            }
        }
        return isSorted;
    }

    public static int find(int[] a, int target) {
        return IntStream.range(0, a.length)
                .filter(i -> target == a[i])
                .findFirst()
                .orElse(-1);    // return -1 if target is not found
    }

    public static boolean isKthBitSet(int n, int k) {
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
            result = (result << 1) + (n & 1);
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

    public static String intToBinary(int n) {
        return Integer.toBinaryString(n);
    }

    public static int binaryToInt(String str) {
        return Integer.parseInt(str, 2);
    }

    //https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/solution/mian-shi-ti-15-er-jin-zhi-zhong-1de-ge-shu-wei-yun/
    //返回一个整数有多少1
    public static int count_one(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);// 理解方法： 分两种情况，最后一位是1的，n-1与n则最后一位变为0；
            // 最后一位甚至是多位是0的， n-1与n则最后一位和多为还是0，且第一个不是0 的位因为n-1的缘故也变为0，与操作之后还是0
            count++;
        }
        return count;
    }

    //返回两个整数相差多少位
    public int convertA2B(int A, int B) {
        int n = A ^ B;
        return count_one(n);
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
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }


    public static boolean isLong(String s) {
        try {
            Long.parseLong(s);
        } catch (NumberFormatException | NullPointerException e) {
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

    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;

        while (true) {
            int pos = partition(nums, left, right);
            if (pos + 1 > k) {
                right = pos - 1;
            } else if (pos + 1 < k) {
                left = pos + 1;
            } else {
                return nums[pos];
            }
        }
    }

    private static int partition(int[] numbers, int low, int high) {
        int pivot = numbers[low];

        int i = low + 1, j = high;

        while (i <= j) {
            //大的放前边,注意i是严格大于
            while (i <= j && numbers[i] > pivot) i++;
            //小的放后边，注意j是小于等于
            while (i <= j && numbers[j] <= pivot) j--;

            if (i <= j) {
                swap(numbers, i, j);
            }
        }

        //j 和 i交错而过，j成为分界的左边，i成为分界的右边
        swap(numbers, low, j);
        return j;
    }


    public List<String> reverseList(List<String> list) {
        String[] res = new String[list.size()];

        for (int i = list.size() - 1; i >= 0; i--) {
            res[i] = list.get(list.size() - i - 1);
        }

        return Arrays.asList(res);
    }


    public static void printLinkedList(ListNode head) {
        ListNode node = head;

        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void constructMap(Map<Character, List<Integer>> map, String s) {
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);//如果是null就添加
        }
    }

    @Test
    public void test() {
        System.out.println(reverseList(Arrays.asList("abcd", "efg", "lmn", "xyz")));
        System.out.println(getAllSubStrings("abcd"));
    }
}
