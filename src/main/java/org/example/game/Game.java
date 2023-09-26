package org.example.game;

public class Game {
    public static boolean fight(Warrior first, Warrior second) {
        while (first.isAlive()) {
            first.hit(second);
            if (!second.isAlive()) return true;
            second.hit(first);
        }
        return false;
    }

    public static boolean fight(Army first, Army second) {
        Warrior firstWarrior = first.getNextWarrior();
        Warrior secondWarrior = second.getNextWarrior();
        while (firstWarrior != null) {
            boolean isFirstWinner = fight(firstWarrior, secondWarrior);
            if (isFirstWinner) {
                secondWarrior = second.getNextWarrior();
                if (secondWarrior == null) return true;
            } else firstWarrior = first.getNextWarrior();
        }
        return false;
    }
}
