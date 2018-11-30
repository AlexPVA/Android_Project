package csc207phase2.gamecentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class StepSaverTest {

    private StepSaver stepSaver = new StepSaver(3);

    @Test
    public void recordMove() {
        stepSaver.recordMove(1, 2, 3, 4);
        Integer[] integers = new Integer[] {1, 2, 3, 4};
        Integer[] stepSaverList = stepSaver.pop();
        assertArrayEquals(stepSaverList, integers);
    }

    @Test
    public void undo() {
        stepSaver.recordMove(1, 2, 3, 4);
        stepSaver.undo();
        assertEquals(stepSaver.size(), 0);
    }

    @Test
    public void recordMove_moreMoves() {
        stepSaver.recordMove(1, 0, 0, 0);
        stepSaver.recordMove(2, 0, 0, 0);
        stepSaver.recordMove(3, 0, 0, 0);
        stepSaver.recordMove(4, 0, 0, 0);
        Integer[] integers = new Integer[] {4,0,0,0};
        Integer[] pos = stepSaver.undo();
        assertEquals(pos[0], integers[0]);
    }
}