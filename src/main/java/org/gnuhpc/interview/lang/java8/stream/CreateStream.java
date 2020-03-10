package org.gnuhpc.interview.lang.java8.stream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.*;

/**
 * Created by gnuhpc on 2017/1/16.
 */
public class CreateStream {
    public static boolean isOdd(Integer n) {
        return n % 2 != 0;
    }

    ;

    public static void main(String[] args) throws IOException {
        //Creating Streams using user/programmatically specified elements
        Stream<String> Userstream = Stream.of("Creating", "Streams", "from", "Specific", "elements");
        //Creating Streams using array of objects
        Stream<String> ArrayStream = Stream.of(new String[]{"Stream", "from", "an", "array", "of", "objects"});

        //Creating Streams from an array
        String[] StringArray = new String[]{"We", "can", "convert", "an", "array", "to", "a", "Stream", "using", "Arrays", "as", "stream"};
        Stream<String> StringStream = Arrays.stream(StringArray);

        //Creating Streams from Collection
        List<Double> myCollection = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            myCollection.add(Math.random());
        }
//sequential stream
        Stream<Double> sequentialStream = myCollection.stream();
//parallel stream
        Stream<Double> parallelStream = myCollection.parallelStream();

        //Stream from Hashmap
        Map<String, Integer> mapData = new HashMap<>();
        mapData.put("This", 1900);
        mapData.put("is", 2000);
        mapData.put("HashMap", 2100);
        mapData.entrySet()
                .stream()
                .forEach(p -> System.out.println(p));
        mapData.keySet()
                .stream()
                .forEach(p -> System.out.println(p));

        //primitive streams
        IntStream.range(1, 4)
                .forEach(p -> System.out.println(p));
        LongStream.rangeClosed(1, 4)
                .forEach(p -> System.out.println(p));
        DoubleStream.of(1.0, 2.0, 3.0, 4.0)
                .forEach(p -> System.out.println(p));


        //Infinite Streams using generate()
        Stream<Double> sequentialDoubleStream = Stream.generate(Math::random);
        Stream<Integer> sequentialIntegerStream = Stream.generate(new AtomicInteger()::getAndIncrement);

        /*
        In the case of the iterate() method, an initial element seed is passed to the function, which
generates the stream. The first element in such infinite streams is always the seed value that
was passed in the iterate function:
         */
        //Infinite Streams using iterate()
        Stream<Integer> sequentialIntegerStream1 = Stream.iterate(Integer.MIN_VALUE, i -> i++);
        Stream<BigInteger> sequentialBigIntegerStream = Stream.iterate(BigInteger.ZERO, i -> i.add(BigInteger.TEN));


        Stream.iterate(0, n -> n + 1).limit(10).forEach(System.out::println);
        IntStream.of(1, 2, 3).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
        Stream.of(5, 4, 3, 2, 1).skip(2).forEach(System.out::println);
        Stream.of(5, 4, 3, 2, 1).findFirst().ifPresent(System.out::println);
        Stream.of(5, 4, 3, 2, 1).findAny().ifPresent(System.out::println);

        List<String> strings = new ArrayList<>();
        strings.add("a,b,c");
        strings.add("d,e,f");

        strings.stream().flatMap(str -> Stream.of(str.split(","))).collect(Collectors.toList()).stream().forEach(System.out::println);

        Function<Integer, Integer> identity = n -> n;
        Function<Integer, Integer> square = n -> n * n;
        Stream.of(1, 2, 3).collect(Collectors.toMap(identity, square)).entrySet().stream().forEach(System.out::println);

        BiFunction<StringBuilder, Integer, StringBuilder> accumulator = (sb, val) -> sb.append(val);
        BinaryOperator<StringBuilder> combiner = (s1, s2) -> s1.append(s2);
        System.out.println(Stream.of(1, 2, 3).reduce(new StringBuilder(), accumulator, combiner));

        IntStream.range(1, 8).filter(CreateStream::isOdd).forEach(x -> System.out.println(x));

        //Streams from AbstractFile
        Stream<String> streamOfStrings = Files.lines(Paths.get("Apology_by_Plato.txt"));
        Stream<String> streamWithCharset = Files.lines(Paths.get("Apology_by_Plato.txt"), Charset.forName("UTF-8"));

    }


}
