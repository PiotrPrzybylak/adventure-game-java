package adventuregame.logic.oop;

import adventuregame.logic.Direction;
import adventuregame.logic.TileType;

class Player {

    private Cell cell;
    protected int health = 10;
    public Player(Cell cell) {
        this.cell = cell;
        this.cell.setPlayer(this);
    }

    public TileType getTileType() {
        return TileType.PLAYER;
    }

    public String getName() {
        return "Plajer";
    }

    public void move(Direction direction) {
        move(direction.dx, direction.dy);
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
        cell.removePlayer();
        nextCell.setPlayer(this);
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

}
