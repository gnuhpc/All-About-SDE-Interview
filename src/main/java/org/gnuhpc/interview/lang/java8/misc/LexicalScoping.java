package org.gnuhpc.interview.lang.java8.misc;

public class LexicalScoping {
    int a = 1;

    // a has class level scope. So It will be available to be accessed
// throughout the class
    public void sumAndPrint() {
        int b = 1;
        int c = a + b;
// b and c are local variables of method. These will be accessible
// inside the method only
    }
// b and c are no longer accessible

}
