package com.dsa.foundation.logicpuzzles;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {

    //Number is prime by checking for divisibility on numbers less than it. It only needs to go up to the square root of
    //n because if n is divisible by a number greater than its square root then it's divisible by something smaller than
    //it.
    //Time Complexity: O(square root of n)
    //Space Complexity: O(1)
    public boolean isPrime(int n){

        if (n < 2){
            return false;
        }

        for (int i=2; i * i <= n; i++){

            if (n % i == 0){
                return false;
            }
        }

        return true;
    }


    //List of all prime numbers between 2 and n.
    //Time Complexity: O(n*square root of n)
    //Space Complexity: O(1)
    public List<Integer> getPrimes(int n){

        List<Integer> numbers = new ArrayList<>();

        if (n < 2){
            return numbers;
        }

        for (int i = 2; i <= n; i++) {
            if(isPrime(i))
                numbers.add(i);
        }
        return numbers;
    }

    public static void main(String[] args) {
        PrimeNumber pn = new PrimeNumber();
        int n = 11;
        System.out.println("Is "+n+" a prime number: "+pn.isPrime(n));

        System.out.println("List of primes between 2 and "+n);
        List<Integer> primes = pn.getPrimes(n);
        for (Integer i: primes){
            System.out.print(i+", ");
        }
    }
}
