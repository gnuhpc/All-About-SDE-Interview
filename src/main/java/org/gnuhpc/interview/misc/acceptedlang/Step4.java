package org.gnuhpc.interview.misc.acceptedlang;


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Step4 {
    @Test
    public void test(){
        String accStr1 = "fr-FR;q=1, fr-CA;q=0, fr;q=0.5";
        assertEquals(Arrays.asList("fr-FR", "fr-BG", "fr-CA"), parseAcceptLanguage(
                accStr1, Arrays.asList("fr-FR", "fr-CA", "fr-BG"))
        );

        String accStr2 = "fr-FR;q=1, fr-CA;q=0, *;q=0.5";
        assertEquals(Arrays.asList("fr-FR","fr-CA","en-US"), parseAcceptLanguage(
                accStr2, Arrays.asList("fr-FR", "fr-CA", "fr-BG"))
        );
    }

    public List<String> parseAcceptLanguage(String accStr, List<String> suppStrList){
        if(accStr==null || accStr.length() == 0) return Arrays.asList();
        List<String> res = new ArrayList<>();
        Set<String> resSet = new HashSet<>();
        TreeMap<Double, String> accMap = splitAccStr(accStr);
        String[] accArr = accMap.values().toArray(new String[0]);
        Set<String> accSet = new HashSet<>(accMap.values());

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
            if (acc.equals("*")){
                for(String suppStr : suppStrList){
                    if(!resSet.contains(suppStr)){
                        res.add(suppStr);
                        resSet.add(suppStr);
                    }
                }
            }

            else if(suppStrSet.contains(acc)) {
                if(!resSet.contains(acc)){
                    res.add(acc);
                    resSet.add(acc);
                }
            }
            else if(suppStrMap.containsKey(acc)){
                for(String suppStr : suppStrMap.get(acc)){
                    if(!resSet.contains(suppStr) && !accSet.contains(acc)){
                        res.add(suppStr);
                        resSet.add(suppStr);
                        accSet.remove(acc);
                    }
                }
            }
        }
        return res;
    }

    private TreeMap<Double, String> splitAccStr(String accStr){
        TreeMap<Double,String> map = new TreeMap<>(Collections.reverseOrder());
        String[] splits = accStr.split(", ");
        for(String sp: splits){
            String[] tmp = sp.split(";");
            String lang = tmp[0];
            Double prio = Double.valueOf(tmp[1].split("=")[1]);
            map.put(prio,lang);
        }

        return map;
    }
}
