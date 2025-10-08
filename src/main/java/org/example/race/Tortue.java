package org.example.race;

public final class Tortue extends Animal {
    public Tortue(int distance, RaceMonitor monitor) {
        super("Tortue", "🐢", distance, monitor);
    }

    @Override
    protected void avancer() {
        // La tortue avance de 1 à 3 pas de manière aléatoire
        position += 1 + (int)(Math.random() * 3);
    }
}

