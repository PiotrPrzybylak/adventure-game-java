package adventuregame.logic.oop;

import adventuregame.logic.ActorDirector;
import adventuregame.logic.Game;
import adventuregame.logic.LevelProvider;
import adventuregame.logic.TileType;

import java.util.Scanner;

public class MapLoader implements adventuregame.logic.MapLoader {
    private final LevelProvider levelProvider;
    private final RandomDirector randomDirector;

    public MapLoader(LevelProvider levelProvider) {
        this(levelProvider, new TrulyRandomDirector()::getRandomDirection);
    }

    public MapLoader(LevelProvider levelProvider, ActorDirector actorDirector) {
        this.levelProvider = levelProvider;
        this.randomDirector = actorDirector::getRandomDirection;
    }

    public Game loadMap(int level) {

        Scanner scanner = new Scanner(levelProvider.getLevel(level));
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line
        int keysNumber = 2;

        GameMap map = new GameMap(width, height, TileType.EMPTY, level, this);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ' -> cell.setType(TileType.EMPTY);
                        case '#' -> cell.setType(TileType.WALL);
                        case 'H' -> cell.setType(TileType.STAIRS);
                        case '.' -> {
                            cell.setType(TileType.FLOOR);
//                            if (keysNumber > 0) {
//                                keysNumber--;
//                                cell.addKey();
//                            }
                        }
                        case 's' -> {
                            cell.setType(TileType.FLOOR);
                            new Skeleton(cell, randomDirector);
                        }
                        case '@' -> {
                            cell.setType(TileType.FLOOR);
                            map.setPlayer(new Player(cell));
                        }
                        default -> throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }

        }
        return map;
    }

}
