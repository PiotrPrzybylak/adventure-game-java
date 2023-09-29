package adventuregame.logic.functional;

import adventuregame.logic.Direction;
import adventuregame.logic.TileType;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActorTest {

    private static GameMap aMap(Player player) {
        TileType[][] cells = new TileType[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = TileType.FLOOR;
            }
        }
        return new GameMap(3, 3, cells, player, Collections.emptyList());
    }

    @Test
    void moveUpdatesCells() {
        GameMap gameMap = aMap(new Player(new Location(1, 1)));

        assertEquals(TileType.PLAYER, gameMap.getDrawable(1, 1));
        assertEquals(TileType.FLOOR, gameMap.getDrawable(2, 1));

        GameMap result = gameMap.movePlayer(Direction.EAST);

        assertEquals(TileType.FLOOR, result.getDrawable(1, 1));
        assertEquals(TileType.PLAYER, result.getDrawable(2, 1));
    }

//    @Test
//    void cannotMoveIntoWall() {
//        gameMap.getCell(2, 1).setType(CellType.WALL);
//        Player player = new Player(gameMap.getCell(1, 1));
//        player.move(1, 0, Collections.emptyList());
//
//        assertEquals(1, player.getX());
//        assertEquals(1, player.getY());
//    }
//
//    @Test
//    void cannotMoveOutOfMap() {
//        Player player = new Player(gameMap.getCell(2, 1));
//        player.move(1, 0, Collections.emptyList());
//
//        assertEquals(2, player.getX());
//        assertEquals(1, player.getY());
//    }
//
//    @Test
//    void cannotMoveIntoAnotherActor() {
//        Player player = new Player(gameMap.getCell(1, 1));
//        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1));
//        player.move(1, 0, List.of(skeleton));
//
//        assertEquals(1, player.getX());
//        assertEquals(1, player.getY());
//        assertEquals(2, skeleton.getX());
//        assertEquals(1, skeleton.getY());
////        assertEquals(skeleton, gameMap.getCell(2, 1).getActor());
//    }
}