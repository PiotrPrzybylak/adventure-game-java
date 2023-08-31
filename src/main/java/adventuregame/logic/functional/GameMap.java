package adventuregame.logic.functional;

import adventuregame.logic.Direction;
import adventuregame.logic.Game;
import adventuregame.logic.TileType;

import java.util.List;
import java.util.Optional;

class GameMap implements Game {

    private final StaticMap staticMap;

    private final Player player;

    private final Monsters monsters;

    GameMap(int width, int height, TileType[][] cells, Player player, List<Skeleton> monsters) {
        this(new StaticMap(width, height, cells), player, new Monsters(monsters));
    }

    GameMap(StaticMap staticMap, Player player, Monsters monsters) {
        this.staticMap = staticMap;
        this.player = player;
        this.monsters = monsters;
    }

    @Override
    public int width() {
        return staticMap.width();
    }

    @Override
    public int height() {
        return staticMap.height();
    }

    @Override
    public TileType getDrawable(int x, int y) {
        Location location = new Location(x, y);
        return getPlayerAt(location)
                .or(() -> monsters.getMonsterAtLocation(location))
                .map(Actor::getTileType)
                .orElse(staticMap.getDrawable(x, y));
    }

    @Override
    public GameMap movePlayer(Direction direction) {
        return staticMap.getNeighbor(player.getLocation(), direction).map(l -> {
            if (canEnter(l)) {
                return updatePlayer(player.move(l));
            }
            return fight(l);
            // Should monsters move when player is trying to leave the map?
        }).map(GameMap::moveMonsters).orElse(this);
    }

    private GameMap fight(Location location) {
        if (isAMonsterHere(location)) {
            return updatePlayer(player.fight()).updateMonsters(monsters.fight(location));
        }
        return this;
    }

    private GameMap updateMonsters(Monsters monsters) {
        return new GameMap(staticMap, player, monsters);
    }

    private GameMap updatePlayer(Player player) {
        return new GameMap(staticMap, player, monsters);
    }

    @Override
    public int getPlayerHealth() {
        return player.getHealth();
    }

    private GameMap moveMonsters() {
        GameMap gameMap = this;
        for (int i = 0; i < monsters.size(); i++) {
            gameMap = gameMap.moveMonster(i);
        }

        return gameMap;
    }

    private GameMap moveMonster(int i) {
        return updateMonsters(monsters.moveMonster(i, this));
    }


    Optional<Location> getNeighbor(Location location, Direction direction) {
        return staticMap.getNeighbor(location, direction);
    }

    private boolean isPlayerHere(Location location) {
        return player.isInPlace(location);
    }

    boolean canEnter(Location location) {
        return staticMap.canEnter(location) && isUnoccupied(location);
    }

    boolean isUnoccupied(Location location) {
        return !isPlayerHere(location) && !monsters.isAMonsterHere(location);
    }

    boolean isAMonsterHere(Location location) {
        return monsters.isAMonsterHere(location);
    }

    Optional<Actor<?>> getPlayerAt(Location location) {
        return isPlayerHere(location) ? Optional.of(player) : Optional.empty();
    }
}
