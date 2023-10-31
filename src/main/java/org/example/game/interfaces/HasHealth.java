package org.example.game.interfaces;

public interface HasHealth {
    int getHealth();

    default boolean isAlive() {
        return getHealth() > 0;
    }
}
