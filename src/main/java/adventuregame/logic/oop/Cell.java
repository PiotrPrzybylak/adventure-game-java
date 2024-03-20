package adventuregame.logic.oop;

import adventuregame.logic.TileType;

class Cell {
    private TileType type;
    private Actor actor;
    private final GameMap gameMap;
    private final int x;
    private final int y;

    private boolean hasKey;

    private Player player;

    Cell(GameMap gameMap, int x, int y, TileType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    public TileType getTileType() {
        if (actor != null) {
            return actor.getTileType();
        }
        if (player != null) {
            return player.getTileType();
        }
        if (hasKey) {
            return TileType.KEY;
        }
        return  type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean canEnter() {
        return (type == TileType.FLOOR || type == TileType.STAIRS) && actor == null & player == null;
    }

    public void addKey() {
        if (type != TileType.FLOOR) {
            throw new RuntimeException("Key can only be placed on the floor!");
        }
        hasKey = true;
    }

    @Override
    public String toString() {
        return "Cell{" +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public void removePlayer() {
        player = null;
    }

    public void setPlayer(Player player) {
        this.player = player;
        if (type == TileType.STAIRS) {
            gameMap.loadNextLevel();
        }
    }

    public Player getPlayer() {
        return player;
    }
}
