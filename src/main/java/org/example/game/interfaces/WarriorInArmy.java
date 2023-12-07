package org.example.game.interfaces;

import java.util.Optional;

public interface WarriorInArmy extends Warrior {
    Optional<WarriorInArmy> getWarriorBehind();
}
