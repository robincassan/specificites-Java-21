package org.example.race;

import java.util.ArrayList;
import java.util.List;

public class RaceMonitor {
    private volatile boolean raceFinished = false;
    private Animal winner = null;
    private final List<Animal> finishedAnimals = new ArrayList<>();

    public synchronized void setWinner(Animal animal) {
        if (!raceFinished) {
            raceFinished = true;
            winner = animal;
            System.out.println("\n🏆 " + animal.getAnimalName() + " a gagné la course ! 🏆\n");
        }
    }

    public boolean isRaceFinished() {
        return raceFinished;
    }

    public synchronized void printPosition(Animal animal) {
        int total = animal.getDistance();
        int pos = animal.getPosition();
        StringBuilder line = new StringBuilder();
        line.append(animal.getAnimalName()).append(" : ");
        for (int i = 0; i < total; i++) {
            if (i == pos) line.append(animal.getEmoji());
            else line.append('-');
        }
        System.out.println(line.toString());
    }

    public synchronized void addFinishedAnimal(Animal animal) {
        if (!finishedAnimals.contains(animal)) {
            finishedAnimals.add(animal);
        }
    }

    public List<Animal> getFinishedAnimals() {
        return finishedAnimals;
    }

    public Animal getWinner() {
        return winner;
    }
}
