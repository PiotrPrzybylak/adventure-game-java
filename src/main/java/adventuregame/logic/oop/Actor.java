package adventuregame.logic.oop;

import adventuregame.logic.Direction;
import adventuregame.logic.TileType;

abstract class Actor {
    protected Cell cell;
    protected int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    private void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell == null) {
        } else if (nextCell.canEnter()) {
            goTo(nextCell);
        } else if (nextCell.getActor() != null) {
            Actor enemy = nextCell.getActor();
            enemy.fight();
            health -= 2;
        }
    }

    private void goTo(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getHealth() {
        return health;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void move() {

    }

    public void move(Direction direction) {
        move(direction.dx, direction.dy);
    }

    abstract public TileType getTileType();

    public void fight() {
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "cell=" + cell +
                ", health=" + health +
                '}';
    }
}
