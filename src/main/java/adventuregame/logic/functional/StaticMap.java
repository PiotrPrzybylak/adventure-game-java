package adventuregame.logic.functional;

import adventuregame.logic.Direction;
import adventuregame.logic.DrawableMap;
import adventuregame.logic.TileType;

import java.util.Optional;

record StaticMap(int width, int height, TileType[][] cells) implements DrawableMap {
    StaticMap(int width, int height, TileType[][] cells) {
        this.width = width;
        this.height = height;
        this.cells = cells.clone();
    }

    @Override
    public TileType getDrawable(int x, int y) {
        return getCell(new Location(x, y));
    }

    private TileType getCell(Location location) {
        if (isOutOfRange(location)) {
            throw new RuntimeException("x: %d y: %d is out of range! Map size: %d x %d".formatted(location.x(), location.y(), width, height));
        }
        return cells[location.x()][location.y()];
    }

    private boolean isOutOfRange(Location location) {
        return location.x() < 0 || location.x() >= width || location.y() < 0 || location.y() >= height;
    }

    Optional<Location> getNeighbor(Location location, Direction direction) {
        Location newLocation = location.move(direction);
        if (isOutOfRange(newLocation)) {
            return Optional.empty();
        }
        return Optional.of(newLocation);
    }

    boolean canEnter(Location location) {
        return getCell(location) == TileType.FLOOR;
    }
}