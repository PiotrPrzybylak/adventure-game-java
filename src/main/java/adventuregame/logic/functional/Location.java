package adventuregame.logic.functional;

import adventuregame.logic.Direction;

record Location(int x, int y) {

    boolean isSamePlace(Location location) {
        return x == location.x && y == location.y;
    }

    Location move(Direction direction) {
        return new Location(x + direction.dx, y + direction.dy);
    }
}
