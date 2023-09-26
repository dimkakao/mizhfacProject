package org.example.game;

public abstract class AbstractWarrior implements Warrior {
    private int health;

    public AbstractWarrior(int health) {
        this.health = health;
    }

    @Override
    public boolean isAlive() {
        return getHealth() > 0;
    }

    @Override
    public void hit(Warrior second) {
        if (second instanceof AbstractWarrior awSecond) {
            awSecond.setHealth(awSecond.getHealth() - getAttack());
        }
    }

    public abstract int getAttack();

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }
}
