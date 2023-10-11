package org.example.game;

import lombok.extern.slf4j.Slf4j;
import org.example.game.hitDefenceStrategies.HitDefenceStrategy;
import org.example.game.hitDefenceStrategies.HitDefenceStrategyManager;

import java.util.Optional;

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
        Optional<HitDefenceStrategy> strategy = HitDefenceStrategyManager.chooseStrategy(second);
        strategy.ifPresent(x -> x.hit(this.getAttack()));
//        if (strategy != null) {
//            strategy.hit(this.getAttack());
//        }


//        if (second instanceof DefenderImpl defender) {
//            strategy = new SimpleDefenceStrategy(defender, defender.getDefence());
////            defender.setHealth(defender.getHealth() - Math.max(0, (getAttack() - defender.getDefence())));
//        } else if (second instanceof AbstractWarrior awSecond) {
//            strategy = new NoDefenceStrategy(awSecond);
////            awSecond.setHealth(awSecond.getHealth() - getAttack());
//        }
    }

    public abstract int getAttack();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

