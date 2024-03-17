package adventuregame.logic.oop;

import adventuregame.logic.Direction;
import adventuregame.logic.Game;
import adventuregame.logic.TileType;

import java.util.ArrayList;
import java.util.List;

class GameMap implements Game {
    private final int width;
    private final int height;
    private final int level;
    private final MapLoader mapLoader;
    private final Cell[][] cells;

    private Player player;

    private Game next = this;

    public GameMap(int width, int height, TileType defaultCellType, int level, MapLoader mapLoader) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.mapLoader = mapLoader;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void moveMonsters() {
        List<Actor> actors = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Actor actor = cells[x][y].getActor();
                if (actor != null) {
                    actors.add(actor);
                }
            }
        }
        for (Actor actor : actors) {
            actor.move();
        }
    }

    @Override
    public Game movePlayer(Direction direction) {
        player.move(direction);
        moveMonsters();
        return next;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public TileType getDrawable(int x, int y) {
        return getCell(x, y).getTileType();
    }

    @Override
    public int getPlayerHealth() {
        return getPlayer().getHealth();
    }

    @Override
    public Game moveGame() {
        moveMonsters();
        return this;
    }

    public void loadNextLevel() {
        next = mapLoader.loadMap(level + 1);
    }
}
