package org.example.game;

import org.example.game.interfaces.CanAcceptDamage;
import org.example.game.interfaces.CanHaveWeapon;
import org.example.game.interfaces.CanHit;

public interface Warrior extends
        CanAcceptDamage,
        CanHit,
        CanHaveWeapon {
}
