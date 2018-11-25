package csc207phase2.gamecentre;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MemoryBoardTest {

    //Do we need to test the toString method?
    private ArrayList<MemoryTile> memoryTiles = new ArrayList<>();
    private MemoryTile tile = new MemoryTile(10);

    @Test
    public void getNumRows() {
        memoryTiles.add(tile);
        MemoryBoard memoryBoard = new MemoryBoard(1, 1, memoryTiles);
        assertEquals(memoryBoard.getNumRows(), 1);
    }

    @Test
    public void getNumCols() {
        memoryTiles.add(tile);
        MemoryBoard memoryBoard = new MemoryBoard(1, 1, memoryTiles);
        assertEquals(memoryBoard.getNumCols(), 1);
    }

    @Test
    public void getTile() {
        memoryTiles.add(tile);
        MemoryBoard memoryBoard = new MemoryBoard(1, 1, memoryTiles);
        assertEquals(memoryBoard.getTile(0, 0), tile);
    }

    @Test
    public void flipTile() {
        memoryTiles.add(tile);
        MemoryBoard memoryBoard = new MemoryBoard(1, 1, memoryTiles);
        memoryBoard.flipTile(0);
        tile.flipPicture();
        assertEquals(memoryBoard.getTile(0, 0).getBackground(), tile.getBackground());
        memoryBoard.flipTile(0);
        tile.flipBlank();
        assertEquals(memoryBoard.getTile(0, 0).getBackground(), tile.getBackground());
    }

}