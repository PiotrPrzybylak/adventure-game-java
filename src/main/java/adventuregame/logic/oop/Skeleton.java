package adventuregame.logic.oop;

import adventuregame.logic.Direction;
import adventuregame.logic.TileType;

import java.util.Random;

class Skeleton extends Actor {

    private final Random random = new Random();
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public TileType getTileType() {
        return TileType.SKELETON;
    }

    @Override
    public void move() {
        int i = random.nextInt(4);
        Direction direction = Direction.values()[i];
        move(direction);
    }
}
