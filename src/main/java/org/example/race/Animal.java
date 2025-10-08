package org.example.race;

public sealed abstract class Animal extends Thread permits Tortue, Lapin, Cheval {
    protected final String name;
    protected final String emoji;
    protected int position = 0;
    protected final int distance;
    protected final RaceMonitor monitor;

    protected Animal(String name, String emoji, int distance, RaceMonitor monitor) {
        this.name = name;
        this.emoji = emoji;
        this.distance = distance;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (position < distance && !monitor.isRaceFinished()) {
            avancer();
            if (position > distance) position = distance;

            monitor.printPosition(this);

            try {
                Thread.sleep(200 + (int)(Math.random() * 300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            if (position >= distance) {
                monitor.setWinner(this);
            }
        }
        monitor.addFinishedAnimal(this);
    }

    // Méthode abstraite pour avancer selon le type d'animal
    protected abstract void avancer();

    // Getters
    public String getAnimalName() { return name; }
    public String getEmoji() { return emoji; }
    public int getPosition() { return position; }
    public int getDistance() { return distance; }

}
