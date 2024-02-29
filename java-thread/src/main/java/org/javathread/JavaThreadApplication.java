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
    }

}
