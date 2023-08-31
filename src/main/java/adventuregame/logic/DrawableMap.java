package adventuregame.logic;

public interface DrawableMap {
    int width();

    int height();

    TileType getDrawable(int x, int y);

}
