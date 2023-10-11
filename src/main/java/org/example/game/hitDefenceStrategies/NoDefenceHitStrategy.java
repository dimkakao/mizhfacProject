package org.example.game.hitDefenceStrategies;

import org.example.game.AbstractWarrior;

public class NoDefenceHitStrategy implements HitDefenceStrategy {

    AbstractWarrior aw;

    public NoDefenceHitStrategy(AbstractWarrior aw) {
        this.aw = aw;
    }

    @Override
    public void hit(int attack) {
        aw.setHealth(aw.getHealth() - attack);
    }
}