package csc207phase2.gamecentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class StepSaverTest {

    private StepSaver stepSaver = new StepSaver();

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
}