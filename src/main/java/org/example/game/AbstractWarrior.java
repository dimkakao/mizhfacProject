package org.example.game;

import lombok.extern.slf4j.Slf4j;
import org.example.game.interfaces.CanAcceptDamage;

@Slf4j
public abstract class AbstractWarrior implements Warrior, CanAcceptDamage {
    private static int idCounter = 0;
    private final int initialHealth;
    private final int id = ++idCounter;
    private int health;

    public AbstractWarrior(int health) {
        this.health = health;
        this.initialHealth = health;
    }

    @Override
    public void acceptDamage(int damage) {
        setHealth(getHealth() - damage);
        log.info("Ouch! I'm "+ this.getClass().getSimpleName() + "and accepted " + damage + " points of damage. Now I have health: " + getHealth());
    }

//    @Override
//    public void hit(CanAcceptDamage opponent) {
//        log.info("Warrior {} hits {}", this, opponent);
//        if (opponent instanceof AbstractWarrior awSecond) {
//            awSecond.acceptDamage(getAttack());
//        } else {
//            throw new RuntimeException();
//        }
//    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
    }

    @Override
    public String toString() {
        String name = getClass().getSimpleName().replace("Impl", "").toUpperCase();
        return name + "#" + id + "{" + "health=" + health + " attack=" + getAttack() + '}';
    }
}

