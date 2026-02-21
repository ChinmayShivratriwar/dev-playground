package com.chinmayshivratriwar.dev_playground.cses;

import java.util.Scanner;

public class Repetitiond {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println(getRepetitions(line));
    }


    private static int getRepetitions(String str){
        int res = 1;
        int max = res;
        for(int i = 1; i < str.length(); i++){
            if(str.charAt(i) == str.charAt(i-1)){
                res++;
                max = Math.max(res, max);
            }else{
                res = 1;
            }
        }
        return max;
    }
}
