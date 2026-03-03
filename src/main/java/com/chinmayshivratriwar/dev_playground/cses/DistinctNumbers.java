package com.chinmayshivratriwar.dev_playground.cses;
import java.io.*;
import java.util.*;

public class DistinctNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        String[] numbers = br.readLine().split(" ");
        Set<Long> arr = new HashSet<>();
        for(int i = 0; i < n; i++){
            arr.add(Long.parseLong(numbers[i]));
        }
        System.out.println(arr.size());
    }
}
