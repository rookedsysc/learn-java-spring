package org.javathread;

/**
 * Thread를 상속받아서 구현하는 방식
 * 해달 클래스를 new MyThread1와 같이 객체를 생성하고 start() 메소드를 호출하면
 * run() 메소드가 실행된다.
 */
public class MyThread1 extends Thread {
    public void run() {
        try {
            while(true) {
                System.out.println("Hello, World");
                Thread.sleep(500); // miliseconds, 0.5초
            }
        }
        catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }

}
