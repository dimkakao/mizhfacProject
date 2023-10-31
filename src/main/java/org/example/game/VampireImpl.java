package org.example.game;

import org.example.game.interfaces.CanAcceptDamage;
import org.example.game.interfaces.CanHitAndReportMixin;
import org.example.game.interfaces.HasVampirism;

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
        this.setHealth(healing);
    }

    @Override
    public int getVampirismPercent() {
        return VAMPIRISM;
    }
}
