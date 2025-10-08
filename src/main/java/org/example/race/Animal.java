package org.example.race;

import java.util.Random;

public class Animal extends Thread {
    private final String name;
    private final String emoji;
    private int position = 0;
    private final int distance;
    private final RaceMonitor monitor;
    private final Random random = new Random();

    public Animal(String name, String emoji, int distance, RaceMonitor monitor) {
        this.name = name;
        this.emoji = emoji;
        this.distance = distance;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (position < distance && !monitor.isRaceFinished()) {
            int step = 1 + random.nextInt(3); // avance 1 à 3 pas
            position += step;
            if (position > distance) position = distance;

            monitor.printPosition(this);

            try {
                Thread.sleep(200 + random.nextInt(300)); // pause aléatoire
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            if (position >= distance) {
                monitor.setWinner(this);
            }
        }
        // Enregistrer l'arrivée pour le classement
        monitor.addFinishedAnimal(this);
    }

    public String getAnimalName() { return name; }
    public String getEmoji() { return emoji; }
    public int getPosition() { return position; }
    public int getDistance() { return distance; }
}
