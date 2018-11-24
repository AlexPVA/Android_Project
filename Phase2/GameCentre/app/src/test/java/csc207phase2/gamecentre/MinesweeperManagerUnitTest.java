package csc207phase2.gamecentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinesweeperManagerUnitTest {
    @Test
    public void puzzleSolved_isCorrect() {
        MinesweeperManager m = new MinesweeperMoard(9, 9);
        m.touchMove(1);
        assertEquals(m.puzzleSolved(), false);
    }
    @Test
    public void isValidTapTrue_isCorrect() {
        MinesweeperManager m = new MinesweeperMoard(9, 9);
        assertEquals(m.isValidTap(1), True);
    }
    @Test
    public void isValidTapFalse_isCorrect() {
        MinesweeperManager m = new MinesweeperMoard(9, 9);
        m.touchMove(1);
        assertEquals(m.isValidTap(1), True);
    }
}