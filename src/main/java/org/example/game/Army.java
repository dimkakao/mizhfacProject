package org.example.game;

import java.util.*;
import java.util.function.Supplier;

public class Army implements Iterable<Warrior> {

    private static int idCounter = 0;
    private final int id = ++idCounter;
    private Deque<Warrior> army;

    private class WarriorInArmyImpl implements WarriorInArmy {
        Warrior warrior;
        Warrior warriorBehind;
        public WarriorInArmyImpl(Warrior warrior) {
            this.warrior = warrior;
            if (!army.isEmpty()) {
                ((WarriorInArmyImpl) army.peekLast()).setWarriorBehind(this);
            }
        }

        @Override
        public Optional<WarriorInArmy> getWarriorBehind() {
            return Optional.ofNullable((WarriorInArmy) warriorBehind);
        }

        @Override
        public void acceptDamage(int damage) {
            warrior.acceptDamage(damage);
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        public void setWarriorBehind(Warrior warriorBehind) {
            this.warriorBehind = warriorBehind;
        }
    }

    public Army addUnits(WarriorClasses warriorClasses, int i) {
        return addUnits(warriorClasses::make, i);
    }

    public Army addUnits(Supplier<Warrior> warriorFactory, int i) {
        if (army == null) army = new ArrayDeque<>();
        for (int j = 0; j < i; j++) {
            Warrior warriorToAdd = warriorFactory.get();
            WarriorInArmy warriorInArmy = new WarriorInArmyImpl(warriorToAdd);
            army.add(warriorInArmy);
        }
        return this;
    }

    @Override
    public Iterator<Warrior> iterator() {
        return new FirstAliveIterator();
    }

    public Deque<Warrior> getArmy() {
        return army;
    }

    public void setArmy(Deque<Warrior> army) {
        this.army = army;
    }

    public Warrior getNextWarrior() {
        return army.poll();
    }

    private class FirstAliveIterator implements Iterator<Warrior> {

        @Override
        public boolean hasNext() {
            if (!army.isEmpty() && !army.peek().isAlive()) {
                army.poll();
            }
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

    @Override
    public String toString() {
        return "Army{" +
                "#" + id +
                ", army=" + army +
                '}';
    }
}
