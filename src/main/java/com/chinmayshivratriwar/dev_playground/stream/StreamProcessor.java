package com.chinmayshivratriwar.dev_playground.stream;

import java.util.List;

public class StreamProcessor {
    public static void main(String[] args) {
        // Example usage of Java Streams can be added here
        //Stream flow -> source -> intermediate operations -> terminal operation

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(numbers.stream().filter(x -> x % 2 == 0).count());
    }
}