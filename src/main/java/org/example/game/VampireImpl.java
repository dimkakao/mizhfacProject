package org.example.game;

import org.example.game.interfaces.*;

public class VampireImpl extends AbstractWarrior implements HasVampirism, CanHitAndReportMixin {
    static final int ATTACK = 4;
    static final int INITIAL_HEALTH = 40;
    static final int VAMPIRISM = 50;

    public VampireImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public void hit(CanAcceptDamage opponent) {
        int realDamage = hitAndReportDealtDamage(opponent);
        int healing = (int) (realDamage * (double) getVampirismPercent() / 100);
        this.setHealth(getHealth() + healing);
    }

    @Override
    public int getVampirismPercent() {
        return VAMPIRISM + getTotalWeaponVampirism();
    }

    private int getTotalWeaponVampirism() {
        return weaponList.stream().map(Weapon::getVampirism).reduce(0, Integer::sum);
    }

    @Override
    public int hitAndReportDealtDamage(CanAcceptDamage opponent) {
        int healthBefore = opponent.getHealth();
        opponent.acceptDamage(getAttack() + getTotalWeaponAttack());
        int healthAfter = opponent.getHealth();
        return healthBefore - healthAfter;
    }
}
