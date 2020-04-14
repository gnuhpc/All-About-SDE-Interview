package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2019/9/19
 */

//https://www.itcodemonkey.com/article/15645.html
public class Twitter355 {
    class Twit {
        int TwitId;
        Twit next;
        int time;

        Twit(int id, int time) {
            this.TwitId = id;
            this.time = time;
            next = null;
        }
    }

    class User {
        int userId;
        HashSet<Integer> followWho;
        Twit tHead;

        User(int userId) {
            this.userId = userId;
            followWho = new HashSet<>();
            followWho.add(userId);//关注自己
            tHead = new Twit(-1, -1);
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
            temp.next = tHead.next;
            tHead.next = temp;
        }
    }

    /**
     * Initialize your data structure here.
     */
    HashMap<Integer, User> userMap; //UserId - User， 等价于User Database
    int time;

    public Twitter355() {
        userMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        time++;
        User temp;
        if (userMap.containsKey(userId)) {
            temp = userMap.get(userId);
        } else {
            temp = new User(userId);
            userMap.put(userId, temp);
        }
        temp.post(tweetId, time);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     * 转换为N（follower数量，包含自己）个有序List取前top 10个，自己画个图
     */
    public List<Integer> getNewsFeed(int userId) {
        LinkedList<Integer> res = new LinkedList<>();
        if (!userMap.containsKey(userId))
            return res;
        Set<Integer> followWhoSet = userMap.get(userId).followWho;
        PriorityQueue<Twit> pq = new PriorityQueue<>(followWhoSet.size(), (t1, t2) -> t2.time - t1.time);
        //先从各个follower的头部Twit取一遍数据，避免重复
        for (int id : followWhoSet) {
            User follower = userMap.get(id);
            if (follower.tHead.next != null)
                pq.add(follower.tHead.next);
        }

        while (!pq.isEmpty()) {
            Twit t = pq.poll();
            res.addLast(t.TwitId);
            if (res.size() == 10) return res;
            else {
                if (t.next != null)
                    pq.add(t.next);
            }
        }
        return res;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        //判断是不是存在这个user，不存在的话创建并放入database
        //之所以有这一步是因为在这个模拟程序中是没有数据准备这一说的，在这边既然用户给出了就认为是存在的用户，做Database准备
        if (!userMap.containsKey(followerId)) {
            User fer = new User(followerId);
            userMap.put(followerId, fer);
        }
        if (!userMap.containsKey(followeeId)) {
            User fee = new User(followeeId);
            userMap.put(followeeId, fee);
        }

        User fer = userMap.get(followerId);
        fer.follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId) && userMap.containsKey(followeeId)) {
            User temp = userMap.get(followerId);
            temp.unfollow(followeeId);
        }
    }

}
