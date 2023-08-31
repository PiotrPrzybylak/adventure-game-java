package adventuregame.logic;

import java.io.InputStream;

public interface LevelProvider {
    InputStream getLevel(int level);
}
