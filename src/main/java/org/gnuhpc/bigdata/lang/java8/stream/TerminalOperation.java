package org.gnuhpc.bigdata.lang.java8.stream;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperation {
    public static void main(String[] args) {
        Supplier<Stream<String>> streamSupplier =()-> Stream.of( new String[]{"Stream","from","an","array","of","object"});

        streamSupplier.get().sequential().forEach(P->System.out.println("Sequential output :: "+P));

        Supplier<Stream<String>> streamSupplier2 =()->Stream.of( new String[]
                {"Stream","from","an","array","of","objects"} ) ;
//Parallel For each
        streamSupplier2.get().parallel().forEach(P->System.out.println("Parallel output :: "+P));

        //String Concatenation using joining with delimiter parameter
        String delimitedString = Stream.of( new String[]{"Stream","from","an","array","of","object"})
                .collect(Collectors.joining(","));

        System.out.println(delimitedString);

        String concatString = Stream.of( new String[]{"Stream","from","an","array","of","object"})
                .collect(Collectors.joining(",","[","]"));

        System.out.println(concatString);

        //Collection Collectors
        List<String> listCollected = streamSupplier.get().collect(Collectors.toList());
        System.out.println("The list collected value of Stream are :: " + listCollected);
        Set<String> setCollected=streamSupplier.get().collect(Collectors.toSet());
        System.out.println("The set collected value of Stream are :: " + setCollected);

        /*
        * By default, the toSet() method produces a HashSet and hence the results are unsorted. If we want the
results to be sorted, we can use the toCollection() method to specify an ordered set, in our case let's use
Tree Set
        * */
        Set<String>orderedSetCollected=streamSupplier.get().collect(Collectors.toCollection(TreeSet::new));
        System.out.println("The ordered set collected value of Stream are :: "+orderedSetCollected);

        //Map Collectors with duplicate key handling
        Map<Object, List<Integer>> mapWithDupVals=streamSupplier.get().collect(Collectors.toMap(x->x.toString(),
//KeyMapper
                x -> {List <Integer>tmp = new ArrayList<>(); tmp.add(x.toString().length()); return tmp;},
//ValueMapper
                (L1, L2) -> { L1.addAll(L2); return L1;} //MergeFunction
        ));
        System.out.println("The generated Map profits with duplicate profits::" + mapWithDupVals);

        //Grouping Collectors
        Map<Integer,List<String>>groupExample= streamSupplier.get().collect(Collectors.groupingBy(x-> x.toString().length()));
        System.out.println("Grouping stream elements on the basis of its length :: " + groupExample);


        //Partition Collectors
        Map<Boolean,List<String>>partitionExample=streamSupplier.get().collect(
                    Collectors.partitioningBy( x->x.toString().length()==2)
                );
        System.out.println("Patitioning of elements on the basis of its length :: "+partitionExample);

        boolean matchesAll =streamSupplier.get().allMatch(x->x.toString().length() > 1);
        System.out.println("All the elemetns have lenght greater than 1 ::"+matchesAll);

        boolean noneMatches =streamSupplier.get().noneMatch(x->x.toString().length() > 1);
        System.out.println("None of the elemetns have lenght greater than 1 ::"+noneMatches);

        boolean anyMatches =streamSupplier.get().peek(x->System.out.println("Element being iterated is :: "+x))
                .anyMatch(x->x.toString().length() >1);
        System.out.println("The short circuit terminal operation finished with return value :: "+anyMatches);





    }
}
