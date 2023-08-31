package adventuregame.logic.functional;

import adventuregame.logic.TileType;

class Skeleton extends Actor<Skeleton> {

    private final ImmutableRandomDirector random;

    private final int health;

    public Skeleton(Location location) {
        this(location, new ImmutableRandomDirector(), 10);
    }

    private Skeleton(Location location, ImmutableRandomDirector random, int health) {
        super(location);
        this.random = random;
        this.health = health;
    }

    @Override
    public TileType getTileType() {
        return TileType.SKELETON;
    }

    public Skeleton move(GameMap map) {
        return move(random.getRandomDirection(), map);
    }

    @Override
    Skeleton create(Location location) {
        return new Skeleton(location, random.next(), health);
    }

    public Skeleton fight() {
        return new Skeleton(getLocation(), random.next(), health - 5);
    }

    public boolean isAlive() {
        return health > 0;
    }
}
