package org.gnuhpc.interview.lang.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntermediateOperation {
    public static void main(String[] args) {
        //Filter Operation
        IntStream.rangeClosed(1, 10)
                .filter(s -> s > 4)
                .forEach(p -> System.out.println(p));

        //flatMap Example
        Stream<List<String>> streamList = Stream.of(
                Arrays.asList("FistList-FirstElement"),
                Arrays.asList("SecondList-FirstElement", "SecondList-SecondElement"),
                Arrays.asList("ThirdList-FirstElement"));
//The streamList is of the form List<String>
        Stream<String> flatStream = streamList
                .flatMap(strList -> strList.stream());
// But after applying flatMap operaton it translates into Strem<String>
        flatStream.forEach(System.out::println);


    }
}
