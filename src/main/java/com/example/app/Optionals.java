package com.example.app;

import com.google.common.base.Optional;

/**
 * Examples from webinar: Working with optionals
 */
public class Optionals {
    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        Optional<Integer> absentInteger = Optional.absent();

        System.out.println(possible.isPresent()); // returns true
        System.out.println(possible.get()); // returns 5

        // Will fail fast and throw NullPointerException
//        System.out.println(Optional.of(null));

        Optional nullable = Optional.fromNullable(null);
        System.out.println(nullable.isPresent());

        // Will fail fast and throw IllegalStateException
//        System.out.println(nullable.get());

        nullable = Optional.fromNullable("A string");
        System.out.println(nullable.isPresent());
    }

}
