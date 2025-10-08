package org.example.race;

public final class Lapin extends Animal {
    public Lapin(int distance, RaceMonitor monitor) {
        super("Lapin", "🐇", distance, monitor);
    }

    @Override
    protected void avancer() {
        // Le lapin avance 1 à 3 pas aléatoires
        position += 1 + (int)(Math.random() * 3);
    }
}
