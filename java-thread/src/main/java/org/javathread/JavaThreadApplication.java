package org.javathread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class JavaThreadApplication {

    public static void main(String[] args) {
//        MyThread1 myThread1 = new MyThread1();
//        myThread1.start();
//        System.out.println("Hello, My Child");
//
//        Thread myThread2 = new Thread(new MyThread2());
//        myThread2.start();

        Runnable task = () -> {
            try {
                while (true) {
                    System.out.println("Hello, MyThread3");
                    Thread.sleep(500); // miliseconds, 0.5초
                }
            } catch (InterruptedException e) {
                // 2. 여기도 실행이 됨
                System.out.println("Thread interrupted");
            }
        };
        Thread myThread3 = new Thread(task);
        myThread3.start();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // stop은 deprecated 되었다. (동시성 문제로 인해서)
        myThread3.interrupt();
        // 1. 여기 실행한 후에
        System.out.println("Task Thread is Interrupted");
    }
}
