package org.example.game;

public class Knight extends Warrior {
    public static final int ATTACK = 7;

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
