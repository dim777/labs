package ru.ineb.exam;

public class ExamApplication {
    public static void main(String[] args) {
        Thread th = new Thread(new Runnable() {
            //static {
            //    System.out.println("initial");
            //}

            @Override
            public void run() {
                System.out.println("start");
            }
        });
        th.start();

    }
}