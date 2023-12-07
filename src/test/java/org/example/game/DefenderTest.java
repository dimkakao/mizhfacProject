package org.example.game;

import org.example.game.interfaces.Warrior;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.game.Game.fight;
import static org.example.game.WarriorClasses.*;
import static org.junit.jupiter.api.Assertions.*;

public class DefenderTest {

    @Test
    @DisplayName("1. 1D > 1R")
    void fight01() {
        Warrior warrior1 = DEFENDER.make();
        Warrior warrior2 = ROOKIE.make();
        fight(warrior1, warrior2);
        assertEquals(60, ((AbstractWarrior) warrior1).getHealth());
    }

    @Test
    @DisplayName("2. (1D > 1R) && (1D > 1W)")
    void fight02() {
        Warrior warrior1 = DEFENDER.make();
        Warrior warrior2 = ROOKIE.make();
        Warrior warrior3 = ROOKIE.make();
        fight(warrior1, warrior2);
        var res = fight(warrior1, warrior3);
        assertTrue(res);
    }

    @Test
    @DisplayName("3. (5W + 4D) > (4W + 5D)")
    void fight03() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(WARRIOR, 5);
        army1.addUnits(DEFENDER, 4);

        army2.addUnits(DEFENDER, 5);
        army2.addUnits(WARRIOR, 4);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("4. (5D + 20W) > (21W + 4D)")
    void fight04() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(DEFENDER, 5);
        army1.addUnits(WARRIOR, 20);

        army2.addUnits(WARRIOR, 21);
        army2.addUnits(DEFENDER, 4);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("5. (10W + 15D) > 5W")
    void fight05() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(WARRIOR, 10);
        army1.addUnits(DEFENDER, 5);

        army2.addUnits(WARRIOR, 5);
        army1.addUnits(DEFENDER, 10);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("6. (10W + 15D) > 5W")
    void fight06() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(DEFENDER, 2);
        army1.addUnits(WARRIOR, 1);
        army1.addUnits(DEFENDER, 1);

        army2.addUnits(WARRIOR, 5);

        var res = fight(army1, army2);
        assertFalse(res);
    }

}
