package org.example.game;

import org.example.game.interfaces.Warrior;
import org.example.game.interfaces.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.game.Game.fight;
import static org.example.game.WarriorClasses.*;
import static org.example.game.WeaponClasses.*;
import static org.junit.jupiter.api.Assertions.*;

public class WarlordTest {

    @Test
    @DisplayName("1. Warlord")
    void fight001() {

        Warrior unit1 = WarriorClasses.factory(DEFENDER);
        Warrior unit2 = WarriorClasses.factory(WARLORD);

        var res = fight(unit1, unit2);
        assertFalse(res);
        ///////////////
        unit1 = WarriorClasses.factory(DEFENDER);
        unit2 = WarriorClasses.factory(WARLORD);

        res = fight(unit2, unit1);
        assertTrue(res);
    }

    @Test
    @DisplayName("2. Warlord")
    void fight002() {

        Warrior unit1 = WarriorClasses.factory(WARLORD);
        Warrior unit2 = WarriorClasses.factory(VAMPIRE);

        var res = fight(unit1, unit2);
        assertTrue(res);
        ///////////////
        unit1 = WarriorClasses.factory(WARLORD);
        unit2 = WarriorClasses.factory(VAMPIRE);

        res = fight(unit2, unit1);
        assertFalse(res);
    }

    @Test
    @DisplayName("3. Warlord")
    void fight003() {

        Warrior unit1 = WarriorClasses.factory(WARLORD);
        Warrior unit2 = WarriorClasses.factory(KNIGHT);

        var res = fight(unit1, unit2);
        assertTrue(res);
        ///////////////
        unit1 = WarriorClasses.factory(WARLORD);
        unit2 = WarriorClasses.factory(KNIGHT);

        res = fight(unit2, unit1);
        assertFalse(res);
    }

    @Test
    @DisplayName("23. Warlord")
    void fight004() {

        Army army1 = new Army()
                .addUnits(WARLORD, 1)
                .addUnits(WARRIOR, 2)
                .addUnits(LANCER, 2)
                .addUnits(HEALER, 2);

        Army army2 = new Army()
                .addUnits(WARLORD, 1)
                .addUnits(VAMPIRE, 1)
                .addUnits(HEALER, 2)
                .addUnits(KNIGHT, 2);

        army1.moveUnits();
        army2.moveUnits();

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("24. Warlord")
    void fight005() {


        Army army1 = new Army()
                .addUnits(WARRIOR, 2)
                .addUnits(LANCER, 2)
                .addUnits(DEFENDER, 1)
                .addUnits(WARLORD, 3);

        Army army2 = new Army()
                .addUnits(WARLORD, 2)
                .addUnits(VAMPIRE, 1)
                .addUnits(HEALER, 5)
                .addUnits(KNIGHT, 2);

        army1.moveUnits();
        army2.moveUnits();

        var res = fight(army1, army2);
        assertFalse(res);

    }

    @Test
    @DisplayName("25. Warlord")
    void fight006() {


        Army army1 = new Army()
                .addUnits(WARRIOR, 2)
                .addUnits(LANCER, 3)
                .addUnits(DEFENDER, 1)
                .addUnits(WARLORD, 4);

        Army army2 = new Army()
                .addUnits(WARLORD, 1)
                .addUnits(VAMPIRE, 1)
                .addUnits(ROOKIE, 1)
                .addUnits(KNIGHT, 1);

        List<Warrior> list1 = new ArrayList<>(army1.getArmy());
        List<Warrior> list2 = new ArrayList<>(army2.getArmy());

        list1.get(0).equipWeapon(WeaponClasses.factory(SWORD));
        list2.get(0).equipWeapon(WeaponClasses.factory(SHIELD));

        army1.moveUnits();
        army2.moveUnits();

        var res = fight(army1, army2);
        assertTrue(res);


    }

    @Test
    @DisplayName("26. Warlord")
    void fight007() {

        Army army1 = new Army()
                .addUnits(WARRIOR, 2)
                .addUnits(LANCER, 3)
                .addUnits(DEFENDER, 1)
                .addUnits(WARLORD, 1);

        Army army2 = new Army()
                .addUnits(WARLORD, 5)
                .addUnits(VAMPIRE, 1)
                .addUnits(ROOKIE, 1)
                .addUnits(KNIGHT, 1);

        List<Warrior> list1 = new ArrayList<>(army1.getArmy());
        List<Warrior> list2 = new ArrayList<>(army2.getArmy());

        list1.get(0).equipWeapon(WeaponClasses.factory(SWORD));
        list2.get(0).equipWeapon(WeaponClasses.factory(SHIELD));

        army1.moveUnits();
        army2.moveUnits();

        var res = fight(army1, army2);
        assertTrue(res);

    }
}
