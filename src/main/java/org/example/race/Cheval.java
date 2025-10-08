package org.example.race;


public final class Cheval extends Animal {
    public Cheval(int distance, RaceMonitor monitor) {
        super("Cheval", "🐎", distance, monitor);
    }

    @Override
    protected void avancer() {
        // Le Cheval avance 1 à 3 pas aléatoires
        position += 1 + (int)(Math.random() * 3);
    }
}

