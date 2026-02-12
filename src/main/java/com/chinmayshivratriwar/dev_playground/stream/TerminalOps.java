package com.chinmayshivratriwar.dev_playground.stream;

import java.util.List;

public class TerminalOps {
    public static void main(String[] args) {
        // Example usage of Java Streams terminal operations can be added here
        // Terminal operations include forEach, collect, reduce, count, etc.

        //1. Collect
        List<String> words = List.of("apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "apple", "cherry", "fig");
        words.stream().filter(x -> x.toLowerCase().startsWith("a"));
        System.out.println(words.stream().filter(x -> x.toLowerCase().startsWith("a")).toList());

        //2. forEach
        words.stream().forEach(System.out::println);

        //3. Reduce
        //Combine all elements in the stream to produce a single result
        String concatenatedWords = words.stream().reduce("", (a, b) -> a + b);
        System.out.println(concatenatedWords);

        //4. AnyMatch, AllMatch, NoneMatch -> Short-circuiting operations
        boolean anyStartsWithA = words.stream().anyMatch(x -> x.toLowerCase().startsWith("a"));
        boolean allStartsWithA = words.stream().allMatch(x -> x.toLowerCase().startsWith("a"));
        boolean noneStartsWithZ = words.stream().noneMatch(x -> x.toLowerCase().startsWith("z"));
        System.out.println("Any starts with A: " + anyStartsWithA);
        System.out.println("All starts with A: " + allStartsWithA);
        System.out.println("None starts with Z: " + noneStartsWithZ);

        //5. FindFirst, FindAny -> Short-circuiting operations
        String firstWord = words.stream().findFirst().orElse("No words found");
        String anyWord = words.stream().findAny().orElse("No words found");
        System.out.println("First word: " + firstWord);
        System.out.println("Any word: " + anyWord);

        //Examples, find names from a list which has length more than 4 characters
        System.out.println("Names with length more than 4 characters:");
        List<String> names = List.of("John", "Alice", "Bob", "Catherine", "David", "Eve");
        names.stream().filter(x -> x.length() > 4).peek(System.out::println).count();
        List<String> longNames = names.stream().filter(x -> x.length() > 4).toList();

        //Example, sum of all even numbers from a list
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Sum of even " + numbers.stream().filter(x -> x%2 == 0).reduce(0, (x, y) -> x + y));

        //Example, count occurence of character in a large string
        String largeString = "This is a sample string. This string is for testing the count of a character in a large string.";
        System.out.println("Count occurence of t " + largeString.toLowerCase().chars().filter(x -> x == 't').count());

        //Stateful vs Stateless terminal operations
        //Stateful terminal operations need to maintain state information from previously seen elements in order to produce
        //the final result. Examples include collect, reduce, and toArray.
        //Stateless terminal operations do not need to maintain any state information from previously seen elements.
        //Examples include forEach, count, anyMatch, allMatch, noneMatch, findFirst, and findAny.

        //6. toArray
        Object[] wordsArray = words.stream().toArray();
        for (Object word : wordsArray) {
            System.out.println(word);
        }

        //7. min, max
        String minWord = words.stream().min(String::compareTo).orElse("No words found");
        String maxWord = words.stream().max(String::compareTo).orElse("No words found");
        System.out.println("Min word: " + minWord);
        System.out.println("Max word: " + maxWord); 
    }
}
