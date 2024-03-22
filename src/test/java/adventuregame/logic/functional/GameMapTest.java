package adventuregame.logic.functional;

import adventuregame.logic.ActorDirector;
import adventuregame.logic.LevelProvider;
import adventuregame.logic.MapLoader;

public class GameMapTest extends adventuregame.logic.GameMapTest {

    @Override
    protected MapLoader getMapLoader(LevelProvider levelProvider, ActorDirector actorDirector) {
        return new adventuregame.logic.functional.MapLoader(levelProvider, actorDirector);
    }

    @Override
    protected MapLoader getMapLoader(LevelProvider levelProvider) {
        return new adventuregame.logic.functional.MapLoader(levelProvider);
    }
}