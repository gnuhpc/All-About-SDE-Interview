package org.gnuhpc.bigdata.misc;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2019/11/5
 */
public class Solution {
    List<Integer>       res         = new ArrayList<>();
    Map<Integer, Float> idRatingMap = new HashMap<>();

    @Test
    public void test() {

    }

    public List<Integer> filterAndSortBusinesses(
            List<Business> businesses,
            boolean onlyVeganFriendly,
            Integer maxPrice,
            Float maxDistance
    ) {
        // Todo: Complete Me

        for (Business b : businesses) {
            idRatingMap.put(b.id, b.rating);
            if (b.veganFriendly != onlyVeganFriendly) {
                continue;
            }

            if (maxPrice != null) {
                if (b.price > maxPrice) continue;
            }

            if (maxDistance != null) {
                if (b.distance > maxDistance) continue;
            }

            res.add(b.id);
        }

        Collections.sort(res, (a, b) -> Float.compare(idRatingMap.get(a), idRatingMap.get(b)));

        return res;
    }

    private class Business {
        public Integer id;
        public Float   rating;
        public Boolean veganFriendly;
        public Integer price;
        public Float   distance;
    }
}
