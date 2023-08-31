package adventuregame.logic.oop;

import adventuregame.logic.TileType;

class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public TileType getTileType() {
        return TileType.PLAYER;
    }

    public String getName() {
        return "Plajer";
    }

}
