package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.game.*;

import static org.example.game.Game.fight;

@Slf4j(topic = "dfdf.dfdf.dfdfdfdfdff.dfdfd.dfdfdfdbgbgbg")
public class Main {

    public static void main(String[] args) {

        AbstractWarrior lancer1 = new VampireImpl();
        AbstractWarrior defender2 = new HealerImpl();

        var res = fight(lancer1, defender2);
        System.out.println(res);
    }
}