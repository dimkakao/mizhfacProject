package org.example.game;

import lombok.extern.slf4j.Slf4j;
import org.example.game.interfaces.CanAcceptDamage;
import org.example.game.interfaces.CanHit;
import org.example.game.interfaces.CanHitAndReportMixin;
import org.example.game.interfaces.HasMultiHit;

@Slf4j
public class LancerImpl extends AbstractWarrior implements HasMultiHit, CanHitAndReportMixin {
    static final int ATTACK = 6;
    static final int INITIAL_HEALTH = 50;
    static final int PENETRATION = 50;

    public LancerImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public void hit(CanAcceptDamage opponent) {
        log.info("Warrior {} hits {}", this, opponent);
        int realDamage = hitAndReportDealtDamage(opponent);
        log.info("I am Lancer and hit with damage " + realDamage);
        if (opponent instanceof WarriorInArmy warriorInArmy) {
            var nextBehind = warriorInArmy.getWarriorBehind();
            if (nextBehind.isPresent()) {
                int secondDamage = realDamage * PENETRATION / 100;
                CanHit proxySecondHitByLancer = () -> secondDamage + getTotalWeaponAttack();
                proxySecondHitByLancer.hit(nextBehind.get());
                log.info("I am Lancer and hit second time with damage " + secondDamage);
            }
        }
    }

    @Override
    public int getHitCount() {
        return 2;
    }

    @Override
    public int hitAndReportDealtDamage(CanAcceptDamage opponent) {
        int healthBefore = opponent.getHealth();
        opponent.acceptDamage(getAttack() + getTotalWeaponAttack());
        int healthAfter = opponent.getHealth();
        return healthBefore - healthAfter;
    }
}