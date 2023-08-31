package adventuregame.logic.functional;

import adventuregame.logic.TileType;

class Player extends Actor<Player> {
    private final int health;

    public Player(Location location) {
        this(location, 10);
    }

    public Player(Location location, int health) {
        super(location);

        this.health = health;
    }

    public TileType getTileType() {
        return TileType.PLAYER;
    }

    public String getName() {
        return "Plajer";
    }

    public boolean isInPlace(Location location) {
        return this.getLocation().isSamePlace(location);
    }

    public int getHealth() {
        return health;
    }

    Player move(Location location) {
        return new Player(location, health);
    }

    Player fight() {
        return new Player(getLocation(), health - 2);
    }

    @Override
    Player create(Location location) {
        return new Player(location, health);
    }
}
