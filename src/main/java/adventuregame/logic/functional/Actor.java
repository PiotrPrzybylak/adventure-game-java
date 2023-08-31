package adventuregame.logic.functional;

import adventuregame.logic.Direction;
import adventuregame.logic.TileType;

abstract class Actor<T extends Actor<T>> {

    private final Location location;

    Actor(Location location) {
        this.location = location;
    }

    Location getLocation() {
        return location;
    }

    @SuppressWarnings("unchecked")
    T move(Direction direction, GameMap map) {
        return map.getNeighbor(location, direction)
                .filter(map::canEnter)
                .map(this::create)
                .orElse((T) this);
    }

    abstract T create(Location location);

    abstract public TileType getTileType();

}
