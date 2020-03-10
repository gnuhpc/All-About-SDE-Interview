package org.gnuhpc.interview.lang.generics.generic_methods;

import java.util.Objects;

public class Person {

    private final String name;
    private final int age;

    public Person(String name, int age) {
        Objects.requireNonNull(name);

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;

        Person person = (Person) obj;

        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
