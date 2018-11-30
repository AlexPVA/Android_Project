package csc207phase2.gamecentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinesweeperManagerUnitTest {
    @Test
    public void puzzleSolved_isCorrect() {
        MinesweeperManager m = new MinesweeperManager(9, 9);
        m.touchMove(1);
        assertFalse(m.puzzleSolved());
    }
    @Test
    public void isValidTapTrue_isCorrect() {
        MinesweeperManager m = new MinesweeperManager(9, 9);
        assertTrue(m.isValidTap(1));
    }
    @Test
    public void isValidTapFalse_isCorrect() {
        MinesweeperManager m = new MinesweeperManager(9, 9);
        m.touchMove(1);
        assertFalse(m.isValidTap(1));
    }
    @Test
    public void setGameLost_isCorrect() {
        MinesweeperManager m = new MinesweeperManager(9, 9);
        m.setGameLost(true);
        assertTrue(m.getGameLost());
    }
    @Test
    public void touchMove_isCorrect() {
        MinesweeperManager m = new MinesweeperManager(9, 9);
        m.touchMove(0);
        assertTrue(m.getBoard().getTile(0,0).getId() != R.drawable.unclicked_tile);
    }
}