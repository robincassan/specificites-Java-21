package org.example.race;

import java.util.Comparator;
import java.util.List;

public class Arbitre extends Thread {
    private final List<Animal> animaux;
    private final RaceMonitor monitor;

    public Arbitre(List<Animal> animaux, RaceMonitor monitor) {
        this.animaux = animaux;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (!monitor.isRaceFinished()) {
            synchronized (monitor) {
                System.out.println("\n--- Classement provisoire ---");
                animaux.stream()
                        .sorted(Comparator.comparingInt(Animal::getPosition).reversed())
                        .forEach(a ->
                                System.out.printf("%-8s : %d%n", a.getAnimalName(), a.getPosition())
                        );
                System.out.println("\n=============================");
            }
            try {
                Thread.sleep(1000); // mise à jour toutes les secondes
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

