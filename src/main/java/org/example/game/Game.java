package org.example.game;

import lombok.extern.slf4j.Slf4j;
import org.example.game.interfaces.Warrior;

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
        var it1 = first.firstAliveIterator();
        var it2 = second.firstAliveIterator();
        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
            first.moveUnits();
            second.moveUnits();
        }
        return it1.hasNext();
    }

    public static boolean fightStraight(Army first, Army second) {
        log.info("Army {} fights against Army {}", first, second);

        while (!first.isEmpty() && !second.isEmpty()) {
            var it1 = first.iterator();
            var it2 = second.iterator();
            while (it1.hasNext() && it2.hasNext()) {
                fight(it1.next(), it2.next());
            }
        }
        return !first.isEmpty();
    }

}
