package Entity;

import com.sun.tools.javac.Main;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable{
    Random random = new Random();
    Semaphore leftSemFork;
    Semaphore rightSemFork;
    int maxPhilosophers;

    // 3 seconds eat time
    private final int EAT_TIME = 3000;

    public Philosopher(Semaphore lFork, Semaphore rFork){
        this.leftSemFork = lFork;
        this.rightSemFork = rFork;
        this.maxPhilosophers = 5;
    }
    public Philosopher(Semaphore lFork, Semaphore rFork, int numPhilosophers){
        this.leftSemFork = lFork;
        this.rightSemFork = rFork;
        this.maxPhilosophers = numPhilosophers;
    }

    public void run(){
        try {
            while (true){
                /** THINKING **/
                int thinkTime = random.nextInt(1000);
                System.out.println("Philosopher " + Thread.currentThread().getName() + " thinking for " + thinkTime);
                Thread.sleep(thinkTime);
                System.out.println("Philosopher " + Thread.currentThread().getName() + " done thinking");
                /** ATTEMPT TO GRAB FORKS
                 * Ensure one philosopher tries to pick up right before left to avoid deadlock **/

                if (Integer.parseInt(Thread.currentThread().getName()) != maxPhilosophers) {
                    synchronized (leftSemFork) {
                        leftSemFork.acquire();
                        System.out.println("Philosopher " + Thread.currentThread().getName() + " picked up left fork");
                        synchronized (rightSemFork) {
                            rightSemFork.acquire();
                            System.out.println("Philosopher " + Thread.currentThread().getName() + " picked up right fork");
                        }
                    }
                }else{
                    synchronized (rightSemFork) {
                        rightSemFork.acquire();
                        System.out.println("Philosopher " + Thread.currentThread().getName() + " picked up right fork");
                        synchronized (leftSemFork) {
                            leftSemFork.acquire();
                            System.out.println("Philosopher " + Thread.currentThread().getName() + " picked up left fork");
                        }
                    }

                }
                /** EAT **/
                System.out.println("Philosopher " + Thread.currentThread().getName() + " eating");
                Thread.sleep(EAT_TIME);
                System.out.println("Philosopher " + Thread.currentThread().getName() + " done eating");
                /** RETURN FORKS **/
                leftSemFork.release();
                System.out.println("Philosopher " + Thread.currentThread().getName() + " returned left fork");
                rightSemFork.release();
                System.out.println("Philosopher " + Thread.currentThread().getName() + " returned right fork");
            }

        }catch (Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
            System.out.println("Philosopher interrupted : " + System.currentTimeMillis() + " : " + Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        }
    }
}
