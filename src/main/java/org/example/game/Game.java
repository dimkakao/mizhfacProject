package org.example.game;

public class Game {
    public static boolean fight(Warrior first, Warrior second) {
        while (first.isAlive()) {
            first.hit(second);
            if (!second.isAlive()) {
                return true;
            }
            second.hit(first);
        }
        return false;
    }

    public static boolean fight(Army first, Army second) {
        var it1 = first.iterator();
        var it2 = second.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }

}
