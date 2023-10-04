package org.example.game;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.function.Supplier;

public class Army implements Iterable<Warrior> {

    private Queue<Warrior> army;

    public Army addUnits(WarriorClasses warriorClasses, int i) {
        return addUnits(warriorClasses::make, i);
    }

    public Army addUnits(Supplier<Warrior> warriorFactory, int i) {
        if (army == null) army = new ArrayDeque<>();
        for (int j = 0; j < i; j++) {
            army.add(warriorFactory.get());
        }
        return this;
    }

    @Override
    public Iterator<Warrior> iterator() {
        return new FirstAliveIterator();
    }

    public Queue<Warrior> getArmy() {
        return army;
    }

    public void setArmy(Queue<Warrior> army) {
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
            if (!hasNext()) throw new NoSuchElementException();
            return army.peek();
        }
    }
}
