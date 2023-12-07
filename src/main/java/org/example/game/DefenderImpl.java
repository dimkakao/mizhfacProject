package org.example.game;

import org.example.game.interfaces.HasDefence;
import org.example.game.interfaces.Weapon;

public class DefenderImpl extends AbstractWarrior implements HasDefence {

    private static final int ATTACK = 3;
    private static final int INITIAL_HEALTH = 60;
    private static final int DEFENCE = 2;

    public DefenderImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getDefence() {
        return DEFENCE + getTotalWeaponDefence();
    }

    @Override
    public void acceptDamage(int damage) {
        int reducedDamage = Math.max(0, damage - getDefence());
        super.acceptDamage(reducedDamage);
    }

    private int getTotalWeaponDefence() {
        return weaponList.stream().map(Weapon::getDefence).reduce(0, Integer::sum);
    }


}
