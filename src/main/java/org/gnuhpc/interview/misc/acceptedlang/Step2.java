package org.gnuhpc.interview.misc.acceptedlang;


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Step2 {
    @Test
    public void test(){
        String accStr1 = "en";
        assertEquals(Arrays.asList("en-US"), parseAcceptLanguage(
                accStr1, Arrays.asList("en-US","fr-CA","fr-FR"))
        );

        String accStr2 = "fr";
        assertEquals(Arrays.asList("fr-CA","fr-FR"), parseAcceptLanguage(
                accStr2, Arrays.asList("en-US","fr-CA","fr-FR"))
        );

        String accStr3 = "fr-FR,fr";
        assertEquals(Arrays.asList("fr-FR","fr-CA"), parseAcceptLanguage(
                accStr3, Arrays.asList("en-US","fr-CA","fr-FR"))
        );

        String accStr4 = "fr,fr-CA";
        assertEquals(Arrays.asList("fr-CA","fr-FR"), parseAcceptLanguage(
                accStr4, Arrays.asList("en-US","fr-CA","fr-FR"))
        );

    }

    public List<String> parseAcceptLanguage(String accStr, List<String> suppStrList){
        if(accStr==null || accStr.length() == 0) return Arrays.asList();
        List<String> res = new ArrayList<>();
        Set<String> resSet = new HashSet<>();
        String[] accArr = splitAccStr(accStr);
        Set<String> suppStrSet = new HashSet<>();
        Map<String, List<String>> suppStrMap = new HashMap<>();

        for(String suppStr: suppStrList){
            suppStrSet.add(suppStr);
            String[] temp = suppStr.split("-");
            String lang = temp[0];
            if(suppStrMap.containsKey(lang)) {
                suppStrMap.get(lang).add(suppStr);
            } else {
                List<String> suppList = new ArrayList<>();
                suppList.add(suppStr);
                suppStrMap.put(lang, suppList);
            }
        }

        for(String acc: accArr){
            if(suppStrSet.contains(acc)) {
                if(!resSet.contains(acc)){
                    res.add(acc);
                    resSet.add(acc);
                }
            }
            else if(suppStrMap.containsKey(acc)){
                for(String suppStr : suppStrMap.get(acc)){
                    if(!resSet.contains(suppStr)){
                        res.add(suppStr);
                        resSet.add(suppStr);
                    }
                }
            }
        }
        return res;
    }

    private String[] splitAccStr(String accStr){
        return accStr.split(",");
    }
}
