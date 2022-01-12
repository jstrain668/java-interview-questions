package com.gof.creational.singleton.lazy;

public class LazyDemo {

    public static void main(String[] args) {

        LazySingleton singleton1 = LazySingleton.getInstance();
        LazySingleton singleton2 = LazySingleton.getInstance();
        System.out.println(singleton1);
        System.out.println(singleton2);

    }
}
