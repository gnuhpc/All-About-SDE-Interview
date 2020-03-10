package org.gnuhpc.interview.designpattern.memento.allinoneexample;

import lombok.Data;

import java.util.ArrayList;

@Data
abstract class MementoAbstract {
    private String state;
}

class Memento extends MementoAbstract {
}

class Originator extends MementoAbstract {
    /* lots of memory consumptive private data that is not necessary to define the
     * state and should thus not be saved. Hence the small memento object. */

    public void setState(String state) {
        System.out.println("Originator: Setting state to " + state);
        super.setState(state);
    }

    public Memento save() {
        System.out.println("Originator: Saving to Memento.");
        Memento memento = new Memento();
        memento.setState(super.getState());
        return memento;
    }

    public void restore(Memento m) {
        System.out.println("Originator: State after restoring from Memento: " + getState());
        setState(m.getState());
    }
}

class Caretaker {
    private ArrayList<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento m) {
        mementos.add(m);
    }

    public Memento getLastestMemento() {
        return mementos.size() == 0 ? null : mementos.get(mementos.size() - 1);
    }

    public Memento getEarliestMemento() {
        return mementos.size() == 0 ? null : mementos.get(0);
    }
}

public class MementoDemo {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        originator.setState("State1");
        originator.setState("State2");
        caretaker.addMemento(originator.save());
        originator.setState("State3");
        caretaker.addMemento(originator.save());
        originator.setState("State4");
        originator.restore(caretaker.getEarliestMemento());
        originator.restore(caretaker.getLastestMemento());
    }
}
