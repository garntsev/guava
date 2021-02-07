package com.example.app;

import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Examples from webinar
 */
public class App {
    public static void main(String[] args) {
//        threeWaysToCreateImmutableCollection();
        modifyImmutableCollection();
    }

    private static void modifyImmutableCollection() {
        List<Integer> oddNumbers = Collections.unmodifiableList(Arrays.asList(1, 3, 5, 7));
        oddNumbers.toArray()[0] = 2;
        for (Integer i : oddNumbers) {
            System.out.println(i);
        }
    }

    private static void threeWaysToCreateImmutableCollection() {
        out(usingCopyOf());
        out(usingOf());
        out(usingABuilder());
    }

    private static <E> void out(ImmutableSet<E> set) {
        for (E e : set) {
            System.out.println(e);
        }
        System.out.println();
    }

    private static ImmutableSet<String> usingABuilder() {
        // Earth group
        List<String> earthGroup = Arrays.asList("Меркурий", "Меркурий", "Венера", "Земля", "Марс");
        // Giants
        List<String> giants = Arrays.asList("Юпитер", "Сатурн", "Уран", "Нептун");
        return ImmutableSet.<String>builder()
                .addAll(earthGroup)
                .addAll(giants)
                .add("9 планета")
                .build();
    }

    private static ImmutableSet<String> usingOf() {
        return ImmutableSet.of("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс");
    }

    private static ImmutableSet<String> usingCopyOf() {
        // Any Collection, Iterable, Iterator or array
        String[] months = new String[]{"Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"};
        return ImmutableSet.copyOf(months);
    }
}
