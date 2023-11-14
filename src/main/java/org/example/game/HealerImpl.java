package org.example.game;

import lombok.extern.slf4j.Slf4j;
import org.example.game.interfaces.CanHeal;
import org.example.game.interfaces.HasHealth;

@Slf4j
public class HealerImpl extends AbstractWarrior implements Warrior, CanHeal {

    static final int ATTACK = 0;
    static final int INITIAL_HEALTH = 60;
    static final int HEAL_POWER = 2;
    static final int MEDICAL_KITS = 200;
    private int usedKits;

    private Warrior frontWarrior;

    public HealerImpl() {
        this(INITIAL_HEALTH);
    }

    public HealerImpl(int health) {
        super(health);
    }

    @Override
    public void heal(HasHealth patient) {
        if (usedKits < MEDICAL_KITS) {
            log.info("Magic healing! I am " + this + "and I will heal " + patient);
            ((AbstractWarrior) patient).setHealth(patient.getHealth() + getHealPower());
            usedKits++;
        }
    }

    @Override
    public void acceptDamage(int damage) {
        if (damage == 0) this.setHealth(0);
        else super.acceptDamage(damage);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public Warrior getFrontWarrior() {
        return frontWarrior;
    }

    public void setFrontWarrior(Warrior frontWarrior) {
        this.frontWarrior = frontWarrior;
    }

    int getHealPower() {
        return HEAL_POWER;
    }
}
