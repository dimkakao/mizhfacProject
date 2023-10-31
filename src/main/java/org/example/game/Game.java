package org.example.game;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Game {
    public static boolean fight(Warrior first, Warrior second) {
        while (first.isAlive()) {
            first.hit(second);
            if (!second.isAlive()) {
                log.info("1 Warrior {} win", first);
                return true;
            }
            second.hit(first);
        }
        log.info("2 Warrior {} win", second);
        return false;
    }

    public static boolean fight(Army first, Army second) {
        log.info("Army {} fights against Army {}", first, second);
        var it1 = first.iterator();
        var it2 = second.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }

}
