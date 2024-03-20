package adventuregame.logic.oop;

import adventuregame.logic.TileType;

interface Actor {

    void move();

    TileType getTileType();

    void fight();

}
