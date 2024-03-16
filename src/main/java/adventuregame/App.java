package adventuregame;

import adventuregame.infrastructure.level.ClassPathLevelsProvider;
import adventuregame.logic.Game;
import adventuregame.logic.MapLoader;
import adventuregame.ui.console.ConsoleUI;
import adventuregame.ui.javafx.JavaFxUI;

public class App {
    public static void main(String[] args) {

        boolean oop = args.length > 0 && args[0].contains("oop");
        boolean console = args.length > 0 && args[0].contains("console");

        Game game = loadGameMap(oop);

        if (console) {
            ConsoleUI.start(game);
        } else {
            JavaFxUI.start(game);
        }
    }

    public static Game loadGameMap(boolean oop) {
        MapLoader mapLoader = MapLoader.getMapLoader(oop,new ClassPathLevelsProvider());
        return mapLoader.loadMap(1);
    }
}
