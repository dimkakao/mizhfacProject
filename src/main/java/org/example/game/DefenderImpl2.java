package org.example.game;

import org.example.game.AbstractDefender;
import org.example.game.AbstractWarrior;

public class DefenderImpl2 extends AbstractDefender {

    private static final int ATTACK = 3;
    private static final int INITIAL_HEALTH = 60;
    private static final int DEFENCE = 2;

    public DefenderImpl2() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getDefence() {
        return DEFENCE;
    }

    @Override
    public String toString() {
        return "DefenderImpl2{" +
                " attack=" + ATTACK +
                " health" + this.getHealth() +
                '}';
    }
}
