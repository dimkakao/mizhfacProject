package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.game.Game.fight;
import static org.example.game.WarriorClasses.*;
import static org.junit.jupiter.api.Assertions.*;

public class VampireTest {

//    @Test
//    @DisplayName("1. 5D + 6V + 7W < 6W + 6D + 6V")
//    void fight01() {
//        Army army1 = new Army();
//        Army army2 = new Army();
//
//        army1.addUnits(DEFENDER, 5);
//        army1.addUnits(VAMPIRE, 6);
//        army1.addUnits(WARRIOR, 7);
//
//        army2.addUnits(WARRIOR, 6);
//        army2.addUnits(DEFENDER, 6);
//        army2.addUnits(VAMPIRE, 6);
//
//        var res = fight(army1, army2);
//        assertFalse(res);
//    }

    @Test
    @DisplayName("2. (2D+ 3V + 4W) < (4W + 5D + 3V)")
    void fight02() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(DEFENDER, 2);
        army1.addUnits(VAMPIRE, 3);
        army1.addUnits(WARRIOR, 4);

        army2.addUnits(WARRIOR, 4);
        army2.addUnits(DEFENDER, 5);
        army2.addUnits(VAMPIRE, 3);

        var res = fight(army1, army2);
        assertFalse(res);
    }

    @Test
    @DisplayName("3. (11D+ 3V + 4W) > (4W + 4D + 13V)")
    void fight03() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(DEFENDER, 11);
        army1.addUnits(VAMPIRE, 3);
        army1.addUnits(WARRIOR, 4);

        army2.addUnits(WARRIOR, 4);
        army2.addUnits(DEFENDER, 4);
        army2.addUnits(VAMPIRE, 13);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("4. (9D+ 3V + 8W) > (4W + 4D + 13V)")
    void fight04() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(DEFENDER, 9);
        army1.addUnits(VAMPIRE, 3);
        army1.addUnits(WARRIOR, 8);

        army2.addUnits(WARRIOR, 4);
        army2.addUnits(DEFENDER, 4);
        army2.addUnits(VAMPIRE, 13);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("5. 1D > 1")
    void fight05() {
        Warrior defender = DEFENDER.make();
        Warrior vampire = VAMPIRE.make();

        var res = fight(defender,vampire);
        assertTrue(res);
    }

}
