package csc207phase2.gamecentre;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.*;

public class SlidingTilesManagerUnitTest {

    @Test
    public void puzzleSolved_isCorrect() {
        SlidingTilesManager m = new SlidingTilesManager(5,5);
        assertFalse(m.puzzleSolved());
    }
    @Test
    public void isValidTapTrue_isCorrect() {
        SlidingTilesManager m = new SlidingTilesManager(3,3);
        boolean equal;
        assertEquals(m.isValidTap(1), true);
    }
    @Test
    public void isValidTapFalse_isCorrect() {
        SlidingTilesManager m = new SlidingTilesManager(3,3);
        m.touchMove(1);
        assertEquals(m.isValidTap(1), true);
    }
 }




