package adventuregame.logic.functional;

import adventuregame.logic.Direction;
import adventuregame.logic.Game;
import adventuregame.logic.GameMapTest;
import adventuregame.logic.TileType;
import org.junit.jupiter.api.Test;
import org.postgresql.util.ReaderInputStream;

import java.io.StringReader;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkeletonTest {

    @Test
    public void should() {
        ImmutableRandomDirector director = new ImmutableRandomDirector() {
            @Override
            public Direction getRandomDirection() {
                return Direction.SOUTH;
            }
        };
        Skeleton skeleton = new Skeleton(new Location(0, 0), director, 10);
        TileType[][] cells = new TileType[][]{{TileType.FLOOR, TileType.FLOOR, TileType.FLOOR}};
        GameMap gameMap = new GameMap(1, 3, cells, new Player(new Location(0, 2)), List.of(skeleton));

        Skeleton result = skeleton.move(gameMap);

        assertEquals(new Location(0, 1), result.getLocation());

    }


}
