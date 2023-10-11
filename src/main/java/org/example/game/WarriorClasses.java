package org.example.game;

public enum WarriorClasses {
    WARRIOR, KNIGHT, DEFENDER, ROOKIE;

    public static Warrior factory(WarriorClasses warriorClass) {
        return switch (warriorClass) {
            case WARRIOR -> new WarriorImpl();
            case KNIGHT -> new KnightImpl();
//            case DEFENDER -> new DefenderImpl();
            case DEFENDER -> new DefenderImpl2();
            case ROOKIE -> new RookieImpl();
        };
    }

    public Warrior make() {
        return factory(this);
    }
}
