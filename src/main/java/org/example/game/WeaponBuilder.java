package org.example.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.game.interfaces.Weapon;

public class WeaponBuilder {
    private final InnerWeapon weapon = new InnerWeapon();

    WeaponBuilder addHealth(int health) {
        weapon.health += health;
        return this;
    }

    WeaponBuilder addAttack(int attack) {
        weapon.attack += attack;
        return this;
    }

    WeaponBuilder addDefence(int defence) {
        weapon.defence += defence;
        return this;
    }

    WeaponBuilder addVampirism(int vampirism) {
        weapon.vampirism += vampirism;
        return this;
    }

    WeaponBuilder addHealPower(int healPower) {
        weapon.healPower += healPower;
        return this;
    }

    Weapon collectWeapon() {
        return weapon;
    }

    Weapon collectWeapon(int health, int attack, int defence, int vampirism, int healPower) {
        return new InnerWeapon(health, attack, defence, vampirism, healPower);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private class InnerWeapon implements Weapon {
        private int health = 0;
        private int attack = 0;
        private int defence = 0;
        private int vampirism = 0;
        private int healPower = 0;
    }
}

