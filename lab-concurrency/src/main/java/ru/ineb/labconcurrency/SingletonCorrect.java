package ru.ineb.labconcurrency;

public class SingletonCorrect {
    private static volatile SingletonCorrect INSTANCE;

    private int var = 0;

    private SingletonCorrect() {
    }

    public static SingletonCorrect getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonCorrect.class) {
                if (INSTANCE == null) INSTANCE = new SingletonCorrect();
            }
        }
        return INSTANCE;
    }

    public void setVar(int newVar){
        var = newVar;
    }

    public int getVar(){
        return var;
    }
}
