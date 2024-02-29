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
                for(int i=0; i<5; i++) {
                    System.out.println("Hello, MyThread3");
                    Thread.sleep(500); // miliseconds, 0.5초
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        };
        Thread myThread3 = new Thread(task);
        myThread3.start();

        try {
            // thread는 wait()이 아니라 join()을 사용해서 대기한다.
            myThread3.join(); // myThread3이 종료될 때까지 대기
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("Task Thread is Done");
    }
}
