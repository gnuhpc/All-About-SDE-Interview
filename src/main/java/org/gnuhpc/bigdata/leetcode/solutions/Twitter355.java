package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2019/9/19
 */

//https://www.itcodemonkey.com/article/15645.html
public class Twitter355 {
    class Twit {
        int  TwitId;
        Twit next;
        int  time;

        Twit(int id, int time) {
            this.TwitId = id;
            this.time = time;
            next = null;
        }
    }

    class User {
        int              userId;
        HashSet<Integer> followWho;
        Twit             head;

        User(int userId) {
            this.userId = userId;
            followWho = new HashSet<>();
            followWho.add(userId);//关注自己
            head = new Twit(-1, -1);
        }

        public void follow(int user) {
            followWho.add(user);
        }

        public void unfollow(int user) {
            if (user != this.userId)
                followWho.remove(user);
        }

        public void post(int twitId, int time) {
            Twit temp = new Twit(twitId, time);
            temp.next = head.next;
            head.next = temp;
        }
    }

    /**
     * Initialize your data structure here.
     */
    HashMap<Integer, User> users;
    int                    time;

    public Twitter355() {
        users = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        time++;
        User temp;
        if (users.containsKey(userId)) {
            temp = users.get(userId);
        }
        else {
            temp = new User(userId);
            users.put(userId, temp);
        }
        temp.post(tweetId, time);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        LinkedList<Integer> res = new LinkedList<>();
        if (!users.containsKey(userId))
            return res;
        Set<Integer> set = users.get(userId).followWho;
        PriorityQueue<Twit> pq = new PriorityQueue<>(set.size(), (t1, t2) -> t2.time - t1.time);
        for (int id : set) {
            User temp = users.get(id);
            //只添加表头，避免重复排序
            // System.out.print("a");
            if (temp.head.next != null)
                pq.add(temp.head.next);
        }
        int count = 0;
        while (!pq.isEmpty()) {
            Twit t = pq.poll();
            res.addLast(t.TwitId);
            if (t.next != null)
                pq.add(t.next);
            if (++count >= 10)
                return res;
        }
        return res;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!users.containsKey(followerId)) {
            User fer = new User(followerId);
            users.put(followerId, fer);
        }
        if (!users.containsKey(followeeId)) {
            User fee = new User(followeeId);
            users.put(followeeId, fee);
        }
        User fer = users.get(followerId);
        fer.follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (users.containsKey(followerId) && users.containsKey(followeeId)) {
            User temp = users.get(followerId);
            temp.unfollow(followeeId);
        }
    }

}
