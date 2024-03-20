package adventuregame.logic.oop;

import adventuregame.logic.Direction;

import java.util.Random;

public class TrulyRandomDirector implements RandomDirector {

    private static final Random random = new Random();

    @Override
    public Direction getRandomDirection() {
        return randomMember(Direction.class);
    }

    public static <T extends Enum<T>> T randomMember(Class<T> enumType) {
        T[] constants = enumType.getEnumConstants();
        int i = random.nextInt(constants.length);
        return constants[i];
    }
}
