package org.example.game;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        log.info("Warrior {} hits {}", this, second);
        if (second instanceof AbstractWarrior awSecond) {
            awSecond.acceptDamage(getAttack());
        } else {
            throw new RuntimeException();
        }
    }

    public void acceptDamage(int damage) {
        setHealth(getHealth() - damage);
    }

    public abstract int getAttack();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        String name = getClass().getSimpleName().replace("Impl", "").toUpperCase();
        return name + "{" + "health=" + health + " attack=" + getAttack() +'}';
    }
}

