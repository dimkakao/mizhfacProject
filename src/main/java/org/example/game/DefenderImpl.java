package org.example.game;

public class DefenderImpl extends AbstractWarrior {

    private static final int ATTACK = 3;
    private static final int INITIAL_HEALTH = 60;
    private static final int DEFENCE = 3;

    public DefenderImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getDefence() {
        return DEFENCE;
    }
}
