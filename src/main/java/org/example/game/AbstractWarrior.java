package org.example.game;

import lombok.extern.slf4j.Slf4j;
import org.example.game.interfaces.CanAcceptDamage;
import org.example.game.interfaces.Weapon;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractWarrior implements Warrior, CanAcceptDamage {
    private static int idCounter = 0;
    private final int id = ++idCounter;
    protected int initialHealth;
    protected boolean isChangedWeapons = false;
    protected int weaponAttack;
    protected List<Weapon> weaponList = new ArrayList<>();
    private int health;

    public AbstractWarrior(int health) {
        this.health = health;
        this.initialHealth = health;
    }

    @Override
    public void acceptDamage(int damage) {
        setHealth(getHealth() - damage);
        log.info("Ouch! I'm " + this + " and accepted " + damage + " points of damage. Now I have health: " + getHealth());
    }

    @Override
    public void hit(CanAcceptDamage opponent) {
        opponent.acceptDamage(getAttack() + getTotalWeaponAttack());
    }

//    @Override
//    public void hit(CanAcceptDamage opponent) {
//        log.info("Warrior {} hits {}", this, opponent);
//        if (opponent instanceof AbstractWarrior awSecond) {
//            awSecond.acceptDamage(getAttack());
//        } else {
//            throw new RuntimeException();
//        }
//    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
    }

    @Override
    public String toString() {
        String name = getClass().getSimpleName().replace("Impl", "").toUpperCase();
        return name + "#" + id + "{" + "health=" + health + " attack=" + getAttack() + '}';
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        isChangedWeapons = true;
        weaponList.add(weapon);
        int healthWeapon = weapon.getHealth();
        initialHealth += healthWeapon;
        health += healthWeapon;
    }

    protected int getTotalWeaponAttack() {
        return weaponList.stream().map(Weapon::getAttack).reduce(0, Integer::sum);
    }
}

