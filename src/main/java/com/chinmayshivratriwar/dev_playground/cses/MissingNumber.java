package com.chinmayshivratriwar.dev_playground.cses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class MissingNumber {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        long n = sc.nextInt();
        List<Long> arr = new ArrayList<>();
        for(long i = 0; i < n-1; i++){
            arr.add(sc.nextLong());
        }
        System.out.println(returnMissing(arr, n));
    }

    private static long returnMissing(List<Long> arr, long n){
        AtomicLong arrSum = new AtomicLong();
        arr.stream().forEach(x -> arrSum.addAndGet(x));
        long totalSum = (n * (n + 1)) / 2;

        return totalSum - arrSum.get();
    }
}
