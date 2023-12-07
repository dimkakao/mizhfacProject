package org.example.game;

import org.example.game.interfaces.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.game.Game.fight;
import static org.example.game.WarriorClasses.*;
import static org.example.game.WeaponClasses.*;
import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    @Test
    @DisplayName("1. Weapon")
    void fight01() {

        Warrior unit1 = WarriorClasses.factory(WARRIOR);
        Warrior unit2 = WarriorClasses.factory(VAMPIRE);
        Weapon weapon1 = new WeaponBuilder().collectWeapon(-10, 5, 0, 40, 0);
        Weapon weapon2 = WeaponClasses.factory(SWORD);
        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);

        var res = fight(unit1, unit2);
        assertTrue(res);
    }

    @Test
    @DisplayName("2. Weapon")
    void fight02() {

        Warrior unit1 = WarriorClasses.factory(DEFENDER);
        Warrior unit2 = WarriorClasses.factory(LANCER);
        Weapon weapon1 = WeaponClasses.factory(SHIELD);
        Weapon weapon2 = WeaponClasses.factory(GREAT_AXE);
        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);

        var res = fight(unit1, unit2);
        assertFalse(res);
    }

    @Test
    @DisplayName("3. Weapon")
    void fight03() {
        Warrior unit1 = WarriorClasses.factory(HEALER);
        Warrior unit2 = WarriorClasses.factory(KNIGHT);
        Weapon weapon1 = WeaponClasses.factory(MAGIC_WAND);
        Weapon weapon2 = WeaponClasses.factory(KATANA);
        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);

        var res = fight(unit1, unit2);
        assertFalse(res);
    }

    @Test
    @DisplayName("4. Weapon")
    void fight04() {

        Warrior unit1 = WarriorClasses.factory(DEFENDER);
        Warrior unit2 = WarriorClasses.factory(VAMPIRE);

        Weapon weapon1 = WeaponClasses.factory(SHIELD);
        Weapon weapon2 = WeaponClasses.factory(MAGIC_WAND);
        Weapon weapon3 = WeaponClasses.factory(SHIELD);
        Weapon weapon4 = WeaponClasses.factory(KATANA);

        unit1.equipWeapon(weapon1);
        unit1.equipWeapon(weapon2);

        unit2.equipWeapon(weapon3);
        unit2.equipWeapon(weapon4);

        var res = fight(unit1, unit2);
        assertFalse(res);
    }

    @Test
    @DisplayName("5. Weapon")
    void fight05() {
        Weapon weapon1 = WeaponClasses.factory(MAGIC_WAND);
        Weapon weapon2 = WeaponClasses.factory(GREAT_AXE);


        Army army1 = new Army()
                .addUnits(KNIGHT, 1)
                .addUnits(LANCER, 1);
        Army army2 = new Army()
                .addUnits(VAMPIRE, 1)
                .addUnits(HEALER, 1);

        List<Warrior> list1 = new ArrayList<>(army1.getArmy());
        List<Warrior> list2 = new ArrayList<>(army2.getArmy());

        list1.get(0).equipWeapon(weapon1);
        list1.get(1).equipWeapon(weapon2);

        list2.get(0).equipWeapon(weapon1);
        list2.get(1).equipWeapon(weapon2);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("6. Weapon")
    void fight06() {
        Weapon weapon1 = WeaponClasses.factory(SWORD);
        Weapon weapon2 = WeaponClasses.factory(GREAT_AXE);


        Army army1 = new Army()
                .addUnits(DEFENDER, 1)
                .addUnits(WARRIOR, 1);
        Army army2 = new Army()
                .addUnits(KNIGHT, 1)
                .addUnits(HEALER, 1);

        List<Warrior> list1 = new ArrayList<>(army1.getArmy());
        List<Warrior> list2 = new ArrayList<>(army2.getArmy());

        list1.get(0).equipWeapon(weapon2);
        list1.get(1).equipWeapon(weapon2);

        list2.get(0).equipWeapon(weapon1);
        list2.get(1).equipWeapon(weapon1);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("7. Weapon")
    void fight07() {
        Weapon weapon1 = WeaponClasses.factory(KATANA);

        Army army1 = new Army()
                .addUnits(DEFENDER, 2);
        Army army2 = new Army()
                .addUnits(KNIGHT, 1)
                .addUnits(VAMPIRE, 1);

        List<Warrior> list1 = new ArrayList<>(army1.getArmy());
        List<Warrior> list2 = new ArrayList<>(army2.getArmy());

        list1.get(0).equipWeapon(weapon1);
        list1.get(1).equipWeapon(weapon1);

        list2.get(0).equipWeapon(weapon1);
        list2.get(1).equipWeapon(weapon1);

        var res = fight(army1, army2);
        assertFalse(res);
    }

    @Test
    @DisplayName("8. Weapon")
    void fight08() {
        Weapon weapon1 = new WeaponBuilder().collectWeapon(-20, 6, 1, 40, -2);
        Weapon weapon2 = new WeaponBuilder().collectWeapon(20, -2, 2, -55, 3);

        Army army1 = new Army()
                .addUnits(KNIGHT, 3);
        Army army2 = new Army()
                .addUnits(WARRIOR, 1)
                .addUnits(DEFENDER, 2);

        List<Warrior> list1 = new ArrayList<>(army1.getArmy());
        List<Warrior> list2 = new ArrayList<>(army2.getArmy());

        list1.get(0).equipWeapon(weapon1);
        list1.get(1).equipWeapon(weapon1);
        list1.get(2).equipWeapon(weapon2);

        list2.get(0).equipWeapon(weapon1);
        list2.get(1).equipWeapon(weapon2);
        list2.get(2).equipWeapon(weapon2);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("9. Weapon")
    void fight09() {
        Weapon weapon1 = new WeaponBuilder().collectWeapon(-20, 1, 1, 40, -2);
        Weapon weapon2 = new WeaponBuilder().collectWeapon(20, 2, 2, -55, 3);

        Army army1 = new Army()
                .addUnits(VAMPIRE, 3);
        Army army2 = new Army()
                .addUnits(WARRIOR, 1)
                .addUnits(DEFENDER, 2);

        List<Warrior> list1 = new ArrayList<>(army1.getArmy());
        List<Warrior> list2 = new ArrayList<>(army2.getArmy());

        list1.get(0).equipWeapon(weapon1);
        list1.get(1).equipWeapon(weapon1);
        list1.get(2).equipWeapon(weapon2);

        list2.get(0).equipWeapon(weapon1);
        list2.get(1).equipWeapon(weapon2);
        list2.get(2).equipWeapon(weapon2);

        var res = fight(army1, army2);
        assertFalse(res);
    }

    @Test
    @DisplayName("10. Weapon")
    void fight10() {
        Weapon weapon1 = WeaponClasses.factory(KATANA);
        Weapon weapon2 = WeaponClasses.factory(SHIELD);

        Army army1 = new Army()
                .addUnits(VAMPIRE, 2)
                .addUnits(ROOKIE,2);
        Army army2 = new Army()
                .addUnits(WARRIOR, 1)
                .addUnits(DEFENDER, 2);

        List<Warrior> list1 = new ArrayList<>(army1.getArmy());
        List<Warrior> list2 = new ArrayList<>(army2.getArmy());

        list1.get(0).equipWeapon(weapon1);
        list1.get(1).equipWeapon(weapon1);
        list1.get(2).equipWeapon(weapon2);

        list2.get(0).equipWeapon(weapon1);
        list2.get(1).equipWeapon(weapon2);
        list2.get(2).equipWeapon(weapon2);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("11. Weapon")
    void fight11() {
        Weapon weapon1 = WeaponClasses.factory(SWORD);
        Weapon weapon2 = WeaponClasses.factory(GREAT_AXE);

        Army army1 = new Army()
                .addUnits(VAMPIRE, 3);
        Army army2 = new Army()
                .addUnits(WARRIOR, 1)
                .addUnits(DEFENDER, 1);

        List<Warrior> list1 = new ArrayList<>(army1.getArmy());
        List<Warrior> list2 = new ArrayList<>(army2.getArmy());

        list1.get(0).equipWeapon(weapon2);
        list1.get(1).equipWeapon(weapon2);
        list1.get(2).equipWeapon(weapon2);

        list2.get(0).equipWeapon(weapon1);
        list2.get(1).equipWeapon(weapon1);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("12. Weapon")
    void fight12() {
        Weapon weapon1 = WeaponClasses.factory(KATANA);
        Weapon weapon2 = WeaponClasses.factory(MAGIC_WAND);

        Army army1 = new Army()
                .addUnits(ROOKIE, 3);
        Army army2 = new Army()
                .addUnits(DEFENDER, 1)
                .addUnits(HEALER, 1);

        List<Warrior> list1 = new ArrayList<>(army1.getArmy());
        List<Warrior> list2 = new ArrayList<>(army2.getArmy());

        list1.get(0).equipWeapon(weapon1);
        list1.get(1).equipWeapon(weapon1);
        list1.get(2).equipWeapon(weapon1);

        list2.get(0).equipWeapon(weapon2);
        list2.get(1).equipWeapon(weapon2);

        var res = fight(army1, army2);
        assertFalse(res);
    }

    @Test
    @DisplayName("13. Weapon")
    void fight013() {

        Warrior unit1 = WarriorClasses.factory(WARRIOR);
        Warrior unit2 = WarriorClasses.factory(KNIGHT);
        Weapon weapon1 = WeaponClasses.factory(SWORD);
        Weapon weapon2 = WeaponClasses.factory(KATANA);
        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);

        var res = fight(unit1, unit2);
        assertTrue(res);
        assertAll(
                () -> assertEquals(3, unit1.getHealth()),
                () -> assertEquals(-5, unit2.getHealth())
        );
    }
}
