package adventuregame.ui.console;

import adventuregame.logic.Direction;
import adventuregame.logic.Game;
import adventuregame.logic.TileType;

import java.util.Map;

import static adventuregame.logic.TileType.EMPTY;
import static adventuregame.logic.TileType.FLOOR;
import static adventuregame.logic.TileType.KEY;
import static adventuregame.logic.TileType.PLAYER;
import static adventuregame.logic.TileType.SKELETON;
import static adventuregame.logic.TileType.STAIRS;
import static adventuregame.logic.TileType.WALL;

public class ConsoleUI {
    private Game game;
    private final static Console console = new Console();

    public final static Map<TileType, Character> tilesToChars = Map.of(
            EMPTY, ' ',
            FLOOR, '.',
            WALL, '#',
            SKELETON, 'S',
            PLAYER, '@',
            STAIRS, 'H',
            KEY, 'K');

    private ConsoleUI(Game game) {
        this.game = game;
    }

    public static void start(Game game) {
        new ConsoleUI(game).start();
    }

    private void start() {
        char key;
        do {
            refresh();
            key = console.getKey();
            Direction direction = switch (key) {
                case 'w' -> Direction.NORTH;
                case 's' -> Direction.SOUTH;
                case 'a' -> Direction.WEST;
                case 'd' -> Direction.EAST;
                default -> null;
            };
            if (direction != null) {
                game = game.movePlayer(direction);
            }
        } while (key != 'q');
    }

    private void refresh() {
        console.clearScreen();
        StringBuilder mapAsString = new StringBuilder();
        for (int y = 0; y < game.height(); y++) {
            for (int x = 0; x < game.width(); x++) {
                TileType cell = game.getDrawable(x, y);
                mapAsString.append(tilesToChars.get(cell));
            }
            mapAsString.append("\n");
        }

        console.println("Health: " + game.getPlayerHealth());
        console.println(mapAsString.toString());
    }


}
