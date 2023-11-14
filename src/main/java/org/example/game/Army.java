package org.example.game;

import org.example.game.interfaces.CanAcceptDamage;
import org.example.game.interfaces.CanHeal;

import java.util.*;
import java.util.function.Supplier;

public class Army implements Iterable<Warrior> {

    private static int idCounter = 0;
    private final int id = ++idCounter;
    private Deque<WarriorInArmyImpl> army;
    private List<CanHeal> healers;

    public Army addUnits(WarriorClasses warriorClasses, int count) {
        return addUnits(warriorClasses::make, count);
    }

    public Army addUnits(Supplier<Warrior> warriorFactory, int count) {
        if (army == null) {
            army = new ArrayDeque<>();
        }
        if (healers == null) {
            healers = new ArrayList<>();
        }
        for (int i = 0; i < count; i++) {
            var currentLast = army.peekLast();
            Warrior warriorToAdd = warriorFactory.get();
            WarriorInArmyImpl noviceInArmy = new WarriorInArmyImpl(warriorToAdd);
            Optional<WarriorInArmyImpl> currentLastOptional = Optional.ofNullable(currentLast);
            if (currentLast != null) {
                currentLast.setWarriorBehind(noviceInArmy);
            }
            army.add(noviceInArmy);
            if (currentLast != null && warriorToAdd instanceof HealerImpl healer) {
                healer.setFrontWarrior(currentLast.getMainWarrior());
                healers.add(healer);
            }
        }
        return this;
    }

    @Override
    public Iterator<Warrior> iterator() {
        return new FirstAliveIterator();
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

    public interface Command {
    }


    enum ChampionDealsHit implements Command{
        INSTANCE
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

        public Warrior getMainWarrior() {
            return warrior;
        }

        @Override
        public void acceptDamage(int damage) {
            warrior.acceptDamage(damage);
        }

        void passCommand(Command command, WarriorInArmy passer) {
            if (passer != this) {
                if (command instanceof ChampionDealsHit && warrior instanceof CanHeal healer) {
                    healer.heal(((WarriorInArmyImpl)passer).getMainWarrior());
                }
            }
            getWarriorBehind().ifPresent(w -> ((WarriorInArmyImpl) w).passCommand(command, this));
        }

        @Override
        public void hit(CanAcceptDamage opponent) {
            warrior.hit(opponent);
            passCommand(ChampionDealsHit.INSTANCE, this);
//            Army.this.healers.forEach(CanHeal::heal);
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

    }

}
