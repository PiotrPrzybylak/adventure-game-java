package adventuregame.logic.functional;

import adventuregame.logic.Direction;
import adventuregame.logic.TileType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SkeletonTest {

    @Test
    public void shouldGoSouth() {
        ImmutableRandomDirector director = mock(ImmutableRandomDirector.class);
        when(director.getRandomDirection()).thenReturn(Direction.SOUTH);
        Skeleton skeleton = new Skeleton(new Location(0, 0), director, 10);
        TileType[][] cells = new TileType[][]{{TileType.FLOOR, TileType.FLOOR, TileType.FLOOR}};
        GameMap gameMap = new GameMap(1, 3, cells, new Player(new Location(0, 2)), List.of(skeleton));

        Skeleton result = skeleton.move(gameMap);

        assertEquals(new Location(0, 1), result.getLocation());

    }

    @Test
    public void shouldNotStickToTheWall() {
        ImmutableRandomDirector director = mock(ImmutableRandomDirector.class);
        ImmutableRandomDirector director2 = mock(ImmutableRandomDirector.class);
        when(director.getRandomDirection()).thenReturn(Direction.NORTH);
        when(director.next()).thenReturn(director2);
        when(director2.getRandomDirection()).thenReturn(Direction.SOUTH);

        Skeleton skeleton = new Skeleton(new Location(0, 1), director, 10);
        TileType[][] cells = new TileType[][]{{TileType.WALL, TileType.FLOOR, TileType.FLOOR}};
        GameMap gameMap = new GameMap(1, 4, cells, new Player(new Location(0, 3)), List.of(skeleton));

        Skeleton result = skeleton.move(gameMap);

        assertEquals(new Location(0, 1), result.getLocation());

        result = result.move(gameMap);

        assertEquals(new Location(0, 2), result.getLocation());

    }


}
