package org.example.game.defenceStrategies;

import org.example.game.AbstractDefender;
import org.example.game.AbstractWarrior;
import org.example.game.Warrior;

import java.util.Optional;

public class HitDefenceStrategyManager {

    public static Optional<HitDefenceStrategy> chooseStrategy(Warrior warrior) {
        HitDefenceStrategy strategy = null;
        if (warrior instanceof AbstractDefender defender) {
            strategy = new SimpleDefenceHitStrategy(defender, defender.getDefence());
        } else if (warrior instanceof AbstractWarrior awSecond) {
            strategy = new NoDefenceHitStrategy(awSecond);
        }
        return Optional.ofNullable(strategy);
    }
}
