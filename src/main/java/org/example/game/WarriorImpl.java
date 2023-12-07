package org.example.game;

public class WarriorImpl extends AbstractWarrior {
    static final int ATTACK = 5;
    static final int INITIAL_HEALTH = 50;

    public WarriorImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
