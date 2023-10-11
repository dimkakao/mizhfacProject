package org.example.game;

public abstract class AbstractDefender extends AbstractWarrior{
    public AbstractDefender(int health) {
        super(health);
    }

    public abstract int getDefence();
}
