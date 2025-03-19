package Entity;

import java.util.Random;

public class Philosopher implements Runnable{
    Random random = new Random();

    public void run(){
        try {
            while (true){
                int waitTime = random.nextInt(1000);
                System.out.println("Philosopher " + Thread.currentThread().getName() + " thinking for " + waitTime);
                Thread.currentThread().wait(waitTime);
                System.out.println("Philosopher " + Thread.currentThread().getName() + " done thinking");
            }

        }catch (Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
            System.out.println("Philosopher interrupted : " + System.currentTimeMillis() + " : " + Thread.currentThread().getName());
            Thread.currentThread().interrupt();
            return;
        }
    }
}
