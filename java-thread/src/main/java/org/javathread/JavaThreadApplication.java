package org.javathread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class JavaThreadApplication {

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();
        System.out.println("Hello, My Child");

        Thread myThread2 = new Thread(new MyThread2());
        myThread2.start();

        Runnable task = () -> {
            try {
                while (true) {
                    System.out.println("Hello, MyTask");
                    Thread.sleep(500); // miliseconds, 0.5초
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        };
        Thread myThread3 = new Thread(task);
        myThread3.start();
    }
}
