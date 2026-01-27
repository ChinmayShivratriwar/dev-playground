package com.chinmayshivratriwar.dev_playground.stream;


import java.util.List;
import java.util.stream.Collectors;

public class IntermediateOps {
    public static void main(String[] args) {
        // Example usage of Java Streams intermediate operations can be added here
        // Stream flow -> source -> intermediate operations -> terminal operation
        // IMP -> Intermediate operations are lazy in nature and do not produce a result until a terminal operation is invoked


        //1. Filter
        //Filter is used to filter elements based on a condition
        //Filter has predicate, that means primary function of predicate is to hold a condition and return boolean value
        List<String> words = List.of("apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "apple", "cherry", "fig");
        words.stream().filter(x -> x.toLowerCase().startsWith("a"));
        //Since no terminal operation is invoked, this line(above) does not produce any output
        System.out.println(words.stream().filter(x -> x.toLowerCase().startsWith("a")).count());
        //Now since terminal operation(count()) is invoked, the above line produces output


        //2. Map
        //Map is used to transform each element in the stream
        //Map has function, that means primary function of function is to take an input and produce an output
        List<String> uppercaseWords = words.stream().map(String::toUpperCase).toList();
        System.out.println(uppercaseWords);

        //3. Sorted
        // Sorted is used to sort the elements in the stream
        //Sorted has comparator, that means primary function of comparator is to compare two elements and return an integer value
        List<String> sortedWords = words.stream().sorted().toList();//natural order
        List<String> sortedWordsBySize = words.stream().sorted((a, b) -> a.length() - b.length()).toList();//by size
        System.out.println(sortedWords);
        System.out.println(sortedWordsBySize);

        //4. Distinct
        //Distinct is used to remove duplicate elements from the stream
        List<String> distinctWords = words.stream().distinct().toList();
        System.out.println(distinctWords);

        //5. Limit
        //Limit is used to limit the number of elements in the stream
        List<String> limitedWords = words.stream().limit(5).toList();
        System.out.println(limitedWords);

        // 6. Skip
        // Skip is used to skip the first n elements in the stream
        List<String> skippedWords = words.stream().skip(3).toList();
        System.out.println(skippedWords);

        //7. Peek
        // Peek is used to perform an action on each element in the stream without modifying the stream
        //Peek has consumer, that means primary function of consumer is to take an input and perform an action without returning any output
        //Peek mostly used for debugging purpose
        List<String> peekedWords = words.stream().peek(x -> System.out.println("Processing word: " + x)).toList();
        System.out.println(peekedWords);

        //Lets try function of peek using map
        List<String> peekedWordsUsingMap = words.stream()
            .map(x -> {
                System.out.println("Mapping word: " + x);
                return x.toUpperCase();
            })
            .toList();
        System.out.println("Peek using map " +  peekedWordsUsingMap);

        // 8. FlatMap
        // FlatMap is used to flatten a stream of streams into a single stream
        //FlatMap has function, that means primary function of function is to take an input and produce an output
        List<List<String>> listOfLists = List.of(
            List.of("apple", "banana"),
            List.of("cherry", "date"),
            List.of("elderberry", "fig", "grape")
        );
        List<String> flatMappedWords = listOfLists.stream()
            .flatMap(list -> list.stream())
            .toList();
        System.out.println(flatMappedWords);
    }
}
