package adventuregame.logic.oop;

import adventuregame.logic.TileType;

class Cell {
    private TileType type;
    private Actor actor;
    private final GameMap gameMap;
    private final int x;
    private final int y;

    private boolean hasKey;

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
        if (type == TileType.STAIRS && actor instanceof Player) {
            gameMap.loadNextLevel();
        }
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    public TileType getTileType() {
        return actor != null ? actor.getTileType() : hasKey ? TileType.KEY : type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean canEnter() {
        return (type == TileType.FLOOR || type == TileType.STAIRS) && actor == null;
    }

    public void addKey() {
        if (type != TileType.FLOOR) {
            throw new RuntimeException("Key can only be placed on the floor!");
        }
        hasKey = true;
    }
}
