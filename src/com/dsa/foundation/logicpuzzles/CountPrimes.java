package com.dsa.foundation.logicpuzzles;

import java.util.Arrays;

//Reference:https://leetcode.com/problems/count-primes/

public class CountPrimes {

    public boolean isPrime(int number){

        int sqrt = (int) Math.sqrt(number);

        for (int i = 2; i <= sqrt; i++){
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }

    //Description: Check each number from 2 to n to determine if its prime. The isPrime method checks if
    //the passed number is prime or not by dividing number by 2 and increments as long there is no
    //remainder and index is <= square root of the number.
    //The square root of n is sufficient because, for every number a which divides n evenly, there is a
    //complement b, where a * b = n. If a > square root of n, then b < square root of n (since
    //square root of n squared = n. Therefore don't need a to check n's primality, since we would have
    //already checked with b.
    //Time Complexity: O(x=n-2) * O(y=square root of n) = O(x * square root of y)
    //Space Complexity: O(1)
    public int countPrimes(int n) {

        if (n < 0){
            throw new IllegalArgumentException("Negative number passed");
        }

        if (n < 2 && n >= 0){
            return 0;
        }

        int count = 0;

        for (int i=2; i < n; i++){
            if (isPrime(i)){
                count++;
            }
        }
        return count;
    }

    public int countPrimes3(int n) {
        if (n < 0){
            throw new IllegalArgumentException("Negative number passed");
        }

        if (n < 2 && n >= 0) {
            return 0;
        }
        int ans = 0;
        boolean [] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i*i <= n; i++) {
            if (isPrime[i]){
                for (int j = i*i; j <= n; j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            ans += isPrime[i] ? 1: 0;
        }
        return ans;
    }

    public int countPrimes2(int n){
        boolean[] primeNum = new boolean[n];
        int result = 0;
        for(int i = 2; i < n; i++){
            if(primeNum[i] == false){
                result++;
                for(int times = 2; i * times < n; times++){
                    primeNum[i * times] = true;
                }
            }
        }
        return result;
    }

    public int countPrimes4(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative number passed");
        }

        if (n < 2 && n >= 0) {
            return 0;
        }
        int count = 0;

        for (int i=2; i < n; i++){
            if (isPrime(i)){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        CountPrimes cp = new CountPrimes();
        int n = 10;
        System.out.println("Number of primes up to "+n+ " equals "+cp.countPrimes(n));
        System.out.println("Number of primes up to "+n+ " equals "+cp.countPrimes3(n));
        System.out.println("Number of primes up to "+n+ " equals "+cp.countPrimes4(n));
    }


}
