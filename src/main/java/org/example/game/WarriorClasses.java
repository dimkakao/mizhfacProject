package org.example.game;

public enum WarriorClasses {
    WARRIOR, KNIGHT, DEFENDER, ROOKIE, VAMPIRE, LANCER, HEALER;

    public static Warrior factory(WarriorClasses warriorClass) {
        return switch (warriorClass) {
            case WARRIOR -> new WarriorImpl();
            case KNIGHT -> new KnightImpl();
            case DEFENDER -> new DefenderImpl();
            case ROOKIE -> new RookieImpl();
            case VAMPIRE -> new VampireImpl();
            case LANCER -> new LancerImpl();
            case HEALER -> new HealerImpl();
        };
    }

    public Warrior make() {
        return factory(this);
    }
}
