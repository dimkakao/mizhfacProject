package org.example.game;

import org.example.game.interfaces.Warrior;

import java.util.Optional;

public interface WarriorInArmy extends Warrior {
    Optional<WarriorInArmy> getWarriorBehind();
}
