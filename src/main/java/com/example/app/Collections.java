package com.example.app;

import com.google.common.collect.TreeMultiset;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Examples from webinar: Collections
 */
public class Collections {
    public static String[] poem = new String[]{
            "мчатся", "тучи", "вьются", "тучи",
            "невидимкою", "луна",
            "освещает", "снег", "летучий",
            "мутно", "небо", "ночь", "мутна",
            "еду", "еду", "в", "чистом", "поле",
            "колокольчик", "дин-дин-дин",
            "страшно", "страшно", "поневоле",
            "средь", "неведомых", "равнин"
    };

    public static void main(String[] args) {
        out(traditionalApproach());
        usingGuavaMutliset();
        variousMethodsOfMultiSet();
        multiMapUsage();
    }

    private static void multiMapUsage() {
    }

    private static void variousMethodsOfMultiSet() {
        TreeMultiset<String> multiset = TreeMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        System.out.println(multiset.size());
        // For a HashMultiset, count is O(1), for a TreeMultiset, count is O(log n), etc.
        System.out.println(multiset.count("a"));
        System.out.println(multiset.count("b"));
        System.out.println(multiset.count("c"));
        multiset.add("c", 5);
        multiset.remove("c", 2);
        for (String s : multiset.elementSet()) {
            System.out.printf("%s: %d%n", s, multiset.count(s));
        }
        multiset.setCount("c", 1);
        System.out.println(multiset.size());
    }

    private static void usingGuavaMutliset() {
        TreeMultiset<String> multiset = TreeMultiset.create(Arrays.asList(poem));
        // Any see error?
        for (String s : multiset) {
            System.out.printf("%s: %d%n", s, multiset.count(s));
        }
    }

    private static Map<String, Integer> traditionalApproach() {
        Map<String, Integer> counts = new TreeMap<>();
        for (String word : poem) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, count + 1);
            }
        }
        return counts;
    }

    private static <K, V> void out(Map<K, V> map) {
        for (Map.Entry<K, V> e : map.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        System.out.println();
    }
}
