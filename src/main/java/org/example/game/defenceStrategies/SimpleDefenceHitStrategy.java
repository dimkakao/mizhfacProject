package org.example.game.defenceStrategies;

import org.example.game.AbstractWarrior;

public class SimpleDefenceHitStrategy implements HitDefenceStrategy {

    private AbstractWarrior aw;
    private int defence;

    public SimpleDefenceHitStrategy(AbstractWarrior aw, int defence) {
        this.aw = aw;
        this.defence = defence;
    }

    @Override
    public void hit(int attack) {
        aw.setHealth(aw.getHealth() - Math.max(0,(attack - defence)));
    }
}