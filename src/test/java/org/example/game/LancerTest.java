package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.game.Game.fight;
import static org.example.game.WarriorClasses.*;
import static org.junit.jupiter.api.Assertions.*;

public class LancerTest {

    @Test
    @DisplayName("1. 7L + 3V + 4W +2D > 4W + 4D + 6V +4L")
    void fight01() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(LANCER, 7);
        army1.addUnits(VAMPIRE, 3);
        army1.addUnits(WARRIOR, 4);
        army1.addUnits(DEFENDER, 2);

        army2.addUnits(WARRIOR, 4);
        army2.addUnits(DEFENDER, 4);
        army2.addUnits(VAMPIRE, 6);
        army2.addUnits(LANCER, 4);

        var res = fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("2. (2W) < (1L + 1W)")
    void fight02() {

        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(LANCER, 1);

        army2.addUnits(WARRIOR, 1);
        army2.addUnits(KNIGHT, 1);

        var res = fight(army1, army2);
        assertFalse(res);

    }

    @Test
    @DisplayName("3. (1L) > (1W) | 1K")
    void fight03() {
        Warrior lancer = LANCER.make();
        Army army = new Army();


        army.addUnits(WARRIOR::make, 1);
        army.addUnits(KNIGHT::make, 1);

        Warrior warrior = army.getArmy().peek();
        Warrior knight = army.getArmy().peekLast();
        assert warrior != null;
        assert knight != null;

        assertTrue(fight(lancer, army.getArmy().peek()));
        assertAll(
                () -> assertTrue(lancer.isAlive()),
                () -> assertEquals(10, lancer.getHealth()),
                () -> assertFalse(warrior.isAlive()),
                () -> assertEquals(-4, warrior.getHealth()),
                () -> assertTrue(knight.isAlive()),
                () -> assertEquals(23, knight.getHealth())
        );
    }

    @Test
    @DisplayName("4. (1L) > (2D)")
    void fight04() {
        Warrior lancer = LANCER.make();
        Army army = new Army();


        army.addUnits(DEFENDER::make, 2);

        Warrior firstOpponent = army.getArmy().peek();
        Warrior secondOpponent = army.getArmy().peekLast();
        assert firstOpponent != null;
        assert secondOpponent != null;

        assertTrue(fight(lancer, firstOpponent));
        assertAll(
                () -> assertTrue(lancer.isAlive()),
                () -> assertEquals(8, lancer.getHealth()),
                () -> assertFalse(firstOpponent.isAlive()),
                () -> assertEquals(0, firstOpponent.getHealth()),
                () -> assertTrue(secondOpponent.isAlive()),
                () -> assertEquals(60, secondOpponent.getHealth())
        );
    }
}
