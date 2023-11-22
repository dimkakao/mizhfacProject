package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.game.Game.fight;
import static org.example.game.Game.fightStraight;
import static org.example.game.WarriorClasses.*;
import static org.junit.jupiter.api.Assertions.*;

public class StraightFightTest {

    @Test
    @DisplayName("Fight 1")
    void fight01() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1
                .addUnits(WARRIOR, 2)
                .addUnits(KNIGHT, 1);

        army2
                .addUnits(KNIGHT, 1)
                .addUnits(HEALER, 1)
                .addUnits(KNIGHT, 1);

        var res = fightStraight(army1, army2);
        assertTrue(res);
        army1.allAliveIterator().forEachRemaining(System.out::println);
        System.out.println("---");
        army2.allAliveIterator().forEachRemaining(System.out::println);

        assertAll(
                () -> assertEquals(2, army1.getArmy().size()),
                () -> assertEquals(0, army2.getArmy().size())
        );
    }

    @Test
    @DisplayName("Fight 2")
    void fight02() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1
                .addUnits(LANCER, 5)
                .addUnits(VAMPIRE, 3)
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 2);

        army2
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 4)
                .addUnits(VAMPIRE, 6)
                .addUnits(LANCER, 5);

        var res = fightStraight(army1, army2);
        assertFalse(res);
    }

    @Test
    @DisplayName("Fight 3")
    void fight03() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1
                .addUnits(LANCER, 7)
                .addUnits(VAMPIRE, 3)
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 2);

        army2
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 4)
                .addUnits(VAMPIRE, 6)
                .addUnits(LANCER, 4);

        var res = fightStraight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("Fight 4")
    void fight04() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(LANCER, 7);
        army1.addUnits(VAMPIRE, 3);
        army1.addUnits(HEALER, 1);
        army1.addUnits(WARRIOR, 4);
        army1.addUnits(HEALER, 1);
        army1.addUnits(DEFENDER, 2);//18

        army2.addUnits(WARRIOR, 4);
        army2.addUnits(DEFENDER, 4);
        army2.addUnits(HEALER, 1);
        army2.addUnits(VAMPIRE, 6);
        army2.addUnits(LANCER, 4);//19

        var res = fightStraight(army1, army2);
        assertFalse(res);
    }

    @Test
    @DisplayName("Fight 5")
    void fight05() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(LANCER, 4);
        army1.addUnits(WARRIOR, 3);
        army1.addUnits(HEALER, 1);
        army1.addUnits(WARRIOR, 4);
        army1.addUnits(HEALER, 1);
        army1.addUnits(KNIGHT, 2);

        army2.addUnits(WARRIOR, 4);
        army2.addUnits(DEFENDER, 4);
        army2.addUnits(HEALER, 1);
        army2.addUnits(VAMPIRE, 2);
        army2.addUnits(LANCER, 4);

        var res = fightStraight(army1, army2);
        assertTrue(res);
    }

}
