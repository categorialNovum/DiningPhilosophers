package org.example;

import Entity.Philosopher;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Dining Philosophers");
        Philosopher[] diningPhilosophers = new Philosopher[5];

        for (int i =0; i < diningPhilosophers.length; i++){
            diningPhilosophers[i] = new Philosopher();
            Thread thread = new Thread(diningPhilosophers[i], "PHILOSOPHER " + (i+1));
            thread.start();
        }

    }
}