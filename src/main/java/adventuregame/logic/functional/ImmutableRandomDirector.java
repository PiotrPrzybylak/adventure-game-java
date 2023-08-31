package adventuregame.logic.functional;

import adventuregame.logic.Direction;

import java.util.Random;

class ImmutableRandomDirector implements RandomDirector {

    private final Random random;
    private final Direction direction;

    private ImmutableRandomDirector next;

    public ImmutableRandomDirector() {
        this(new Random());
    }

    private ImmutableRandomDirector(Random random) {
        this.random = random;
        direction = Direction.values()[random.nextInt(Direction.values().length)];
    }


    @Override
    public Direction getRandomDirection() {
        return direction;
    }

    public ImmutableRandomDirector next() {
        if (next == null) {
            next = new ImmutableRandomDirector(random);
        }
        return next;
    }
}
