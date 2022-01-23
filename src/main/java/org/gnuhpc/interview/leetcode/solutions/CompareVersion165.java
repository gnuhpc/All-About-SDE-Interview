package org.gnuhpc.interview.leetcode.solutions;

import com.google.inject.internal.cglib.reflect.$FastClass;
import org.junit.Test;

public class CompareVersion165 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < v1.length || i < v2.length; ++i) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (num1 != num2) return num1 > num2 ? 1 : -1;
        }
        return 0;
    }

    @Test
    public void test() {
        System.out.println(compareVersion("0.1", "1.1"));
        System.out.println(compareVersion("1.0.1", "1"));
        System.out.println(compareVersion("7.5.2.4", "7.5.3"));
        System.out.println(compareVersion("1.01", "1.001"));
    }


    /*
    Golang Version:
    func compareVersion(version1 string, version2 string) int {
        v1 := strings.Split(version1, ".")
        v2 := strings.Split(version2, ".")

        for len(v1) < len(v2) {
            v1 = append(v1, "0")
        }
        for len(v2) < len(v1) {
            v2 = append(v2, "0")
        }

        for i := 0; i < len(v1); i++ {
            a, _ := strconv.Atoi(v1[i])
            b, _ := strconv.Atoi(v2[i])
            if a > b {
                return 1
            } else if a < b {
                return -1
            }
        }
        return 0
    }

     */
}
