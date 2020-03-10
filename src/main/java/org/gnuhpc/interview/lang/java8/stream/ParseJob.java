package org.gnuhpc.interview.lang.java8.stream;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * Created by gnuhpc on 2017/1/6.
 */
public class ParseJob {

    public static void main(String[] args) {
        Map<Integer, String> jobMap = new HashMap();
        Map<Integer, String> jobMap2 = new HashMap();
        jobMap.put(10, "Job1");
        jobMap.put(20, "Job2");
        jobMap.put(30, "Job3");
        jobMap.put(40, "Job4");
        jobMap2.put(50, "Job5");
        jobMap2.put(60, "Job6");
        jobMap2.put(70, "Job7");

        System.out.println("Filter map:");
        jobMap.entrySet().stream().map(job -> {
            SparkJob sparkJob = new SparkJob();
            sparkJob.setJobId(job.getKey());
            sparkJob.setJobName(job.getValue());
            return sparkJob;
        }).filter(job -> job.getJobName() != "Job1").forEach(System.out::println);

        System.out.println("Convert Map to Set:");
        Set<Map.Entry<Integer, String>> set = jobMap.entrySet().stream().collect(Collectors.toSet());
        set.forEach(k -> System.out.println(k.getValue()));


        System.out.println("Merge two Map to a list:");

        List<SparkJob> jobList = Stream.of(jobMap.entrySet(), jobMap2.entrySet()).flatMap(Collection::stream).map(entry -> new SparkJob(entry.getKey(), entry.getValue())).collect(toList());
        jobList.forEach(job -> System.out.println(job.getJobId() + ":" + job.getJobName()));
        System.out.println("Max Id of jobs:" + jobMap2.entrySet().stream().max(comparing(Map.Entry::getKey)).get().getValue());
        System.out.println("Max Id of jobs:" + jobMap2.entrySet().stream().collect(maxBy(comparing(Map.Entry::getKey))).get().getValue());
        System.out.println("Number of jobs:" + Stream.of(jobMap.entrySet(), jobMap2.entrySet()).flatMap(Collection::stream).count());

        System.out.println(jobMap.entrySet().stream().map(Map.Entry::getKey).reduce((job1, job2) -> (job1 + job2)).get());


        Map<Boolean, List<SparkJob>> partitionList = jobMap.entrySet().stream().map(job -> new SparkJob(job.getKey(), job.getValue())).collect(partitioningBy(SparkJob::isOdd));
        partitionList.get(true).stream().forEach(System.out::println);
        partitionList.get(false).forEach(System.out::println);
        System.out.println(partitionList.get(false).stream().map(SparkJob::getJobName).collect(joining(", ", "[", "]")));

        Map<Boolean, List<String>> jobNameList = jobMap.entrySet().stream().map(job -> new SparkJob(job.getKey(), job.getValue())).collect(partitioningBy(SparkJob::isOdd, mapping(SparkJob::getJobName, toList())));
        jobNameList.get(true).forEach(System.out::println);
        jobNameList.get(false).forEach(System.out::println);

        System.out.println("===============");
        jobMap.forEach((jobId, jobName) -> System.out.println(jobName));

        System.out.println(Stream.of(1, 3, 6).reduce((left, right) -> left + right).get());

        System.out.println(Stream.of(1, 3, 6).reduce(100, (left, right) -> left + right));

        BiFunction<Integer, Integer, Integer> accumulator = (identity, e) -> identity * e;
        BinaryOperator<Integer> combiner = (left, right) -> left + right;
        int sum = Stream.of(1, 3, 6)
                .parallel()
                .reduce(2, accumulator, combiner); //2*1 + 2*3 + 2*6 = 20
        System.out.println(sum);


    }
}
