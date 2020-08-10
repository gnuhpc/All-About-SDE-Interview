package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Copyright gnuhpc 2020/8/9
 */
interface HtmlParser {
    List<String> getUrls(String url);
}

public class Crawl1242 {
    private String getHost(String url) {
        if (url == null) return null;

        int startIndex = 7;
        int endIndex = url.indexOf('/', 7);

        if (endIndex == -1) endIndex = url.length();

        return url.substring(startIndex, endIndex);
    }

    private boolean isSameHost(String aUrl, String bUrl) {
        if (aUrl == null && bUrl == null) return true;
        if (aUrl == null || bUrl == null) return false;

        return getHost(aUrl).equals(getHost(bUrl));
    }


    public List<String> crawl(String startUrl, final HtmlParser htmlParser) {

        //requirement - bfs / multi-thread ?
        //test cases
        //example 1 - 2
        //null/null -> return null

        //precheck
        if (startUrl == null || htmlParser == null) return null;

        //main
        //bfs first

        Map<String, Integer> result = new ConcurrentHashMap<>();
        //there is no ConcurrentHashSet in java !


        final Queue<String> q = new LinkedBlockingQueue<>();

        q.add(startUrl);
        result.put(startUrl, -1);

        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            final CountDownLatch count = new CountDownLatch(size);


            for (int i = 0; i < size; i++) {
                Thread t = new Thread(() -> {

                    try {
                        String url = q.poll();

                        List<String> nextUrls = htmlParser.getUrls(url);

                        if (nextUrls != null) {
                            //List<String> nextUrls = new ArrayList<>();
                            for (String nextUrl : nextUrls) {
                                if (nextUrl != null) {
                                    //concurrent.contains == containsValue ! So we need to use containsKey here.
                                    if (isSameHost(startUrl, nextUrl) && !result.containsKey(nextUrl)) {
                                        q.offer(nextUrl);
                                        result.put(nextUrl, -1);
                                    }
                                }
                            }

                        }

                        count.countDown();
                    } catch (Exception ex) {
                        throw new RuntimeException("Error in Thread", ex);
                    }

                });

                t.start();
            }

            try {
                count.await();
            } catch (Exception ex) {
                throw new RuntimeException("count.await() error", ex);
            }

            level++;
        }

        return new ArrayList<>(result.keySet());
    }
}
