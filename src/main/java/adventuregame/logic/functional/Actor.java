package adventuregame.logic.functional;

import adventuregame.logic.TileType;

abstract class Actor<T extends Actor<T>> {

    private final Location location;

    Actor(Location location) {
        this.location = location;
    }

    Location getLocation() {
        return location;
    }

    abstract T create(Location location);

    abstract public TileType getTileType();

}
