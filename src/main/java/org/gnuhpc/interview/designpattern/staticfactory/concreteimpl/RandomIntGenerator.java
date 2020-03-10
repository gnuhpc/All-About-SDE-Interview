package org.gnuhpc.interview.designpattern.staticfactory.concreteimpl;

import org.gnuhpc.interview.designpattern.staticfactory.abstractlevel.IRandomGenerator;

import java.util.concurrent.ThreadLocalRandom;

/*
static factory方法解决了同一个类中签名一致的构造函数不同含义的问题

比如在这个案例中，希望用户只指定min和只指定max的构造函数是一致的，无法区分
因此需要静态工厂方法进行构造
 */

public class RandomIntGenerator implements IRandomGenerator<Integer> {
    private final int min;
    private final int max;

    //注意是default scope
    RandomIntGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static RandomIntGenerator between(int min, int max) {
        return new RandomIntGenerator(min, max);
    }

    public static RandomIntGenerator biggerThan(int min) {
        return new RandomIntGenerator(min, Integer.MAX_VALUE);
    }

    public static RandomIntGenerator smallerThan(int max) {
        return new RandomIntGenerator(Integer.MIN_VALUE, max);
    }

    public Integer next() {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}

/*
what are the advantages and disadvantages of this technique?

We already mentioned the first advantage of static factory methods: unlike constructors they have names. This has two direct consequences,

We can provide a meaningful name for our constructors.

We can provide several constructors with the same number and type of parameters, something that as we saw earlier we can’t do with class constructors.

Another advantage of static factories is that, unlike constructors, they are not required to return a new object every time they are invoked. This is extremely useful when working with immutable classes  to provide constant objects for common used values and avoid creating unnecessary duplicate objects. The Boolean.valueOf code that I showed previously illustrates this point perfectly. Notice that this static method returns either TRUE or FALSE, both immutable Boolean objects.

Sample third advantage of static factory methods is that they can return an object of any subtype of their return type. This gives you the possibility to change the return type freely without affecting clients. Moreover, you can hide implementation classes and have an interface-based API, which is usually a really good idea. But I think this can be better seen by an example.
 */
