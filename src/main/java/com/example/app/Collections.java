package com.example.app;

import com.google.common.collect.*;

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
        biMapUsage();
        rangeSetUsage();
        rangeMapUsage();
    }

    private static void rangeMapUsage() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); // {[1, 10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo", (10, 20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); // {[1, 3] => "foo", (3, 5) => "bar", (11, 20) => "foo"}
    }

    private static void rangeSetUsage() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
        rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
        rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
        rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
        rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}
    }

    private static void biMapUsage() {
        BiMap<String, Integer> wordsCount = HashBiMap.create();
        for (String word : poem) {
            Integer count = wordsCount.get(word);
            if (count == null) {
                wordsCount.put(word, 1);
            } else {
                wordsCount.put(word, count + 1);
            }
        }
        out(wordsCount);
        out(wordsCount.inverse());
    }

    // For example, Map<K, Set<V>> is a typical way to represent an unlabeled directed graph
    // A Multimap<K, V> is not a Map<K, Collection<V>>
    private static void multiMapUsage() {
        ListMultimap<String, String> vladimir = MultimapBuilder.treeKeys().arrayListValues().build();
        vladimir.put("Олава", "Вышеслав");
        vladimir.put("Предислава", "Святополк");
        vladimir.put("Рогнеда", "Изяслав");
        vladimir.put("Рогнеда", "Мстислав");
        vladimir.put("Рогнеда", "Всеволод");
        vladimir.put("Рогнеда", "Премислава");
        vladimir.put("Рогнеда", "Мстислава");
        vladimir.putAll("Аделья", Arrays.asList("Мстислав", "Станислав", "Судислав"));
        vladimir.put("Мальфрида", "Святослав");
        vladimir.put("Милолика", "Борис");
        vladimir.put("Милолика", "Глеб");
        vladimir.putAll("Неизвестно", Arrays.asList("Позвизд", "Добронега-Мария"));

        System.out.println(vladimir.remove("Неизвестно", "Позвизд"));
        Immutable.out(vladimir.get("Неизвестно"));
        vladimir.put("Неизвестно", "Позвизд");
        System.out.println(vladimir.removeAll("Неизвестно"));
        System.out.println(vladimir.size());
        System.out.println(vladimir.keySet().size());
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
