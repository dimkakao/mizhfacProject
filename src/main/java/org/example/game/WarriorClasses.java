package org.example.game;

public enum WarriorClasses {
    WARRIOR, KNIGHT, DEFENDER, ROOKIE, VAMPIRE, LANCER;

    public static Warrior factory(WarriorClasses warriorClass) {
        return switch (warriorClass) {
            case WARRIOR -> new WarriorImpl();
            case KNIGHT -> new KnightImpl();
            case DEFENDER -> new DefenderImpl();
            case ROOKIE -> new RookieImpl();
            case VAMPIRE -> new VampireImpl();
            case LANCER -> new LancerImpl();
        };
    }

    public Warrior make() {
        return factory(this);
    }
}
