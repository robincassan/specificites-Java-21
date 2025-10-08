package org.example.race;

import java.util.List;

public class Race {
    public static void main(String[] args) throws InterruptedException {
        int distance = 30;

        RaceMonitor monitor = new RaceMonitor(distance);

        List<Animal> animaux = List.of(
                new Tortue(distance, monitor),
                new Lapin(distance, monitor),
                new Cheval(distance, monitor)
        );

        // Créer et démarrer le thread arbitre
        Arbitre arbitre = new Arbitre(animaux, monitor);
        arbitre.start();

        System.out.println("=== DÉPART DE LA COURSE ===\n");

        // Démarrer tous les animaux
        animaux.forEach(Thread::start);

        // Attendre la fin de tous les animaux
        for (Animal a : animaux) {
            a.join();
        }

        // Attendre la fin de l'arbitre
        arbitre.join();

        System.out.println("\n=== COURSE TERMINÉE ===");
        monitor.printClassement();
    }
}
