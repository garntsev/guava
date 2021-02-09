package com.example.app;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.*;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static com.example.app.Immutable.out;

/**
 * Examples from webinar: Working with strings
 */
public class Strings {
    public static void main(String[] args) {
        usageOfJoiner();
        usageOfSplitter();
        usageOfToString();
    }

    private static void usageOfToString() {
        // Returns "MyObject{x=1}"
        System.out.println(MoreObjects.toStringHelper("MyObject").add("x", 1).toString());
    }

    private static void usageOfSplitter() {
        // Character, can string also be used
        out(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("hello,world,and,all,the,people"));
        // CharMatcher
        out(Splitter.on(CharMatcher.anyOf(";.,"))
                .trimResults()
                .omitEmptyStrings()
                .split("hello.world;and.all,the,people"));
        // Fixed length
        out(Splitter.fixedLength(3)
                .trimResults()
                .omitEmptyStrings()
                .split("hello.world;and.all,the,people"));

        // Char matcher
//        any()
//        none()
//        whitespace()
//        breakingWhitespace()
//        invisible()
//        digit()
//        javaLetter()
//        javaDigit()
//        javaLetterOrDigit()
//        javaIsoControl()
//        javaLowerCase()
//        javaUpperCase()
//        ascii()
//        singleWidth()

    }

    private static void usageOfJoiner() {
        // are always immutable
        Joiner joiner = Joiner.on(", ").skipNulls();
        System.out.println(joiner.join("local", "dev", "test", "prod", null));
        System.out.println(joiner.join(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23)));
    }
}
