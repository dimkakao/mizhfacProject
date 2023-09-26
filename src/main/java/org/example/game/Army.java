package org.example.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;

public class Army {

    private Queue<Warrior> army;

    public Army addUnits(WarriorClasses warriorClasses, int i) {
        if (army == null) army = new LinkedList<>();
        for (int j = 0; j < i; j++) {
            army.add(warriorClasses.make());
        }
        return this;
    }

    public Army addUnits(Supplier<Warrior> warriorFactory, int i) {
        if (army == null) army = new LinkedList<>();
        for (int j = 0; j < i; j++) {
            army.add(warriorFactory.get());
        }
        return this;
    }

//    private void addUnits(Warrior warrior, int times) {
//        if (army == null) army = new LinkedList<>();
//        for (int i = 0; i < times; i++) {
//            army.add(warrior);
//        }
//    }

    public Warrior getNextWarrior() {
        return army.poll();
    }
}
