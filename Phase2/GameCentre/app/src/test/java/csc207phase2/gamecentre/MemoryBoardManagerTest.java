package csc207phase2.gamecentre;

import org.junit.Test;
import static org.junit.Assert.*;

public class MemoryBoardManagerTest {

    private MemoryBoardManager memoryBoardManager = new MemoryBoardManager(4, 4);

    @Test
    public void getFirstTile() {
        assertNull(memoryBoardManager.getFirstTile());
    }

    @Test
    public void getSecondTile() {
        assertNull(memoryBoardManager.getSecondTile());
    }

    @Test
    public void setFirstTile() {
        MemoryTile tile = new MemoryTile(0);
        memoryBoardManager.setFirstTile(tile);
        assertEquals(memoryBoardManager.getFirstTile(), tile);
    }

    @Test
    public void setSecondTile() {
        MemoryTile tile = new MemoryTile(0);
        memoryBoardManager.setSecondTile(tile);
        assertEquals(memoryBoardManager.getSecondTile(), tile);
    }

    @Test
    public void getStoredPosition() {
        assertEquals(memoryBoardManager.getStoredPosition(), 0);
    }

    @Test
    public void puzzleSolved() {
        assertFalse(memoryBoardManager.puzzleSolved());
    }

    @Test
    public void isValidTap() {
        assertTrue(memoryBoardManager.isValidTap(0));
        memoryBoardManager.flipTile1(0);
        assertFalse(memoryBoardManager.isValidTap(0));
    }

    @Test
    public void flipTile1() {
        memoryBoardManager.flipTile1(0);
        assertTrue(memoryBoardManager.getFirstTile().getFlipped());
    }

    @Test
    public void flipTile2() {
        memoryBoardManager.flipTile2(1);
        assertTrue(memoryBoardManager.getSecondTile().getFlipped());
    }

    @Test
    public void getAccountName() {
        assertNull(memoryBoardManager.getAccountName());
    }

    @Test
    public void touchMove() {
        memoryBoardManager.touchMove(0);
        assertTrue(memoryBoardManager.getFirstTile().getFlipped());

    }

    @Test
    public void getBoard() {
        assertNotNull(memoryBoardManager.getBoard());
    }

    @Test
    public void undo() {
        memoryBoardManager.touchMove(0);
        memoryBoardManager.undo();
        assertNull(memoryBoardManager.getFirstTile());
    }
}