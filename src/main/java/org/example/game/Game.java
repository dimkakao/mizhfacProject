package org.example.game;

public class Game {
    public static boolean fight(Warrior first, Warrior second) {
        System.out.println("1 Before " + first);
        System.out.println("2 Before " + second);
        System.out.println();
        while (first.isAlive()) {
            first.hit(second);
            if (!second.isAlive()) {
                System.out.println("1 warrior After " + first);
                System.out.println("2 warrior After " + second);
                System.out.println();
                return true;
            }
            second.hit(first);
        }
        System.out.println("1 warrior After " + first);
        System.out.println("2 warrior After " + second);
        System.out.println();
        return false;
    }

    public static boolean fight(Army first, Army second) {
        var it1 = first.iterator();
        var it2 = second.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();

//        Warrior firstWarrior = first.getNextWarrior();
//        Warrior secondWarrior = second.getNextWarrior();
//
//        while (firstWarrior != null) {
//            boolean isFirstWinner = fight(firstWarrior, secondWarrior);
//            System.out.println((isFirstWinner ? "First" : "Second") + " Winner");
//            System.out.println("First army size: " + first.getArmy().size());
//            System.out.println("Second army size: " + second.getArmy().size());
//            System.out.println();
//            if (isFirstWinner) {
//                secondWarrior = second.getNextWarrior();
//                if (secondWarrior == null) return true;
//            } else {
//                firstWarrior = first.getNextWarrior();
//            }
//        }
//        return false;
    }
}
