package com.chinmayshivratriwar.dev_playground.stream;

import java.util.List;
import java.util.stream.Stream;

public class ParallelStream {
    //Allows parallel processing of data
    //Allow multiple threads to process data simultaneously
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        List<Integer> list = Stream.iterate(1, i -> i+1).limit(20000).toList();
        List<Long> factorialList = list.stream().parallel().map(ParallelStream::factorial).toList(); //without parallel() it took 157 ms, with parallel() it took 47 ms
        Long end = System.currentTimeMillis();
        System.out.println("Time taken in sequential stream: " + (end - start) + " ms");

        //Parallel Stream effective when we have cpu intensive tasks
        //or large data sets where tasks are independenct of each other
        //They may add iverhead for simple tasks or small data sets due to thread management
    }

    public static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
                result *= i;
        }
        return result;
    }
}
