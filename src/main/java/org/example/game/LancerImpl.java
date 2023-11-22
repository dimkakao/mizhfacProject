package org.example.game;

import lombok.extern.slf4j.Slf4j;
import org.example.game.interfaces.CanAcceptDamage;
import org.example.game.interfaces.CanHit;
import org.example.game.interfaces.CanHitAndReportMixin;
import org.example.game.interfaces.HasMultiHit;

@Slf4j
public class LancerImpl extends AbstractWarrior
        implements HasMultiHit, CanHitAndReportMixin {
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
        if (opponent instanceof WarriorInArmyImpl warriorInArmy) {
            var nextBehind = warriorInArmy.getWarriorBehind();
            if (nextBehind.isPresent()) {
                int secondDamage = realDamage * PENETRATION / 100;
                CanHit proxySecondHitByLancer = () -> secondDamage;
                proxySecondHitByLancer.hit(nextBehind.get());
                log.info("I am Lancer and hit second time with damage " + secondDamage);
//                nextBehind.get().acceptDamage(secondDamage);
            }
        }
//        for (int i = 0; i < getHitCount(); i++) {
//            if (opponent instanceof AbstractWarrior awSecond) {
//                int attack = (int) (getAttack() * (percentStrength / 100.0));
//                awSecond.acceptDamage(attack);
//                if (!awSecond.hasSomeoneBehind()) {
//                    break;
//                }
//                opponent = awSecond.getWarriorBehind();
//                percentStrength /= 2;
//            } else {
//                throw new RuntimeException();
//            }
//        }
    }

    @Override
    public int getHitCount() {
        return 2;
    }
}