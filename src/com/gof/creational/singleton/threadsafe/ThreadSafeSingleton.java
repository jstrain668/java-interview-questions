package com.gof.creational.singleton.threadsafe;

public class ThreadSafeSingleton {

    private static ThreadSafeSingleton INSTANCE = null;

    private ThreadSafeSingleton() {
    }

    public static ThreadSafeSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThreadSafeSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
