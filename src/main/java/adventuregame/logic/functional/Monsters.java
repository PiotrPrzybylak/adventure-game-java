package adventuregame.logic.functional;

import java.util.List;
import java.util.Optional;

class Monsters {

    private final ImmutableList<Skeleton> monsters;

    Monsters(ImmutableList<Skeleton> monsters) {
        this.monsters = monsters;
    }

    Monsters(List<Skeleton> monsters) {
        this(new ImmutableList<>(monsters));
    }

    Monsters moveMonster(int i, GameMap map) {
        return replaceMonster(i, monsters.get(i).move(map));
    }

    int size() {
        return monsters.size();
    }

    boolean isAMonsterHere(Location location) {
        return getMonsterAtLocation(location).isPresent();
    }

    Optional<Skeleton> getMonsterAtLocation(Location location) {
        return getMonsterIndexAtLocation(location).map(monsters::get);
    }

    private Optional<Integer> getMonsterIndexAtLocation(Location location) {
        for (int i = 0; i < monsters.size(); i++) {
            Skeleton skeleton = monsters.get(i);
            if (location.isSamePlace(skeleton.getLocation()) && skeleton.isAlive()) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    Monsters fight(Location location) {
        return getMonsterIndexAtLocation(location)
                .map(i -> replaceMonster(i, monsters.get(i).fight()))
                .orElse(this);
    }

    private Monsters replaceMonster(int i, Skeleton newElement) {
        return new Monsters(monsters.replaceElementAt(i, newElement));
    }
}
