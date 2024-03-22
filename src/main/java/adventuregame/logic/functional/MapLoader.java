package adventuregame.logic.functional;

import adventuregame.logic.ActorDirector;
import adventuregame.logic.Direction;
import adventuregame.logic.Game;
import adventuregame.logic.LevelProvider;
import adventuregame.logic.TileType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static adventuregame.logic.TileType.EMPTY;
import static adventuregame.logic.TileType.FLOOR;
import static adventuregame.logic.TileType.STAIRS;
import static adventuregame.logic.TileType.WALL;

public class MapLoader implements adventuregame.logic.MapLoader {

    private final LevelProvider levelProvider;
    private final ImmutableRandomDirectorFactory immutableRandomDirectorFactory;

    public MapLoader(LevelProvider levelProvider) {
        this.levelProvider = levelProvider;
        this.immutableRandomDirectorFactory = new ImmutableRandomDirectorFactory(null);
    }

    public MapLoader(LevelProvider levelProvider, ActorDirector actorDirector) {
        this.levelProvider = levelProvider;
        this.immutableRandomDirectorFactory = new ImmutableRandomDirectorFactory(actorDirector);
    }

    public Game loadMap(int level) {
        Scanner scanner = new Scanner(levelProvider.getLevel(level));
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        final TileType[][] cells = new TileType[width][height];
        List<Skeleton> monsters = new ArrayList<>();
        Player player = null;
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                TileType cellType;
                if (x < line.length()) {
                    switch (line.charAt(x)) {
                        case ' ' -> cellType = EMPTY;
                        case '#' -> cellType = WALL;
                        case '.' -> cellType = FLOOR;
                        case 's' -> {
                            cellType = FLOOR;
                            monsters.add(new Skeleton(new Location(x, y), immutableRandomDirectorFactory.create()));
                        }
                        case '@' -> {
                            cellType = FLOOR;
                            player = new Player(new Location(x, y));
                        }
                        case 'H' -> cellType = STAIRS;
                        default -> throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                } else {
                    cellType = EMPTY;
                }
                cells[x][y] = cellType;
            }
        }
        return new GameMap(width, height, cells, player, monsters);
    }

    private class ImmutableRandomDirectorFactory {
        private final ActorDirector actorDirector;

        public ImmutableRandomDirectorFactory(ActorDirector actorDirector) {
            this.actorDirector = actorDirector;
        }

        public ImmutableRandomDirector create() {
            if (actorDirector == null) {
                return new ImmutableRandomDirector();
            }
            return new ActorDirectorImmutableRandomDirector(actorDirector);
        }
    }

    private class ActorDirectorImmutableRandomDirector extends ImmutableRandomDirector {
        private final ActorDirector actorDirector;

        private final Direction direction;

        private ImmutableRandomDirector next;


        public ActorDirectorImmutableRandomDirector(ActorDirector actorDirector) {
            this.actorDirector = actorDirector;
            direction = actorDirector.getRandomDirection();
        }

        @Override
        public Direction getRandomDirection() {
            return direction;
        }

        @Override
        public ImmutableRandomDirector next() {
            if (next == null) {
                next = new ActorDirectorImmutableRandomDirector(actorDirector);
            }
            return next;
        }
    }
}
