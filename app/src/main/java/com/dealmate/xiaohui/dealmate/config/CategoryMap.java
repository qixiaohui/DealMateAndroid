package com.dealmate.xiaohui.dealmate.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaohui on 1/22/2017.
 */

public class CategoryMap {
    private static Map<String, List<String>> listMap = new HashMap<>();


    static  {
        List<String> temp = new ArrayList<>();
        String[] computers = new String[] {
                "laptops-ultrabooks",
                "desktops",
                "accessories-add-ons",
                "monitors", "" +
                "networking-servers",
                "software",
                "printers"};
        temp = Arrays.asList(computers);
        listMap.put("computers", temp);
        String[] electronics = new String[] {
                "hdtvs-home-theater",
                "cameras-camcorders",
                "speakers-headphones"
        };
        temp = Arrays.asList(electronics);
        listMap.put("electronics", temp);
        String[] tablet = new String[] {
                "accessories-apps",
                "tablets",
                "unlocked-phones"
        };
        temp = Arrays.asList(tablet);
        listMap.put("tablets-phones", temp);
        String[] gaming = new String[] {
                "games",
                "hardware"
        };
        temp = Arrays.asList(gaming);
        listMap.put("gaming", temp);
        String[] lisfestyle = new String[] {
                "accessories-watches",
                "automotive",
                "clothing-shoes",
                "entertainment",
                "gadgets-novelty",
                "health-fitness",
                "home-outdoors",
                "home-improvement-tools-garden"
        };
        temp = Arrays.asList(lisfestyle);
        listMap.put("lifestyle-home", temp);
    }

    public static List<String> getCategory(String key) {
        if(listMap.containsKey(key))
            return listMap.get(key);
        return new ArrayList<>();
    }
}
