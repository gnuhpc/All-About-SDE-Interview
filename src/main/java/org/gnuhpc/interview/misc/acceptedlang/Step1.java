package org.gnuhpc.interview.misc.acceptedlang;


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Step1 {
    @Test
    public void test(){
        assertEquals(Arrays.asList("en-US","fr-FR"), parseAcceptLanguage(
                "en-US,fr-CA,fr-FR", Arrays.asList("fr-FR","en-US"))
        );

        assertEquals(Arrays.asList("fr-FR"), parseAcceptLanguage(
                "fr-CA,fr-FR", Arrays.asList("en-US","fr-FR"))
        );

        assertEquals(Arrays.asList("en-US"), parseAcceptLanguage(
                "en-US", Arrays.asList("en-US","fr-CA"))
        );
    }

    public List<String> parseAcceptLanguage(String accStr, List<String> suppStrList){
        if(accStr==null || accStr.length() == 0) return Arrays.asList();
        List<String> res = new ArrayList<>();
        String[] accArr = splitAccStr(accStr);
        Set<String> suppStrSet = new HashSet<>();

        for(String suppStr: suppStrList){
            suppStrSet.add(suppStr);
        }

        for(String acc: accArr){
            if(suppStrSet.contains(acc)){
                res.add(acc);
            }
        }
        return res;
    }

    private String[] splitAccStr(String accStr){
        return accStr.split(",");
    }
}
