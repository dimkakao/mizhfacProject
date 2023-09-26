package org.example.game;

public enum WarriorClasses {
    WARRIOR, KNIGHT;

    public static Warrior factory(WarriorClasses warriorClass) {
        return switch (warriorClass) {
            case WARRIOR -> new WarriorImpl();
            case KNIGHT -> new KnightImpl();
        };
    }

    public Warrior make() {
        return factory(this);
    }
}
