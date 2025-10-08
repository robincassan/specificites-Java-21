package org.example.race;

import java.util.*;

public class RaceMonitor {
    private final int distance;
    private volatile boolean raceFinished = false;
    private Animal winner = null;
    private final List<Animal> classement = Collections.synchronizedList(new ArrayList<>());

    public RaceMonitor(int distance) {
        this.distance = distance;
    }

    public synchronized boolean isRaceFinished() {
        return raceFinished;
    }

    public synchronized void setWinner(Animal animal) {
        if (!raceFinished) {
            raceFinished = true;
            winner = animal;
            System.out.println("\n🏆 " + animal.getAnimalName() + " a gagné la course !");
        }
    }

    public synchronized void printPosition(Animal animal) {
        int pos = Math.min(animal.getPosition(), distance);
        String track = "-".repeat(pos) + animal.getEmoji() + "-".repeat(Math.max(0, distance - pos));
        System.out.printf("%-8s: %s%n", animal.getAnimalName(), track);
    }

    public void addFinishedAnimal(Animal animal) {
        classement.add(animal);
    }

    public void printClassement() {
        System.out.println("\n=== CLASSEMENT FINAL ===");
        if (winner != null) {
            System.out.println("🥇 1er : " + winner.getAnimalName());
        }
        int rank = 2;
        for (Animal a : classement) {
            if (a != winner) {
                System.out.println("🥈 " + rank++ + "e : " + a.getAnimalName());
            }
        }
    }
}
