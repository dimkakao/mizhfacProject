package org.example.game.interfaces;

import java.util.Collection;

public interface CanMoveUnits extends Warrior {
    Collection<Warrior> moveUnits(Collection<Warrior> army);
}
