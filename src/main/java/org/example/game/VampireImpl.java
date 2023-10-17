package org.example.game;

public class VampireImpl extends AbstractWarrior implements HasVampirism {
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
    public void hit(Warrior second) {
        if (second instanceof AbstractWarrior aw) {
            int healthBefore = aw.getHealth();
            super.hit(second);
            int healthAfter= aw.getHealth();
            int realDamage = healthBefore - Math.max(healthAfter, 0);
            int healing = (int) (realDamage * (double) getVampirismPercent() / 100);
            this.setHealth(healing);
        }

    }

    @Override
    public int getVampirismPercent() {
        return VAMPIRISM;
    }
}
