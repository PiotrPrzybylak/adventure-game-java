package adventuregame.infrastructure.level;

import adventuregame.logic.LevelProvider;

import java.io.InputStream;

public class ClassPathLevelsProvider implements LevelProvider {
    @Override
    public InputStream getLevel(int level) {
        return getClass().getResourceAsStream("/map" + level + ".txt");
    }
}
