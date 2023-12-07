package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.game.*;

import static org.example.game.Game.fight;

@Slf4j(topic = "dfdf.dfdf.dfdfdfdfdff.dfdfd.dfdfdfdbgbgbg")
public class Main {

    //    static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

//        AbstractWarrior lancer1 = new LancerImpl();
//        AbstractWarrior warrior2 = new WarriorImpl();
//
//        var res = fight(lancer1, warrior2);
//        System.out.println(res);

//        AbstractWarrior lancer1 = new LancerImpl();
//        AbstractWarrior defender2 = new DefenderImpl();
//
//        var res = fight(lancer1, defender2);
//        System.out.println(res);

        AbstractWarrior lancer1 = new VampireImpl();
        AbstractWarrior defender2 = new HealerImpl();

        var res = fight(lancer1, defender2);
        System.out.println(res);


//        void fight04() {
//            Army army1 = new Army();
//            Army army2 = new Army();
//
//            army1.addUnits(LANCER, 7);
//            army1.addUnits(VAMPIRE, 3);
//            army1.addUnits(HEALER, 1);
//            army1.addUnits(WARRIOR, 4);
//            army1.addUnits(HEALER, 1);
//            army1.addUnits(DEFENDER, 2);//18
//
//            army2.addUnits(WARRIOR, 4);
//            army2.addUnits(DEFENDER, 4);
//            army2.addUnits(HEALER, 1);
//            army2.addUnits(VAMPIRE, 6);
//            army2.addUnits(LANCER, 4);//19
//
//            var res = fightStraight(army1, army2);
//            assertFalse(res);
//        }
    }
}