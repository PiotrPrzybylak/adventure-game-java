package adventuregame.logic;

import adventuregame.ui.console.ConsoleUI;
import org.junit.jupiter.api.Test;
import org.postgresql.util.ReaderInputStream;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class GameMapTest {

    @Test
    public void playerMovesAround() {
        String map =
                """
                        2 1
                        @.""";


        Game game = loadGameFromMap(map);

        game = game.movePlayer(Direction.EAST);
        assertEquals(".@", toString(game));
        game = game.movePlayer(Direction.EAST);
        assertEquals(".@", toString(game));
        game = game.movePlayer(Direction.WEST);
        assertEquals("@.", toString(game));
        game = game.movePlayer(Direction.WEST);
        assertEquals("@.", toString(game));
        game = game.movePlayer(Direction.NORTH);
        assertEquals("@.", toString(game));
        game = game.movePlayer(Direction.SOUTH);
        assertEquals("@.", toString(game));
    }

    @Test
    public void playerCannotMoveOnMonster() {
        String map =
                """
                        2 1
                        @s""";


        Game game = loadGameFromMap(map);

        game = game.movePlayer(Direction.EAST);
        assertEquals("@S", toString(game));
    }

    @Test
    public void playerCannotMoveOntoTheWall() {
        String map =
                """
                        2 1
                        @#""";


        Game game = loadGameFromMap(map);

        game = game.movePlayer(Direction.EAST);
        assertEquals("@#", toString(game));
    }

    @Test
    public void player_and_monsters_runing_around() {
        String map = """
                5 5
                @....
                .....
                s....
                ..s..
                .....
                """;

        Game game = loadGameFromMap(map, () -> Direction.EAST);

        game = game.movePlayer(Direction.EAST);
        assertEquals("""
                .@...
                .....
                .S...
                ...S.
                .....""", toString(game));
    }

    private Game loadGameFromMap(String map, ActorDirector actorDirector) {
        return getMapLoader(level -> new ReaderInputStream(new StringReader(map)), actorDirector).loadMap(1);
    }

    protected abstract MapLoader getMapLoader(LevelProvider levelProvider, ActorDirector actorDirector);

    private Game loadGameFromMap(String map) {
        return getMapLoader(level -> new ReaderInputStream(new StringReader(map))).loadMap(1);
    }

    protected abstract MapLoader getMapLoader(LevelProvider levelProvider);

    private String toString(Game game) {
        String result = "";
        for (int y = 0; y < game.height(); y++) {
            for (int x = 0; x < game.width(); x++) {
                result += ConsoleUI.tilesToChars.get(game.getDrawable(x, y));
            }
            if (y != game.height() - 1) {
                result += "\n";
            }
        }
        return result;
    }

}