package adventuregame.logic;

public interface MapLoader {

    Game loadMap(int level);

    static MapLoader getMapLoader(boolean oop, LevelProvider levelProvider) {
        if (oop) {
            return new adventuregame.logic.oop.MapLoader(levelProvider);
        }
        return new adventuregame.logic.functional.MapLoader(levelProvider);
    }
}
