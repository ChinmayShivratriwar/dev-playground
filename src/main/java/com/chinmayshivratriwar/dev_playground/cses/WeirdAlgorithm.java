package com.chinmayshivratriwar.dev_playground.cses;

public class WeirdAlgorithm {
    public static void main(String[] args) {
        algorithm(3);
    }

    private static void algorithm(int n) {
        System.out.print(n + " ");
        if(n == 1){
            return;
        }
        if(n%2 == 0){
            algorithm(n/2);
        }else{
            algorithm((n*3) +1);
        }

    }
}
