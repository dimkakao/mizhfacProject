package org.example.game;

import org.example.game.interfaces.Weapon;

public enum WeaponClasses {
    SWORD, SHIELD, GREAT_AXE, KATANA, MAGIC_WAND;

    public static Weapon factory(WeaponClasses weaponClasses) {
        return switch (weaponClasses) {
            case SWORD -> new WeaponBuilder()
                    .addHealth(5)
                    .addAttack(2)
                    .collectWeapon();
            case SHIELD -> new WeaponBuilder()
                    .addHealth(20)
                    .addAttack(-1)
                    .addDefence(2)
                    .collectWeapon();
            case GREAT_AXE -> new WeaponBuilder()
                    .addHealth(-15)
                    .addAttack(5)
                    .addDefence(-2)
                    .addVampirism(10)
                    .collectWeapon();
            case KATANA -> new WeaponBuilder()
                    .addHealth(-20)
                    .addAttack(6)
                    .addDefence(-5)
                    .addVampirism(50)
                    .collectWeapon();
            case MAGIC_WAND -> new WeaponBuilder()
                    .addHealth(30)
                    .addAttack(3)
                    .addHealPower(3)
                    .collectWeapon();
        };
    }

    public Weapon make() {
        return factory(this);
    }
}
