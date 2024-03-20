package adventuregame.logic.oop;

import adventuregame.logic.Direction;
import adventuregame.logic.TileType;

import java.util.Random;

class Skeleton implements Actor {

    private final RandomDirector director;

    private Cell cell;

    private int health = 10;

    public Skeleton(Cell cell, RandomDirector director) {
        this.cell = cell;
        this.cell.setActor(this);
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

    @Override
    public void fight() {
        health -= 5;
        if (health <= 0) {
            cell.setActor(null);
        }
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

    // TODO only used in tests
    public int getHealth() {
        return health;
    }
}
