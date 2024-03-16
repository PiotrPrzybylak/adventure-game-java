package adventuregame.logic.oop;


import adventuregame.logic.Direction;
import adventuregame.logic.TileType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerTest {

    @Test
    void doesNotMove_classic() {
        Player player = new Player(new Cell(new GameMap(1, 1, TileType.FLOOR, 1, null), 0, 0, null));

        player.move(Direction.EAST);

        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
    }

    @Test
    void doesNotMove_mockist() {
        Player player = new Player(mock(Cell.class));

        player.move(Direction.EAST);

        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
    }

    @Test
    void moves_classic() {
        Player player = new Player(new Cell(new GameMap(2, 1, TileType.FLOOR, 1, null), 0, 0, null));

        player.move(Direction.EAST);

        assertEquals(1, player.getX());
        assertEquals(0, player.getY());
    }

    @Test
    void moves_mockist() {
        Cell cell = mock(Cell.class);
        Cell cell2 = mock(Cell.class);
        when(cell.getNeighbor(1, 0)).thenReturn(cell2);
        when(cell2.getX()).thenReturn(1);
        when(cell2.canEnter()).thenReturn(true);
        Player player = new Player(cell);

        player.move(Direction.EAST);

        assertEquals(1, player.getX());
        assertEquals(0, player.getY());
    }


    @Test
    void fights_classic() {
        GameMap gameMap = new GameMap(2, 1, TileType.FLOOR, 1, null);
        Cell skeletonCell = gameMap.getCell(1, 0);
        skeletonCell.setActor(new Skeleton(skeletonCell));
        Player player = new Player(new Cell(gameMap, 0, 0, null));

        player.move(Direction.EAST);

        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
        assertEquals(8, player.getHealth());
    }

    @Test
    void fights_mockist() {
        Cell cell = mock(Cell.class);
        Cell cell2 = mock(Cell.class);
        when(cell.getNeighbor(1, 0)).thenReturn(cell2);
        when(cell2.getX()).thenReturn(1);
        when(cell2.canEnter()).thenReturn(false);
        when(cell2.getActor()).thenReturn(mock(Actor.class));
        Player player = new Player(cell);

        player.move(Direction.EAST);

        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
        assertEquals(8, player.getHealth());

    }



}