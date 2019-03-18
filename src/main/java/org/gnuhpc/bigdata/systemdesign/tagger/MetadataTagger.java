package org.gnuhpc.bigdata.systemdesign.tagger;

/**
 * Copyright RedRock 2013-14
 */


import org.junit.Test;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class MetadataTagger {
    public static void main(String[] args) {
        MetadataTagger tagger = new MetadataTagger();
        String input = "I travelled to San Francisco for work and stayed at Airbnb. " +
                       "I really loved the city and the home where I stayed. " +
                       "I stayed with San and Francisco. They both were really good and san's hospitality was outstanding. It's a great Airbnb experience!";
        Map<String, String> tags = new HashMap<>();
        tags.put("san francisco", "city");
        tags.put("san", "person");
        tags.put("francisco", "person");
        tags.put("Airbnb", "business");
        tags.put("city", "location");
        String output = tagger.tag(input, tags);
        System.out.println(output);
    }

    private String tag(String input, Map<String, String> tags) {
        //First, sort keys in tags by length in reverse order
        List<String> sortedKeys = tags.keySet().stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
        Map<Integer, String> keyStartIndices = new HashMap<>();
        Set<Integer> processedIndices = new HashSet<>();
//        //Mark input using regex
//        for (String key: sortedKeys) {
//            Matcher m = Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(input);
//            while (m.find()) {
//                if (!processedIndices.contains(m.start())) {
//                    keyStartIndices.put(m.start(), key);
//                    IntStream.rangeClosed(m.start(), m.end()).forEach(processedIndices::add);
//                }
//            }
//        }
        //Mark input using indexOf
        String inputLowerCase = input.toLowerCase();
        for (String key: sortedKeys) {
            String keyLowerCase = key.toLowerCase();
            int fromIndex = 0;
            int start, end;
            while(fromIndex < input.length() &&
                  (start = inputLowerCase.indexOf(keyLowerCase, fromIndex)) != -1) {
              end = start + key.length();
              if (!processedIndices.contains(start)) {
                  keyStartIndices.put(start, key);
                  IntStream.range(start, end).forEach(processedIndices::add);
              }
              fromIndex = end;
            }
        }
        //Process tags
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = start;
        while (end < input.length()) {
            if (keyStartIndices.containsKey(end)) {
                String key = keyStartIndices.get(end);
                String tag = tags.get(key);
                sb.append(input.substring(start, end));
                sb.append(String.format("[%s]{%s}", tag, input.substring(end, end + key.length())));
                start = end + key.length();
                end = start;
            } else {
                end++;
            }
        }
        if (start != end) {
            sb.append(input.substring(start, end));
        }
        return sb.toString();
    }


    private Map<String, String> tagsMap;
    //Previous Match StateMachine Definition
    /*
    -1: init state
    0: Switch to match preference so that the short match goes first
    1: Partial Match state
     */
    private int previousState = -1;

    @Test
    public void test1() {
        String review = "I visited San Francisco for work and stayed at Airbnb. I really loved the city and the home where I stayed.";
        tagsMap = new HashMap<String, String>() {{
            put("Airbnb", "business");
            put("san francisco", "city");
        }};

        assertEquals("I visited [city]{San Francisco} for work and stayed at [business]{Airbnb}. I really loved the city and the home where I stayed.", "I visited [city]{San Francisco} for work and stayed at [business]{Airbnb}. I really loved the city and the home where I stayed.",
                     prependTags(review, tagsMap));
    }


    @Test
    public void test2() {
        String review = "I travelled to San Francisco for work and stayed at Airbnb. " +
                        "I really loved the city and the home where I stayed. " +
                        "I stayed with San and Francisco. They both were really good and san's hospitality was outstanding.";
        tagsMap = new HashMap<String, String>() {{
            put("san francisco", "city");
            put("san", "person");
            put("francisco", "person");
            put("Airbnb", "business");
            put("city", "location");
        }};

        assertEquals("I travelled to [city]{San Francisco} for work and stayed at [business]{Airbnb}. " +
                     "I really loved the [location]{city} and the home where I stayed." +
                     " I stayed with [person]{San} and [person]{Francisco}. " +
                     "They both were really good and [person]{san}'s hospitality was outstanding.",
                     prependTags(review, tagsMap)

        );
    }

    private String prependTags(String review, Map<String, String> tagsMap) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> replacementMap = new HashMap<>();
        for (String key : tagsMap.keySet()) {
            replacementMap.put(key.toLowerCase(), "[" + tagsMap.get(key) + "]" + "{$KEY}");
        }

        String[] splitRes = review.split(" ?(?<!\\G)((?<=[^\\p{Punct}])(?=\\p{Punct})|\\b) ?");
        List<String> keyList = new ArrayList<>(replacementMap.keySet());
        keyList.sort(Comparator.comparingInt(String::length).reversed());

        String last = "";
        for (int i = 0; i < splitRes.length; ) {
            last += splitRes[i];
            switch (getContainStatus(last.toLowerCase(), keyList)) {
                case 0:
                    sb.append(replacementMap.get(last.toLowerCase()).replace("$KEY", last));
                    if (i != splitRes.length - 1 && !Pattern.matches("\\p{Punct}", splitRes[i + 1])) sb.append(" ");
                    i++;
                    last = "";
                    break;
                case 1:
                    last += " ";
                    i++;
                    break;
                case 2:
                    if (previousState==1){
                        i--;
                        last = "";
                        previousState = 0;
                        break;
                    }
                    sb.append(last);

                    if (i != splitRes.length - 1 &&
                        !Pattern.matches("\\p{Punct}", splitRes[i + 1]) &&
                        !(last.length()==1 && last.charAt(0) == '\''))
                        sb.append(" ");

                    i++;
                    last = "";
                    break;
                default:
                    break;
            }
        }


        return sb.toString();
    }

    private int getContainStatus(String s, List<String> keyList) {
        for (String key : keyList) {
            if (key.toLowerCase().startsWith(s.toLowerCase()) && !key.equals(s) && previousState!=0) {
                previousState = 1;
                return 1;
            }

            if (key.equals(s)) {
                previousState = -1;
                return 0;
            }
        }

        return 2;
    }
}
