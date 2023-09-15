package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.game.Game.fight;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    @Test
    @DisplayName("1. When warrior fights against knight first should lose")
    void fight01() {

        Warrior chuck = new Warrior();
        Warrior bruce = new Knight();

        boolean result = fight(chuck, bruce);

        assertFalse(result);
    }

    @Test
    @DisplayName("2. When knight fights against warrior first should win")
    void fight02() {

        Warrior carl = new Knight();
        Warrior jim = new Warrior();

        boolean result = fight(carl, jim);

        assertTrue(result);
    }


//        "3. Fight": [
//        prepare_test(middle_code='''bob = Warrior()
//mars = Warrior()
//fight(bob, mars)''',
//                     test="bob.is_alive",
//                     answer=True)

    @Test
    @DisplayName("3. When warrior fights against warrior first should remain alive")
    void fight03() {

        Warrior bob = new Warrior();
        Warrior mars = new Warrior();

        fight(bob, mars);

        assertTrue(bob.isAlive());
    }

//    "4. Fight": [
//    prepare_test(middle_code='''zeus = Knight()
//            godkiller = Warrior()
//    fight(zeus, godkiller)''',
//    test="zeus.is_alive",
//    answer=True)
//            ],

    @Test
    @DisplayName("4. When knight fights against warrior first should remain alive")
    void fight04() {

        Warrior zeus = new Knight();
        Warrior godKiller = new Warrior();

        fight(zeus, godKiller);

        assertTrue(zeus.isAlive());
    }

    //    "5. Fight": [
//    prepare_test(middle_code='''husband = Warrior()
//            wife = Warrior()
//    fight(husband, wife)''',
//    test="wife.is_alive",
//    answer=False)
//            ],

    @Test
    @DisplayName("5. When warrior fights against warrior second should be dead")
    void fight05() {

        Warrior husband = new Warrior();
        Warrior wife = new Warrior();

        fight(husband, wife);

        assertFalse(wife.isAlive());
    }

    //            "6. Fight": [
//    prepare_test(middle_code='''dragon = Warrior()
//            knight = Knight()
//    fight(dragon, knight)''',
//    test="knight.is_alive",
//    answer=True)
//            ],
    @Test
    @DisplayName("6. When warrior fights against knight second should be alive")
    void fight06() {

        Warrior dragon = new Warrior();
        Warrior knight = new Knight();

        fight(dragon, knight);

        assertTrue(knight.isAlive());
    }

//            "7. Fight": [
//    prepare_test(middle_code='''unit_1 = Warrior()
//            unit_2 = Knight()
//    unit_3 = Warrior()
//    fight(unit_1, unit_2)''',
//    test="fight(unit_2, unit_3)",
//    answer=False)
//            ]

    @Test
    @DisplayName("7. When knight fights against warrior first should win")
    void fight07() {

        Warrior unit_2 = new Knight();
        Warrior unit_3 = new Warrior();

        boolean result = fight(unit_2, unit_3);

        assertTrue(result);
    }

}