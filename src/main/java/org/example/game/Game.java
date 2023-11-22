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
        var it1 = first.firstAliveIterator();
        var it2 = second.firstAliveIterator();
        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }

//    public static boolean fightStraight(Army first, Army second) {
//        int battlesCount = 0;
//        log.info("Army {} fights against Army {}", first, second);
//        var it1 = first.straightIterator();
//        var it2 = second.straightIterator();
//
//        while (it1.hasNext() && it2.hasNext()) {
//            System.out.println("BEFORE All in 1 --- " + first.getArmy().size());
////            it1.forEachRemaining(System.out::println);
//            System.out.println("BEFORE All in 2 --- " + second.getArmy().size());
////            it2.forEachRemaining(System.out::println);
//            while (it1.hasNext() && it2.hasNext()) {
//                battlesCount++;
//                fight(it1.next(), it2.next());
//            }
//            System.out.println("In this tour was " + battlesCount + " battles");
//            battlesCount=0;
//            System.out.println("Next tour!");
//            it1 = first.straightIterator();
//            it2 = second.straightIterator();
//            System.out.println("AFTER All in 1 --- " + first.getArmy().size());
////            it1.forEachRemaining(System.out::println);
//            System.out.println("AFTER All in 2 --- " + second.getArmy().size());
////            it2.forEachRemaining(System.out::println);
//        }
//        return it1.hasNext();
//    }

    public static boolean fightStraight(Army first, Army second) {
        log.info("Army {} fights against Army {}", first, second);

        while (!first.isEmpty() && !second.isEmpty()) {
            int battlesCount = 0;
            System.out.println("TAAAAK");
            var it1 = first.iterator();
            var it2 = second.iterator();
            while (it1.hasNext() && it2.hasNext()) {
                fight(it1.next(), it2.next());
                battlesCount++;
            }
            System.out.println("COUNT OF BATTLES - " + battlesCount);
        }
        return !first.isEmpty();
    }

}
