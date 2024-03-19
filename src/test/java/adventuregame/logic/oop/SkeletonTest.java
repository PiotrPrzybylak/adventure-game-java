package adventuregame.logic.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SkeletonTest {

    @Test
    void fights_classic() {

        Cell cell = new Cell(null, 0, 0, null);
        Skeleton skeleton = new Skeleton(cell);

        assertEquals(10, skeleton.getHealth());
        assertSame(skeleton, cell.getActor());

        skeleton.fight();

        assertEquals(5, skeleton.getHealth());

        skeleton.fight();

        assertEquals(0, skeleton.getHealth());
        assertNull(cell.getActor());
    }

    @Test
    void fights_mockist() {

        Cell cell = mock(Cell.class);
        Skeleton skeleton = new Skeleton(cell);

        assertEquals(10, skeleton.getHealth());

        skeleton.fight();

        assertEquals(5, skeleton.getHealth());

        skeleton.fight();

        assertEquals(0, skeleton.getHealth());
        verify(cell).setActor(skeleton);
        verify(cell).setActor(null);
    }



}