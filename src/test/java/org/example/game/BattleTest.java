package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.game.WarriorClasses.KNIGHT;
import static org.example.game.WarriorClasses.WARRIOR;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleTest {

    @Test
    @DisplayName("1. Battle: 1 Warriors < 2 Warriors")
    void battle01() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(WARRIOR, 1);
        army2.addUnits(WARRIOR, 2);

        var res = Game.fight(army1, army2);
        assertFalse(res);
    }

    //     "2. Battle": [
//    prepare_test(middle_code='''army_1 = Army()
//            army_2 = Army()
//army_1.add_units(Warrior, 2)
//            army_2.add_units(Warrior, 3)
//    battle = Battle()''',
//    test="battle.fight(army_1, army_2)",
//    answer=False)
//            ],
    @Test
    @DisplayName("2. Battle: 2 Warriors < 3 Warriors")
    void battle02() {
        var army1 = new Army().addUnits(WARRIOR, 2);
        var army2 = new Army().addUnits(WARRIOR, 3);
        var res = Game.fight(army1, army2);
        assertFalse(res);
    }

    //            "3. Battle": [
//    prepare_test(middle_code='''army_1 = Army()
//            army_2 = Army()
//army_1.add_units(Warrior, 5)
//            army_2.add_units(Warrior, 7)
//    battle = Battle()''',
//    test="battle.fight(army_1, army_2)",
//    answer=False)
//            ],
    @Test
    @DisplayName("3. Battle: 5 Warriors < 7 Warriors")
    void battle03() {
        var army1 = new Army().addUnits(WARRIOR, 5);
        var army2 = new Army().addUnits(WARRIOR, 7);
        var res = Game.fight(army1, army2);
        assertFalse(res);
    }

    //            "4. Battle": [
//    prepare_test(middle_code='''army_1 = Army()
//            army_2 = Army()
//army_1.add_units(Warrior, 20)
//            army_2.add_units(Warrior, 21)
//    battle = Battle()''',
//    test="battle.fight(army_1, army_2)",
//    answer=True)
//            ],
    @Test
    @DisplayName("4. Battle: 20 Warriors > 21 Warriors")
    void battle04() {
        var army1 = new Army().addUnits(WARRIOR, 20);
        var army2 = new Army().addUnits(WARRIOR, 21);
        var res = Game.fight(army1, army2);
        assertTrue(res);
    }

    //            "5. Battle": [
//    prepare_test(middle_code='''army_1 = Army()
//            army_2 = Army()
//army_1.add_units(Warrior, 10)
//            army_2.add_units(Warrior, 11)
//    battle = Battle()''',
//    test="battle.fight(army_1, army_2)",
//    answer=True)
//            ],
    @Test
    @DisplayName("5. Battle: 10 Warriors > 11 Warriors")
    void battle05() {
        var army1 = new Army().addUnits(WARRIOR, 10);
        var army2 = new Army().addUnits(WarriorImpl::new, 11);
        var res = Game.fight(army1, army2);
        assertTrue(res);
    }

    //            "6. Battle": [prepare_test(middle_code='''army_1 = Army() army_2 = Army()
//army_1.add_units(Warrior, 11)     army_2.add_units(Warrior, 7) battle = Battle()''',
//    test="battle.fight(army_1, army_2)",
//    answer=True)
//            ]
    @Test
    @DisplayName("6. Battle: 11 Warriors > 7 Warriors")
    void battle06() {
        var army1 = new Army().addUnits(WarriorImpl::new, 11);
        var army2 = new Army().addUnits(WARRIOR, 7);
        var res = Game.fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("7. Battle: 20 Warriors + 5 Knights < 30 Warriors")
    void smokeTest() {
        var army1 = new Army()
                .addUnits(WarriorImpl::new, 20)
                .addUnits(KNIGHT, 5);
        var army2 = new Army().addUnits(WARRIOR, 30);
        var res = Game.fight(army1, army2);
        assertFalse(res);
    }

    @Test
    @DisplayName("8. Battle: 20 Warriors + 5 Knights > 25 Warriors")
    void smokeTest2() {
        var army1 = new Army()
                .addUnits(WarriorImpl::new, 20)
                .addUnits(KNIGHT, 5);
        var army2 = new Army().addUnits(WARRIOR, 25);
        var res = Game.fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("9. Battle: 20 Warriors > 10 Knight")
    void smokeTest3() {
        var army1 = new Army().addUnits(WarriorImpl::new, 20);
        var army2 = new Army().addUnits(KnightImpl::new, 10);
        var res = Game.fight(army1, army2);
        assertTrue(res);
    }
}
