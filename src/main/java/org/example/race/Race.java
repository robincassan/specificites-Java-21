package org.example.race;

public class Race {
    public static void main(String[] args) throws InterruptedException {
        int distance = 30;
        RaceMonitor monitor = new RaceMonitor();

        Animal tortue = new Animal("Tortue", "🐢", distance, monitor);
        Animal lapin = new Animal("Lapin", "🐇", distance, monitor);
        Animal cheval = new Animal("Cheval", "🐎", distance, monitor);

        // Lancer les threads
        tortue.start();
        lapin.start();
        cheval.start();

        // Attendre que tous les threads terminent
        tortue.join();
        lapin.join();
        cheval.join();

        // Afficher classement final
        System.out.println("\n📋 Classement final :");
        int rank = 1;
        for (Animal a : monitor.getFinishedAnimals()) {
            System.out.println(rank + ". " + a.getAnimalName());
            rank++;
        }

        System.out.println("\nCourse terminée !");
    }
}
