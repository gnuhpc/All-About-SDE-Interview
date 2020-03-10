package org.gnuhpc.interview.datastructure.hashtable.jdkexample;

import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TestHashTable {
    public enum STATE {
        NEW, RUNNING, WAITING, FINISHED;
    }

    @Test
    public void whenAddElementToMapThenShouldRetrieveIt() {
        //given
        Map<String, Integer> map = new HashMap<>();

        //when
        map.put("a", 1);
        map.put("b", 1);
        map.put("c", 1);

        //then
        assertEquals(map.size(), 3);
        assertEquals((int) map.get("a"), 1);
        assertTrue(map.containsKey("a"));


        SortedMap<Integer, String> mapHttpStatus = new TreeMap<>();

        mapHttpStatus.put(100, "Continue");
        mapHttpStatus.put(200, "OK");
        mapHttpStatus.put(300, "Multiple Choices");

        mapHttpStatus.put(400, "Bad Request");
        mapHttpStatus.put(401, "Unauthorized");
        mapHttpStatus.put(402, "Payment Required");
        mapHttpStatus.put(403, "Forbidden");
        mapHttpStatus.put(404, "Not Found");

        mapHttpStatus.put(500, "Internal Server Error");
        mapHttpStatus.put(501, "Not Implemented");
        mapHttpStatus.put(502, "Bad Gateway");

        System.out.println("All key-value pairs: ");

        for (Integer code : mapHttpStatus.keySet()) {
            System.out.println(code + " -> " + mapHttpStatus.get(code));
        }

        System.out.println();

        Integer firstKey = mapHttpStatus.firstKey();
        String firstValue = mapHttpStatus.get(firstKey);

        System.out.println("First status: " + firstKey + " -> " + firstValue);
        System.out.println();

        Integer lastKey = mapHttpStatus.lastKey();
        String lastValue = mapHttpStatus.get(lastKey);

        System.out.println("Last status: " + lastKey + " -> " + lastValue);
        System.out.println();


        SortedMap<Integer, String> map4xxStatus = mapHttpStatus.subMap(400, 500);

        System.out.println("4xx Statuses: ");

        for (Integer code : map4xxStatus.keySet()) {
            System.out.println(code + " -> " + map4xxStatus.get(code));
        }

        System.out.println();

        SortedMap<Integer, String> mapUnder300Status = mapHttpStatus.headMap(300);

        System.out.println("Statuses < 300: ");

        for (Integer code : mapUnder300Status.keySet()) {
            System.out.println(code + " -> " + mapUnder300Status.get(code));
        }

        System.out.println();

        SortedMap<Integer, String> mapAbove500Status = mapHttpStatus.tailMap(500);

        System.out.println("Statuses > 500: ");

        for (Integer code : mapAbove500Status.keySet()) {
            System.out.println(code + " -> " + mapAbove500Status.get(code));
        }

        Comparator comparator = mapHttpStatus.comparator();

        System.out.println("Sorted by natural ordering? " + (comparator == null));

        test(new LinkedHashMap<>()); //insertion order
        System.out.println();
        test(new LinkedHashMap<>(16, 0.75f, true)); //access order
        System.out.println();
        test(new TreeMap<>()); //sorted order
        System.out.println();
        test(new HashMap<>()); //undefined order

        // Java EnumMap Example 1: creating EnumMap in java with key as enum type STATE
        EnumMap<STATE, String> stateMap = new EnumMap<STATE, String>(STATE.class);

        // Java EnumMap Example 2:
        //putting profits inside EnumMap in Java
        //we are inserting Enum keys on different order than their natural order
        stateMap.put(STATE.RUNNING, "Program is running");
        stateMap.put(STATE.WAITING, "Program is waiting");
        stateMap.put(STATE.NEW, "Program has just created");
        stateMap.put(STATE.FINISHED, "Program has finished");

        // Java EnumMap Example 3:
        //printing size of EnumMap in java
        System.out.println("Size of EnumMap in java: " + stateMap.size());

        // Java EnumMap Example 5:
        //printing Java EnumMap , should print EnumMap in natural order
        //of enum keys (order on which they are declared)
        System.out.println("EnumMap: " + stateMap);

        // Java EnumMap Example 5:
        //retrieving value from EnumMap in java
        System.out.println("EnumMap key : " + STATE.NEW + " value: " + stateMap.get(STATE.NEW));

        // Java EnumMap Example 6:
        //Iterating over Java EnumMap
        Iterator<STATE> enumKeySet = stateMap.keySet().iterator();
        while (enumKeySet.hasNext()) {
            STATE currentState = enumKeySet.next();
            System.out.println("key : " + currentState + " value : " + stateMap.get(currentState));
        }

        //Java EnumMap Example 7: checking if EnumMap contains a particular key
        System.out.println("Does stateMap has :" + STATE.NEW + " : "
                + stateMap.containsKey(STATE.NEW));

        //Java EnumMap Example 8: checking if EnumMap contains a particular value
        System.out.println("Does stateMap has :" + STATE.NEW + " : " + stateMap.containsValue(null));
    }

    private static void test(Map<Integer, String> map) {
        System.out.println(map.getClass().getSimpleName());
        map.put(42, "Meaning");
        map.put(7, "Days Per Week");
        map.put(86400, "Seconds");
        map.put(1, "Highlander");

        System.out.println("map = " + map);
        System.out.println("map.get(7) = " + map.get(7));
        System.out.println("map = " + map);
    }
}
