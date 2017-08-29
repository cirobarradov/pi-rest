package com.pi.core;

import java.util.*;
import java.util.stream.Collectors;


public class StringComparator {

    public final static String BLANK="";
    public final static String POINT=".";
    public final static String COLON=":";
    public final static String SEMI_COLON=";";
    public final static String COMMA=",";
    public final static String SLASH="/";

    /**
     * return a map of number of occurrences of a char in a string
     * @param s
     * @return
     */

    private Map<String, Long> getFrequentChars (String s)
    {
        Map<String, Long> frequentChars = Arrays.stream(
                s.replaceAll(" ","").split("")). // transform string to list of characters
                filter(x -> x.matches("[a-z]")). // filter lowercase letters (a to z).
                collect( Collectors.groupingBy(c -> c, Collectors.counting())); //sort and order by ocurrences

        frequentChars.values().removeIf(x->x<=1); // removes chars with 1 occurrence
        return frequentChars;

    }

    /**
     * merge values of the same key
     * @param key
     * @param listasandindex
     * @return
     */
    private String combineValues(String key, Map<String, Map<String, Long>> listasandindex) {
        Map<String, Map<String, Long>> maps = new HashMap<>();
        for (Map.Entry<String, Map<String, Long>> entry : listasandindex.entrySet())
        {
            String id = entry.getKey();
            Map<String, Long> map = entry.getValue();
            if (map.containsKey(key)) {
                maps.put(id,map);
            }
        }
        return combineValue (key, maps);
    }

    private String combineValue(String key, Map<String, Map<String, Long>> maps) {
        String res;
        if (maps.size()==1)
        {
            res =  String.join(SEMI_COLON, maps.values().iterator().next().get(key).toString(),
                    String.join(POINT,maps.keySet().iterator().next(),key));
        }
        else {
            String ids=null;
            Long max = Long.MIN_VALUE;
            for (Map.Entry<String, Map<String, Long>> entry : maps.entrySet()) {
                Map<String, Long> aux =entry.getValue();
                if (aux.get(key)>max) {
                    max = aux.get(key);
                    ids = entry.getKey();
                } else if (aux.get(key)==max){
                    ids = String.join(COMMA, ids, entry.getKey());
                }
            }
            res =  String.join(SEMI_COLON,max.toString(),
                    String.join(POINT, ids,key));
        }
        return res;
    }

    private String getId (Map.Entry<String, String>  entry)
    {
        if (entry.getValue().length()==3) {
            return String.valueOf(entry.getValue().charAt(0));
        }
        else
        {
            return String.join(COMMA,String.valueOf(entry.getValue().charAt(0)),String.valueOf(entry.getValue().charAt(2)));
        }
    }
    private String getValue (Map.Entry<String, String>  entry)
    {
        return String.join(BLANK, Collections.nCopies(Integer.decode(entry.getKey().split(SEMI_COLON)[0]), String.valueOf(entry.getValue().charAt(entry.getValue().length()-1)) ) );
    }
    /**
     * Return result in a concrete format
     * @param map
     * @return
     */
    private String formatMap (Map<String,String> map, Integer n)
    {
        String res = BLANK;
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            res = String.join(SLASH,
                    res,
                    String.join(COLON,
                            getId(entry) ,
                            getValue(entry)));

        }
        if (n==2)
        {
            res=res.replaceAll("1,2","=");
        }
        return res.substring(1);
    }

    public String compareStrings (List<String> listas)
    {
        Map<String,Map<String, Long>  > listasandindex = new HashMap<>();
        Integer index=1;
        //merge key sets
        Set<String> keys = new HashSet<> ();
        for (String l: listas)
        {
            Map<String, Long> map= getFrequentChars(l);
            listasandindex.put(index.toString(), map);
            keys.addAll(map.keySet());
            index+=1;
        }
        Map<String, String> res =  new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if ((a.charAt(0) < b.charAt(0))) {
                    return 1;
                } else if (a.charAt(0) == b.charAt(0)) {
                    if (a.length()==b.length())
                    {
                        if (a.charAt(2) > b.charAt(2))
                        {
                            return 1;
                        }
                        else if (a.charAt(2) == b.charAt(2))
                        {
                            return (a.charAt(a.length()-1) > b.charAt(b.length()-1)) ? 1 :-1;
                        }
                        else
                        {
                            return -1;
                        }
                    }
                    else
                    {
                        return 1;
                    }
                } else {
                    return -1;
                }
            }
        });



        for (String key: keys) {
            String combine=combineValues(key,listasandindex);
            res.put(combine,combine.split(SEMI_COLON)[1]);
        }
        return formatMap(res, listas.size());
    }

}
