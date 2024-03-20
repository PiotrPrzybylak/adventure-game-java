package adventuregame.logic.oop;

import adventuregame.logic.Direction;
import adventuregame.logic.TileType;

import java.util.Random;

class Skeleton extends Actor {

    private static final Random random = new Random();
    private final RandomDirector director;

    public Skeleton(Cell cell, RandomDirector director) {
        super(cell);
        this.director = director;
    }

    @Override
    public TileType getTileType() {
        return TileType.SKELETON;
    }

    @Override
    public void move() {
        move(director.getRandomDirection());
    }

    private void move(Direction direction) {
        move(direction.dx, direction.dy);
    }

    private void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell == null) {
        } else if (nextCell.canEnter()) {
            goTo(nextCell);
        } else if (nextCell.getPlayer() != null) {
            Player enemy = nextCell.getPlayer();
            enemy.fight();
            fight();
        }
    }

    private void goTo(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }


    @Override
    public void fight() {
        health -= 5;
        if (health <= 0) {
            cell.setActor(null);
        }
    }
}
