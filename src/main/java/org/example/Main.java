package org.example;

import Entity.Philosopher;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        final int MAX_PHIL = 5;
        System.out.println("Initializing Dining Philosophers with " + MAX_PHIL + " diners");
        Philosopher[] diningPhilosophers = new Philosopher[MAX_PHIL];
        Semaphore[] forks = new Semaphore[MAX_PHIL];
        for (int x = 0; x < forks.length; x++){
            /** Semaphore(MAX_AVAILABLE, FAIR?) **/
            forks[x] = new Semaphore(1, true);
        }

        for (int i =0; i < MAX_PHIL; i++){
            Semaphore lFork = forks[i];
            Semaphore rFork = forks[(i +1) % forks.length];
            diningPhilosophers[i] = new Philosopher(lFork, rFork, MAX_PHIL);
            //Thread thread = new Thread(diningPhilosophers[i], "PHILOSOPHER " + (i+1));
            Thread thread = new Thread(diningPhilosophers[i], Integer.toString((i+1)));
            thread.start();
        }
    }
}