package org.example.game;

import java.util.Optional;

public interface WarriorInArmyImpl extends Warrior {
    Optional<WarriorInArmyImpl> getWarriorBehind();
}
