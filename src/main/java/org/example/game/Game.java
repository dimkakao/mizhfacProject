package org.example.game;

public class Game {
    public static boolean fight(Warrior first, Warrior second) {
        while (first.isAlive()) {
            first.hit(second);
            if (!second.isAlive()) return true;
            first.hit(first);
        }
        return false;
    }
}
