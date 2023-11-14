package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.game.Game.fight;
import static org.example.game.WarriorClasses.*;
import static org.junit.jupiter.api.Assertions.*;

public class HealerTest {

    @Test
    @DisplayName("1. 7L + 3V + 1H + 4W + 1H + 2D > 4W + 4D + 6V +4L")
    void fight01() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(LANCER, 7);
        army1.addUnits(VAMPIRE, 3);
        army1.addUnits(HEALER, 1);
        army1.addUnits(WARRIOR, 4);
        army1.addUnits(HEALER, 1);
        army1.addUnits(DEFENDER, 2);

        army2.addUnits(WARRIOR, 4);
        army2.addUnits(DEFENDER, 4);
        army2.addUnits(HEALER, 1);
        army2.addUnits(VAMPIRE, 6);
        army2.addUnits(LANCER, 4);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    void fight02() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(LANCER, 1);
        army1.addUnits(WARRIOR, 3);
        army1.addUnits(HEALER, 1);
        army1.addUnits(WARRIOR, 4);
        army1.addUnits(HEALER, 1);
        army1.addUnits(KNIGHT, 2);

        army2.addUnits(WARRIOR, 4);
        army2.addUnits(DEFENDER, 4);
        army2.addUnits(HEALER, 1);
        army2.addUnits(VAMPIRE, 6);
        army2.addUnits(LANCER, 4);

        var res = fight(army1, army2);
        assertFalse(res);
    }

    @Test
    @DisplayName("3. (1L) > (1W) | 1H")
    void fight03() {
        Warrior lancer = LANCER.make();
        Army army = new Army();

        army.addUnits(WARRIOR::make, 1);
        army.addUnits(HEALER::make, 1);

        Warrior warrior = army.getArmy().peek();
        Warrior healer = army.getArmy().peekLast();
        assert warrior != null;
        assert healer != null;

        assertFalse(fight(lancer, army.getArmy().peek()));
        assertAll(
                () -> assertFalse(lancer.isAlive()),
                () -> assertEquals(0, lancer.getHealth()),
                () -> assertTrue(warrior.isAlive()),
                () -> assertEquals(10, warrior.getHealth()),
                () -> assertTrue(healer.isAlive()),
                () -> assertEquals(30, healer.getHealth())
        );
    }

    @Test
    @DisplayName("4. (1H + 1W) > (1H + 1W)")
    void fight04() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(HEALER, 1);
        army1.addUnits(WARRIOR, 1);

        army2.addUnits(HEALER, 1);
        army2.addUnits(WARRIOR, 1);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("4. (1D + 1H) < (1D + 1H)")
    void fight05() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(DEFENDER, 1);
        army1.addUnits(HEALER, 1);

        army2.addUnits(DEFENDER, 1);
        army2.addUnits(HEALER, 1);

        var res = fight(army1, army2);
        assertFalse(res);
    }
}
