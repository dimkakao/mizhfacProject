package org.example.game;

import org.example.game.interfaces.CanAcceptDamage;
import org.example.game.interfaces.CanHeal;

import java.util.*;
import java.util.function.Supplier;

public class Army implements Iterable<Warrior> {

    private static int idCounter = 0;
    private final int id = ++idCounter;
    private Deque<WarriorInArmyImpl> army;

    public Army addUnits(WarriorClasses warriorClasses, int count) {
        return addUnits(warriorClasses::make, count);
    }

    public Army addUnits(Supplier<Warrior> warriorFactory, int count) {
        if (army == null) {
            army = new ArrayDeque<>();
        }
        for (int i = 0; i < count; i++) {
            var currentLast = army.peekLast();
            Warrior warriorToAdd = warriorFactory.get();
            WarriorInArmyImpl noviceInArmy = new WarriorInArmyImpl(warriorToAdd);
            Optional<WarriorInArmyImpl> currentLastOptional = Optional.ofNullable(currentLast);
            if (currentLast != null) {
                currentLast.setWarriorBehind(noviceInArmy);
            }
            army.add(noviceInArmy);
            if (currentLast != null && warriorToAdd instanceof HealerImpl healer) {
                healer.setFrontWarrior(currentLast.unwrap());
            }
        }
        return this;
    }

    @Override
    public Iterator<Warrior> iterator() {
        return allAliveIterator();
    }

    public Iterator<Warrior> firstAliveIterator() {
        return new FirstAliveIterator();
    }

    public Iterator<Warrior> allAliveIterator() {
        return army.stream()
                .filter(Warrior::isAlive)
                .map(Army.WarriorInArmyImpl::unwrap)
                .iterator();
    }

    public Deque<WarriorInArmyImpl> getArmy() {
        return army;
    }

    public Warrior getNextWarrior() {
        return army.poll();
    }

    @Override
    public String toString() {
        return "Army{" + "#" + id + ", army=" + army + '}';
    }

    public boolean isEmpty() {
        return ! new FirstAliveIterator().hasNext();
    }

    enum ChampionDealsHit implements Command {
        INSTANCE
    }

    public interface Command {
    }

    private class FirstAliveIterator implements Iterator<Warrior> {

        @Override
        public boolean hasNext() {
            if (!army.isEmpty() && !army.peek().isAlive()) army.poll();
            return !army.isEmpty();
        }

        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return army.peek();
        }
    }

//    private class AllAliveIterator implements Iterator<Warrior> {
//
//
//        Iterator<WarriorInArmyImpl> iterator = army.iterator();
//        WarriorInArmyImpl warrior;
//
//        @Override
//        public boolean hasNext() {
//            while (iterator.hasNext()) {
//                warrior = iterator.next();
//                if (warrior.isAlive()) return true;
//            }
//            return false;
//        }
//
//        @Override
//        public Warrior next() {
//            return iterator.next()();
//        }
//    }

//    private class AllAliveIterator implements Iterator<Warrior> {
//
//        Iterator<WarriorInArmyImpl> iterator = army.iterator();
//        WarriorInArmyImpl warrior;
//
////        AllAliveIterator() {
////            Deque<WarriorInArmyImpl> tmpArmy = new ArrayDeque<>(army);
////            tmpArmy.forEach((x) -> {
////                if (!x.getMainWarrior().isAlive()) army.remove(x);
////            });
////            iterator = army.iterator();
////        }
//
//        @Override
//        public boolean hasNext() {
//            while (iterator.hasNext()) {
//                warrior = iterator.next();
//                if (warrior.isAlive()) return true;
//            }
//            return false;
////            if (!army.isEmpty() && !army.peek().isAlive()) army.poll();
//
//        }
//
//        @Override
//        public Warrior next() {
//            return iterator.next().getMainWarrior();
//        }
//    }

    private class WarriorInArmyImpl implements org.example.game.WarriorInArmyImpl {
        private final Warrior warrior;
        private org.example.game.WarriorInArmyImpl warriorBehind;

        public WarriorInArmyImpl(Warrior warrior) {
            this.warrior = Objects.requireNonNull(warrior);
        }

        @Override
        public Optional<org.example.game.WarriorInArmyImpl> getWarriorBehind() {
            return Optional.ofNullable(warriorBehind);
        }

        public void setWarriorBehind(org.example.game.WarriorInArmyImpl warriorBehind) {
            this.warriorBehind = Objects.requireNonNull(warriorBehind);
        }

        public Warrior unwrap() {
            return warrior;
        }

        @Override
        public void acceptDamage(int damage) {
            warrior.acceptDamage(damage);
        }

        void passCommand(Command command, WarriorInArmyImpl passer) {
            if (passer != this) {
                if (command instanceof ChampionDealsHit && warrior instanceof CanHeal healer) {
                    healer.heal(passer.unwrap());
                }
            }
            getWarriorBehind().ifPresent(w -> ((WarriorInArmyImpl) w).passCommand(command, this));
        }

        @Override
        public void hit(CanAcceptDamage opponent) {
            warrior.hit(opponent);
            passCommand(ChampionDealsHit.INSTANCE, this);
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        @Override
        public boolean isAlive() {
            return warrior.isAlive();
        }

        @Override
        public String toString() {
            return warrior.toString();
        }

    }

}
