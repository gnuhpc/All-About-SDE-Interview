package org.gnuhpc.bigdata.leetcode.solutions;

import com.google.inject.internal.cglib.reflect.$FastClass;
import org.junit.Test;

public class CompareVersion165 {
    public int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) return 0;

        int idx1 = version1.indexOf(".");
        int idx2 = version2.indexOf(".");

        int v1 = 0, v2 = 0;
        if (idx1 == -1) {
            v1 = Integer.parseInt(version1);
        }
        else {
            v1 = Integer.parseInt(version1.substring(0, idx1));
        }

        if (idx2 == -1) {
            v2 = Integer.parseInt(version2);
        }
        else {
            v2 = Integer.parseInt(version2.substring(0, idx2));
        }

        if (v1 > v2) return 1;
        else if (v2 > v1) return -1;
        else {
            if (idx1 == -1 && idx2 == -1)
                return 0;
            else if (idx1 != -1 && idx2 == -1)
                return compareVersion(version1.substring(idx1 + 1), "0");
            else if (idx1 == -1 && idx2 != -1)
                return compareVersion("0", version2.substring(idx2 + 1));
            else
                return compareVersion(version1.substring(idx1 + 1), version2.substring(idx2 + 1));
        }
    }

    @Test
    public void test() {
        System.out.println(compareVersion("0.1", "1.1"));
        System.out.println(compareVersion("1.0.1", "1"));
        System.out.println(compareVersion("7.5.2.4", "7.5.3"));
        System.out.println(compareVersion("1.01", "1.001"));
    }
}
