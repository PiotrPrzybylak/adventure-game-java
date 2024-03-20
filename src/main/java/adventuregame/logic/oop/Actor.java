package adventuregame.logic.oop;

import adventuregame.logic.TileType;

abstract class Actor {
    protected Cell cell;
    protected int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
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
