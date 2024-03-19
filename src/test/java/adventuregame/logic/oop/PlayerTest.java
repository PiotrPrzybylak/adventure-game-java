package adventuregame.logic.oop;


import adventuregame.logic.Direction;
import adventuregame.logic.TileType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
        GameMap gameMap = new GameMap(2, 1, TileType.FLOOR, 1, null);
        Player player = new Player(new Cell(gameMap, 0, 0, null));

        player.move(Direction.EAST);

        assertEquals(1, player.getX());
        assertEquals(0, player.getY());
        assertEquals(TileType.FLOOR, gameMap.getCell(0, 0).getTileType());
        assertEquals(TileType.PLAYER, gameMap.getCell(1, 0).getTileType());
    }

    @Test
    void moves_mockist() {
        Cell cell1 = mock(Cell.class);
        Cell cell2 = mock(Cell.class);
        when(cell1.getNeighbor(1, 0)).thenReturn(cell2);
        when(cell2.getX()).thenReturn(1);
        when(cell2.canEnter()).thenReturn(true);
        Player player = new Player(cell1);

        player.move(Direction.EAST);

        assertEquals(1, player.getX());
        assertEquals(0, player.getY());
        verify(cell1).removePlayer();
        verify(cell2).setPlayer(player);
    }


    @Test
    void fights_classic() {
        GameMap gameMap = new GameMap(2, 1, TileType.FLOOR, 1, null);
        Cell skeletonCell = gameMap.getCell(1, 0);
        Skeleton skeleton = new Skeleton(skeletonCell);
        skeletonCell.setActor(skeleton);
        Player player = new Player(new Cell(gameMap, 0, 0, null));

        player.move(Direction.EAST);

        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
        assertEquals(8, player.getHealth());
        assertEquals(5, skeleton.getHealth());
    }

    @Test
    void fights_mockist() {
        Cell cell = mock(Cell.class);
        Cell cell2 = mock(Cell.class);
        when(cell.getNeighbor(1, 0)).thenReturn(cell2);
        when(cell2.getX()).thenReturn(1);
        when(cell2.canEnter()).thenReturn(false);
        Actor monster = mock(Actor.class);
        when(cell2.getActor()).thenReturn(monster);
        Player player = new Player(cell);

        player.move(Direction.EAST);

        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
        assertEquals(8, player.getHealth());
        verify(monster).fight();
    }


}