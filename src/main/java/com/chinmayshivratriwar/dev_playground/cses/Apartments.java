package com.chinmayshivratriwar.dev_playground.cses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Apartments {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mnk = br.readLine().split(" ");
        int n = Integer.parseInt(mnk[0]);
        int m = Integer.parseInt(mnk[1]);
        long k = Long.parseLong(mnk[2]);

        String[] nIntegers = br.readLine().split(" ");
        String[] mIntegers = br.readLine().split(" ");

        long[] apartments = new long[n];
        long[] applicants = new long[m];

        for(int i = 0; i < n; i++){
            apartments[i] = Long.parseLong(nIntegers[i]);
        }
        for(int i = 0; i < m; i++){
            applicants[i] = Long.parseLong(mIntegers[i]);
        }

        Arrays.sort(apartments);
        Arrays.sort(applicants);

        long res = 0;
        int j = 0;
        for(int i = 0; i < m; i++){
            long desired = applicants[i];
            // Move j forward while apartment is too small
            while(j < n && apartments[j] < desired - k){
                j++;
            }
            // Check if current apartment is in valid range
            if(j < n && apartments[j] <= desired + k){
                res++;
                j++; // This apartment is assigned, move to next
            }
        }
        System.out.println(res);
    }
}


