package org.example.game;

public class Warrior {
    public static final int ATTACK = 5;
    static final int INITIAL_HEALTH = 50;
    private int health;
    private int attack;

    public Warrior() {
        this(INITIAL_HEALTH);
    }

    protected Warrior(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return ATTACK;
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }

    public void hit(Warrior second) {
        second.setHealth(second.getAttack() - getAttack());
    }
}
