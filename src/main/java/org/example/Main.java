package org.example;

import org.example.game.Knight;
import org.example.game.Warrior;

public class Main {
    public static void main(String[] args) {
        Warrior warrior = new Knight();
        System.out.println(warrior.getAttack());
    }
}