package org.javathread;

/**
 * Runnable Interface를 구현하는 방식
 * Thread thread = new Thread(new MyThread2());
 * 위와 같은 형식으로 Thread에 인자값으로 전달하고 thread.start()를 호출하면
 * run() 메소드가 실행된다.
 */
public class MyThread2 implements Runnable {
    @Override
    public void run() {
        try {
            while(true) {
                System.out.println("Hello, MyThread2");
                Thread.sleep(500); // miliseconds, 0.5초
            }
        }
        catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }
}
