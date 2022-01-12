package com.dsa.foundation.logicpuzzles;

public class ToThePower {

    //Time Complexity: O(p)
    public int power(int n,int power){
        if (power < 0){
            return 0;
        } else if (power == 0){
            return 1;
        }

        return n * power(n,power-1);
    }

    public static void main(String[] args) {
        ToThePower ttp = new ToThePower();
        int n = 2;
        int power = 3;
        System.out.println(n+" to the power of "+power+ " = "+ttp.power(n,power));

    }
}
