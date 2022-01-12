package com.dsa.foundation.logicpuzzles;

public class MultiplyNumbers {

    public int naiveMultiply(int x, int y){

        if ( x == 0 || y ==0){
            return 0;
        }

        long sign = (x < 0) ^ (y < 0) ? -1: 1;

        long a = Math.abs(x);
        long b = Math.abs(y);

        long result = 0;

        for (int i=0; i < b; i++){
            result += a;
        }

        result *= sign;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return  0;
        }

        return (int) result;
    }

    public int multiply(int x, int y) {

        if (x == 0 || y == 0) {
            return 0;
        }

        long sign = (x < 0) ^ (y < 0) ? -1 : 1;

        long a = Math.abs(x);
        long b = Math.abs(y);

        long result = 0;
        long count = 0;

        while(b > 0)
        {
            if((b & 1) == 1)
            {
                result+=(a << count);
            }
            count++;
            b >>= 1;
        }

        result *= sign;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return  0;
        }

        return (int) result;
    }

    public static void main(String[] args) {
        MultiplyNumbers mn = new MultiplyNumbers();
        int x = 3;
        int y = -5;
        System.out.println("Multiply "+x+" by "+y+" = "+mn.naiveMultiply(x,y));
        System.out.println("Multiply "+x+" by "+y+" = "+mn.multiply(x,y));
    }


}
