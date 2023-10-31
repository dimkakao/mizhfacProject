package org.example.game.interfaces;

public interface CanHitAndReportMixin extends CanHit {
    default int hitAndReportDealtDamage(CanAcceptDamage opponent) {
        int healthBefore = opponent.getHealth();
        CanHit.super.hit(opponent);
        int healthAfter = opponent.getHealth();
//        return healthBefore - Math.max(healthAfter, 0);
        return healthBefore - healthAfter;
    }
}
