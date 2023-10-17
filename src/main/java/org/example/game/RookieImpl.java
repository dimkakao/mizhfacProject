package org.example.game;

public class RookieImpl extends AbstractWarrior {

    private static final int ATTACK = 1;
    private static final int INITIAL_HEALTH = 60;

    public RookieImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
