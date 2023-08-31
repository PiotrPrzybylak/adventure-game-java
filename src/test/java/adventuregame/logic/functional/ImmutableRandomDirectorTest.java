package adventuregame.logic.functional;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImmutableRandomDirectorTest {

    @RepeatedTest(100)
    public void shouldBeImmutable() {
        ImmutableRandomDirector sut = new ImmutableRandomDirector();

        ImmutableRandomDirector next1 = sut.next();
        next1.next().next().next();
        ImmutableRandomDirector next2 = sut.next();

        assertEquals(next1.getRandomDirection(), next2.getRandomDirection());
    }

}