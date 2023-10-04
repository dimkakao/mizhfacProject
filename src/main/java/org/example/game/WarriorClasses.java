package org.example.game;

public enum WarriorClasses {
    WARRIOR, KNIGHT, DEFENDER;

    public static Warrior factory(WarriorClasses warriorClass) {
        return switch (warriorClass) {
            case WARRIOR -> new WarriorImpl();
            case KNIGHT -> new KnightImpl();
            case DEFENDER -> new DefenderImpl();
        };
    }

    public Warrior make() {
        return factory(this);
    }
}
