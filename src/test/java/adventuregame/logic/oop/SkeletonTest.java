package adventuregame.logic.oop;

import adventuregame.logic.TileType;
import org.junit.jupiter.api.Test;

import static adventuregame.logic.Direction.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SkeletonTest {

    @Test
    void moves_classic() {
        GameMap gameMap = new GameMap(2, 1, TileType.FLOOR, 1, null);
        RandomDirector director = () -> WEST;
        Skeleton skeleton = new Skeleton(gameMap.getCell(1, 0), director);

        skeleton.move();

        assertEquals(skeleton, gameMap.getCell(0, 0).getActor());
        assertNull(gameMap.getCell(1,0).getActor());
    }

    @Test
    void moves_mockist() {

        Cell westernCell= mock(Cell.class);
        when(westernCell.canEnter()).thenReturn(true);
        Cell cell = mock(Cell.class);
        when(cell.getNeighbor(-1, 0)).thenReturn(westernCell);
        RandomDirector director = mock(RandomDirector.class);
        when(director.getRandomDirection()).thenReturn(WEST);
        Skeleton skeleton = new Skeleton(cell, director);

        skeleton.move();

        verify(westernCell).setActor(skeleton);
        verify(cell).setActor(null);
    }

    @Test
    void fights_classic() {

        Cell cell = new Cell(null, 0, 0, null);
        Skeleton skeleton = new Skeleton(cell, null);

        assertEquals(10, skeleton.getHealth());
        assertSame(skeleton, cell.getActor());

        skeleton.fight();

        assertEquals(5, skeleton.getHealth());

        skeleton.fight();

        assertEquals(0, skeleton.getHealth());
        assertNull(cell.getActor());
    }

    @Test
    void fights_mockist() {

        Cell cell = mock(Cell.class);
        Skeleton skeleton = new Skeleton(cell, null);

        assertEquals(10, skeleton.getHealth());

        skeleton.fight();

        assertEquals(5, skeleton.getHealth());

        skeleton.fight();

        assertEquals(0, skeleton.getHealth());
        verify(cell).setActor(skeleton);
        verify(cell).setActor(null);
    }


    @Test
    void attacks_classic() {

        GameMap gameMap = new GameMap(2, 1, TileType.FLOOR, 1, null);
        Player player = new Player(gameMap.getCell(0, 0));
        RandomDirector director = () -> WEST;
        Skeleton skeleton = new Skeleton(gameMap.getCell(1, 0), director);

        skeleton.move();

        assertEquals(8, player.getHealth());
        assertEquals(5, skeleton.getHealth());


    }

    @Test
    void attacks_mockist() {

        Cell cell = mock(Cell.class);
        Cell cellWithPlayer = mock(Cell.class);
        when(cell.getNeighbor(-1, 0)).thenReturn(cellWithPlayer);
        Player player = mock(Player.class);
        when(cellWithPlayer.getPlayer()).thenReturn(player);
        RandomDirector director = mock(RandomDirector.class);
        when(director.getRandomDirection()).thenReturn(WEST);
        Skeleton skeleton = new Skeleton(cell, director);

        skeleton.move();

        assertEquals(5, skeleton.getHealth());
        verify(player).fight();
    }


}