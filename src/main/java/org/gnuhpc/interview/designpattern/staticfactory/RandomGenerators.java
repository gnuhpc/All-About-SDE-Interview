package org.gnuhpc.interview.designpattern.staticfactory;

//https://jlordiales.me/2012/12/26/static-factory-methods-vs-traditional-constructors/

/*
Provided that this new class implements `IRandomGenerator`
 we can change the return type of the static factory method and all clients are now magically using the new implementation without them even noticing the change.
 */

//if you have an interface named Type
// you put your static factory methods in a noninstantiable class named Types.


import org.gnuhpc.interview.designpattern.staticfactory.abstractlevel.IRandomGenerator;
import org.gnuhpc.interview.designpattern.staticfactory.concreteimpl.RandomIntGenerator;
import org.gnuhpc.interview.designpattern.staticfactory.concreteimpl.RandomStringGenerator;

/*
The main disadvantage of static factories is that classes without public or protected constructors cannot be extended. But this might be actually a good thing in some cases because it encourages developers to favor composition over inheritance.

To summarize, static factory methods provide a lot of benefits and just one drawback that might actually not be a problem when you think about it. Therefore, resist the urge to automatically provide public constructors and evaluate if static factories are a better fit for your class.
 */
public class RandomGenerators {
    // Suppresses default constructor, ensuring non-instantiability.
    private RandomGenerators() {
    }

    public static final IRandomGenerator<Integer> intBetweenGenerator(int min, int max) {
        return RandomIntGenerator.between(min, max);
    }

    public static final IRandomGenerator<String> stringGenerator(int length) {
        return RandomStringGenerator.stringGenerator("", length);
    }

    public static final IRandomGenerator<Integer> intBiggerThanGenerator(int min) {
        return RandomIntGenerator.biggerThan(min);
    }

    public static void main(String[] args) {
        System.out.println(RandomGenerators.stringGenerator(16).next());
        System.out.println(RandomGenerators.intBetweenGenerator(1, 10).next());
        System.out.println(RandomGenerators.intBiggerThanGenerator(1).next());
    }
}
