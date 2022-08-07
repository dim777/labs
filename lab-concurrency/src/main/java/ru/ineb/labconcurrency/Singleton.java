package ru.ineb.labconcurrency;

public class Singleton {
    private static volatile Singleton INSTANCE;

    int var = 100;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) INSTANCE = new Singleton();
            }
        }
        return INSTANCE;
    }
}
