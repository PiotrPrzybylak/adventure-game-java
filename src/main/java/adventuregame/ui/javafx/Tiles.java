package adventuregame.ui.javafx;

import adventuregame.logic.TileType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/tiles.png", 199, 67, true, false);
    private static final Map<TileType, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;

        Tile(int x, int y) {
            this.x = 1 + x * (TILE_WIDTH + 1);
            this.y = 1 + y * (TILE_WIDTH + 1);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put(TileType.PLAYER, new Tile(0, 0));
        tileMap.put(TileType.SKELETON, new Tile(1, 0));
        tileMap.put(TileType.WALL, new Tile(2, 0));
        tileMap.put(TileType.EMPTY, new Tile(3, 0));
        tileMap.put(TileType.FLOOR, new Tile(4, 0));
        tileMap.put(TileType.KEY, new Tile(5, 0));
        tileMap.put(TileType.STAIRS, new Tile(0, 1));
    }

    public static void drawTile(GraphicsContext context, TileType tileType, int x, int y) {
        Tile tile = tileMap.get(tileType);
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
