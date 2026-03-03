package com.chinmayshivratriwar.dev_playground.cses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class IncreasingArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        String[] numbers = br.readLine().split(" ");  // Read all at once
        List<BigInteger> arr = new ArrayList<>();
        for(int i = 0; i < n; i++){
            arr.add(BigInteger.valueOf(Long.parseLong(numbers[i])));  // Direct string-to-long parsing
        }
        System.out.println(makeIncreasing(arr));
    }

    private static BigInteger makeIncreasing(List<BigInteger> arr){
        BigInteger res = BigInteger.valueOf(0);
        BigInteger prev = arr.get(0);
        for(int i = 1; i < arr.size(); i++){
            if(arr.get(i).compareTo(prev) < 0){
                BigInteger diff = prev.subtract(arr.get(i));
                res = res.add(diff);
            }else{
                prev = arr.get(i);
            }
        }
        return res;
    }
}
