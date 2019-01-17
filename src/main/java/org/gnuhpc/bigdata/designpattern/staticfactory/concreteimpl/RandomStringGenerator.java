package org.gnuhpc.bigdata.designpattern.staticfactory.concreteimpl;

import org.apache.commons.lang3.RandomStringUtils;
import org.gnuhpc.bigdata.designpattern.staticfactory.abstractlevel.IRandomGenerator;

public class RandomStringGenerator implements IRandomGenerator<String> {
    private final String prefix;
    private final int length;

    RandomStringGenerator(String prefix, int length){
        this.prefix = prefix;
        this.length = length;
    }

    public static RandomStringGenerator stringGenerator(String prefix, int length){
        return new RandomStringGenerator(prefix,length);
    }

    @Override
    public String next() {
        return prefix+ RandomStringUtils.randomAlphabetic(length);
    }
}
