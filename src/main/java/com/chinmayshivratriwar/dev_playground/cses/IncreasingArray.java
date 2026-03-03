package com.chinmayshivratriwar.dev_playground.cses;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IncreasingArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        List<BigInteger> arr = new ArrayList<>();
        for(long i = 0; i < n; i++){
            arr.add(BigInteger.valueOf(sc.nextLong()));
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
