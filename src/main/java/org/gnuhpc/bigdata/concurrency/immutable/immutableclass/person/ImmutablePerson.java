package org.gnuhpc.bigdata.concurrency.immutable.immutableclass.person;

public final class ImmutablePerson {
    private final String name;
    private final String address;

    public ImmutablePerson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public ImmutablePerson(MutablePerson person) {
        synchronized (person) { //注意这里有一个坑，就是这两个赋值必须原子性的完成
            this.name = person.getName();
            this.address = person.getAddress();
        }
    }

    public MutablePerson getMutablePerson() {
        return new MutablePerson(this);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return "[ ImmutablePerson: " + name + ", " + address + " ]";
    }
}
