package org.gnuhpc.bigdata.systemdesign.tinyurl;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 19-8-23
 */

class Base62 {
    public static final String BASE_62_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int    BASE         = BASE_62_CHAR.length();

    public static long toBase10(String str) {
        //从右边开始
        return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
    }

    private static long toBase10(char[] chars) {
        long n = 0;
        int pow = 0;
        for (char item : chars) {
            n += toBase10(BASE_62_CHAR.indexOf(item), pow);
            pow++;
        }
        return n;
    }

    private static long toBase10(int n, int pow) {
        return n * (long) Math.pow(BASE, pow);
    }

    public static String fromBase10(long i) {
        StringBuilder sb = new StringBuilder("");
        if (i == 0) {
            return "a";
        }
        while (i > 0) {
            i = fromBase10(i, sb);
        }
        return sb.reverse().toString();
    }

    private static long fromBase10(long i, final StringBuilder sb) {
        int rem = (int) (i % BASE);
        sb.append(BASE_62_CHAR.charAt(rem));
        return i / BASE;
    }
}

public class Base62UrlShorter {
    private long autoIncrId = 10000;

    Map<Long, String> longUrlIdMap = new HashMap<>();

    public long incr() {
        return autoIncrId++;
    }

    public String shorten(String longUrl) {
        long id = incr();
        //add to mapping
        longUrlIdMap.put(id, longUrl);
        return Base62.fromBase10(id);
    }

    public String lookup(String shortUrl) {
        long id = Base62.toBase10(shortUrl);
        return longUrlIdMap.get(id);
    }

    @Test
    public void testLongUrl2Short() {
        Base62UrlShorter shorter = new Base62UrlShorter();
        String longUrl = "www.google.com";
        String shortUrl = shorter.shorten(longUrl);
        System.out.println("short url:" + shortUrl);
        System.out.println(shorter.lookup(shortUrl));
    }
}
