package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class AnimalShelf0306 {
    private Queue<int[]> dog;
    private Queue<int[]> cat;

    public AnimalShelf0306() {
        // 初始化猫狗两个队列
        dog = new LinkedList<>();
        cat = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        //  0 表示猫，1表示狗
        if(animal[1] == 0){
            cat.offer(animal);
        }else{
            dog.offer(animal);
        }
    }

    public int[] dequeueAny() {
        // 如果猫狗队列都为空，则直接返回-1-1
        if(dog.isEmpty() && cat.isEmpty()){
            return new int[]{-1,-1};
        }
        // 如果猫狗队列有一个为空，则直接返回另个队列的poll元素
        if(dog.isEmpty() || cat.isEmpty()){
            return dog.isEmpty() ? cat.poll() : dog.poll();
        }
        // 如果猫狗队列都不为空，则比较每个队列的peek元素[0]谁大谁小，小的表示先进来的，所以需要poll小的
        return dog.peek()[0] < cat.peek()[0] ? dog.poll() : cat.poll();
    }

    public int[] dequeueDog() {
        if(dog.isEmpty()){
            return new int[]{-1,-1};
        }
        return dog.poll();
    }

    public int[] dequeueCat() {
        if(cat.isEmpty()){
            return new int[]{-1,-1};
        }
        return cat.poll();
    }
}
