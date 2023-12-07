package org.example.game;

import org.example.game.interfaces.CanMoveUnits;
import org.example.game.interfaces.HasDefence;
import org.example.game.interfaces.Warrior;
import org.example.game.interfaces.Weapon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class WarlordImpl extends AbstractWarrior implements CanMoveUnits, HasDefence {

    private static final int ATTACK = 4;
    private static final int INITIAL_HEALTH = 100;
    private static final int DEFENCE = 2;

    public WarlordImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getDefence() {
        return DEFENCE + getTotalWeaponDefence();
    }

    @Override
    public void acceptDamage(int damage) {
        int reducedDamage = Math.max(0, damage - getDefence());
        super.acceptDamage(reducedDamage);
    }

    private int getTotalWeaponDefence() {
        return weaponList.stream().map(Weapon::getDefence).reduce(0, Integer::sum);
    }

    @Override
    public Collection<Warrior> moveUnits(Collection<Warrior> army) {


        ArrayList<Warrior> list = new ArrayList<>();

        list.addAll(army.stream().filter(warrior -> warrior instanceof LancerImpl).toList());

        list.addAll(army.stream().filter(warrior -> !(warrior instanceof LancerImpl)).toList());

        list.removeIf((warrior) -> !warrior.isAlive());

        Warrior firstPosition = null;
        Warrior secondPosition = null;
        Warrior warlord = null;

        Iterator<Warrior> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (firstPosition != null && secondPosition != null & warlord != null) break;

            Warrior warrior = iterator.next();
            if (warrior == null) continue;
            if (warrior instanceof HealerImpl) {
                if (secondPosition == null) {
                    secondPosition = warrior;
                }
            } else if (warrior instanceof WarlordImpl && warlord == null) {
                warlord = warrior;
            } else if (firstPosition == null) {
                firstPosition = warrior;
            }
        }

        list.remove(firstPosition);
        list.remove(secondPosition);
        list.remove(warlord);

        Optional.ofNullable(firstPosition).ifPresent((warrior) -> list.add(0, warrior));
        Optional.ofNullable(secondPosition).ifPresent((warrior) -> list.add(Math.min(list.size(), 1), warrior));
        Optional.ofNullable(warlord).ifPresent(list::add);
        return list;
    }
}
