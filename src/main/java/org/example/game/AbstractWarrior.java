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
        if (second instanceof DefenderImpl defender) {
            defender.setHealth(defender.getHealth() - Math.max(0, (getAttack() - defender.getDefence())));
        } else if (second instanceof AbstractWarrior awSecond) {
            awSecond.setHealth(awSecond.getHealth() - getAttack());
        }
    }

    public abstract int getAttack();

    int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }
}
