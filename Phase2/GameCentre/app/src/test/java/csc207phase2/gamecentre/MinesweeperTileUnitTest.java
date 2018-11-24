package csc207phase2.gamecentre;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Minesweeper Tile Unit Test
 */
public class MinesweeperTileUnitTest {
    @Test
    public void isTappedFalse_isCorrect() {
        MinesweeperTile tile = new MinesweeperTile(1);
        assertEquals(tile.isTapped(), false);
    }
    @Test
    public void isTappedTrue_isCorrect() {
        MinesweeperTile tile = new MinesweeperTile(1);
        tile.setTapped(true);
        assertEquals(tile.isTapped(), true);
    }
    @Test
    public void getId_isCorrect() {
        MinesweeperTile tile = new MinesweeperTile(1);
        assertEquals(tile.getId(), 1);
    }
    @Test
    public void setId_isCorrect() {
        MinesweeperTile tile = new MinesweeperTile(1);
        tile.setId(2);
        assertEquals(tile.getId(), 2);
    }
}