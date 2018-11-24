package csc207phase2.gamecentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class SlidingTilesBoardUnitTest {
    List<SlidingTilesTile> tiles = new ArrayList<>();
    final int numTiles = row * col - 1;
    for (int tileNum = 0; tileNum != numTiles; tileNum++) {
        tiles.add(new SlidingTilesTile(tileNum));
    }
        tiles.add(new SlidingTilesTile(24));

    @Test
    public void getNumRows_isCorrect() {
        SlidingTilesBoard board = new SlidingTilesBoard(4, 4, tiles);
        assertEquals(board.getNumRows(), 4);
    }
    @Test
    public void getNumCols_isCorrect() {
        SlidingTilesBoard board = new SlidingTilesBoard(4, 4, tiles);
        assertEquals(board.getNumCols(), 4);
    }
    @Test
    public void getTile_isCorrect() {
        SlidingTilesBoard board = new SlidingTilesBoard(4, 4, tiles);
        assertEquals(board.getTile(0, 0), tiles[0]);
        //not sure if this is the correct way to check
    }
    @Test
    public void swapTile_isCorrect() {
        SlidingTilesBoard board = new SlidingTilesBoard(4, 4, tiles);
        SlidingTilesTile t1 = board.getTile(1, 1);
        SlidingTilesTile t2 = board.getTile(0, 0);
        board.swapTiles(0, 0, 1, 1);
        assertEquals(board.getTile(0, 0).equals(t1) && board.getTile(1, 1).equals(t2));
    }
}