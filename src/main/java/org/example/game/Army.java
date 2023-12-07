package org.example.game;

import org.example.game.interfaces.*;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Army implements Iterable<Warrior> {

    private static int idCounter = 0;
    private final int id = ++idCounter;
    private Deque<WarriorInArmyImpl> army;
    private CanMoveUnits warlord;

    public void moveUnits() {
        if (warlord == null) return;
        Collection<Warrior> newArmy = warlord.moveUnits(army.stream()
                .map(WarriorInArmyImpl::unwrap)
                .collect(Collectors.toList()));
        warlord = null;
        army.clear();
        newArmy.forEach(this::addWarrior);
    }

    public Army addUnits(WarriorClasses warriorClasses, int count) {
        return addUnits(warriorClasses::make, count);
    }

    public void addWarrior(Warrior warriorToAdd) {
        WarriorInArmyImpl currentLast = army.peekLast();
        if (warriorToAdd instanceof CanMoveUnits warlordToAdd) {
            if (warlord == null) {
                warlord = warlordToAdd;
            } else return;
        }
        WarriorInArmyImpl noviceInArmy = new WarriorInArmyImpl(warriorToAdd);

        if (currentLast != null) {
            currentLast.setWarriorBehind(noviceInArmy);
            if (warriorToAdd instanceof HealerImpl healer) {
                healer.setFrontWarrior(currentLast.unwrap());
            }
        }
        army.add(noviceInArmy);
    }

    public Army addUnits(Supplier<Warrior> warriorFactory, int count) {
        if (army == null) {
            army = new ArrayDeque<>();
        }
        for (int i = 0; i < count; i++) {
            addWarrior(warriorFactory.get());
        }
        return this;
    }

    @Override
    public Iterator<Warrior> iterator() {
        return allAliveIterator();
    }

    public Iterator<Warrior> firstAliveIterator() {
        return new FirstAliveIterator();
    }

    public Iterator<Warrior> allAliveIterator() {
        return army.stream()
                .filter(Warrior::isAlive)
                .map(Army.WarriorInArmyImpl::unwrap)
                .iterator();
    }

    public Deque<WarriorInArmyImpl> getArmy() {
        return army;
    }

    public Warrior getNextWarrior() {
        return army.poll();
    }

    @Override
    public String toString() {
        return "Army{" + "#" + id + ", army=" + army + '}';
    }

    public boolean isEmpty() {
        return !new FirstAliveIterator().hasNext();
    }

    enum ChampionDealsHit implements Command {
        INSTANCE
    }

    public interface Command {
    }

    private class FirstAliveIterator implements Iterator<Warrior> {

        @Override
        public boolean hasNext() {
            if (!army.isEmpty() && !army.peek().isAlive()) army.poll();
            return !army.isEmpty();
        }

        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return army.peek();
        }
    }

    private class WarriorInArmyImpl implements WarriorInArmy {
        private final Warrior warrior;
        private WarriorInArmy warriorBehind;

        public WarriorInArmyImpl(Warrior warrior) {
            this.warrior = Objects.requireNonNull(warrior);
        }

        @Override
        public Optional<WarriorInArmy> getWarriorBehind() {
            return Optional.ofNullable(warriorBehind);
        }

        public void setWarriorBehind(WarriorInArmy warriorBehind) {
            this.warriorBehind = Objects.requireNonNull(warriorBehind);
        }

        public Warrior unwrap() {
            return warrior;
        }

        @Override
        public void acceptDamage(int damage) {
            warrior.acceptDamage(damage);
        }

        void passCommand(Command command, WarriorInArmyImpl passer) {
            if (passer != this) {
                if (command instanceof ChampionDealsHit && warrior instanceof CanHeal healer) {
                    healer.heal(passer.unwrap());
                }
            }
            getWarriorBehind().ifPresent(w -> ((WarriorInArmyImpl) w).passCommand(command, this));
        }

        @Override
        public void hit(CanAcceptDamage opponent) {
            warrior.hit(opponent);
            passCommand(ChampionDealsHit.INSTANCE, this);
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        @Override
        public boolean isAlive() {
            return warrior.isAlive();
        }

        @Override
        public String toString() {
            return warrior.toString();
        }

        @Override
        public void equipWeapon(Weapon weapon) {
            warrior.equipWeapon(weapon);
        }
    }

}
