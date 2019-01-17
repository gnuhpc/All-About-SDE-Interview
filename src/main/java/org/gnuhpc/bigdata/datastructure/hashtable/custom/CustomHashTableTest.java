package org.gnuhpc.bigdata.datastructure.hashtable.custom;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class CustomHashTableTest {

    @Test
    public void whenAddElementToCustomHashTableThenShouldRetrieveIt() {
        //given
        CustomHashTable<String, Integer> map = new CustomHashTable<>();

        //when
        map.put("a", 1);
        map.put("b", 1);
        map.put("c", 1);

        //then
        assertEquals(map.size(),3);
        assertEquals((int) map.get("a"),1);
        assertTrue(map.containsKey("a"));
        assertFalse(map.containsKey("d"));
    }

    @Test
    public void addMultipleElementsToTheHashTable() {
        //given
        CustomHashTable<String, Integer> map = new CustomHashTable<>();

        //when
        IntStream.range(0, 10_000).forEach(i ->
                map.put(String.valueOf(i), i)
        );

        //then
        assertEquals(map.size(),10_000);
    }

    @Test
    public void whenUsingObjectWithIncorrectHashCodeThenAllElementsWillLandInTheSameBucket() {
        //given
        CustomHashTable<BrokenHashCode, Integer> map = new CustomHashTable<>();

        //when
        IntStream.range(0, 10_000).forEach(i ->
                map.put(new BrokenHashCode(i), i)
        );

        //then
        assertEquals(
                map.getHashCodesDistribution()
                        .stream()
                        .filter(v -> v.equals(10_000))
                        .collect(Collectors.toList())
                        .size()
        ,1);
        assertEquals(map.size(),10_000);
    }

    @Test
    public void whenUsingObjectWithCorrectHashCodeThenDataShouldBeDistributedEquallyInAllBuckets() {
        //given
        CustomHashTable<ProperHashCode, Integer> map = new CustomHashTable<>();

        //when
        IntStream.range(0, 10_000).forEach(i ->
                map.put(new ProperHashCode(i), i)
        );

        //then
        assertEquals(
                map.getHashCodesDistribution()
                        .stream()
                        .filter(v -> v.equals(1_000))
                        .collect(Collectors.toList())
                        .size()
        ,10);
        assertEquals(map.size(),10_000);
    }


    private class BrokenHashCode {
        private final int value;

        public BrokenHashCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BrokenHashCode that = (BrokenHashCode) o;

            return value == that.value;
        }

        @Override
        public int hashCode() {
            return 1; //broker hash code
        }
    }

    private class ProperHashCode {
        private final int value;

        public ProperHashCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProperHashCode that = (ProperHashCode) o;

            return value == that.value;
        }

        @Override
        public int hashCode() {
            return value;
        }
    }


}
