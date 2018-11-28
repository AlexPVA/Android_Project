package csc207phase2.gamecentre;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SlidingTilesBoardUnitTest {
    private ArrayList<SlidingTilesTile> tiles = new ArrayList<>();
    final private int row = 4;
    final private int col = 4;

    @Test
    public void getNumRows_isCorrect() {
        final int numTiles = row * col - 1;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new SlidingTilesTile(tileNum));
        }
        tiles.add(new SlidingTilesTile(24));
        SlidingTilesBoard board = new SlidingTilesBoard(row, col, tiles);
        assertEquals(board.getNumRows(), 4);
    }
    @Test
    public void getNumCols_isCorrect() {
        final int numTiles = row * col - 1;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new SlidingTilesTile(tileNum));
        }
        tiles.add(new SlidingTilesTile(24));
        SlidingTilesBoard board = new SlidingTilesBoard(row, col, tiles);
        assertEquals(board.getNumCols(), 4);
    }
    @Test
    public void getTile_isCorrect() {
        final int numTiles = row * col - 1;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new SlidingTilesTile(tileNum));
        }
        tiles.add(new SlidingTilesTile(24));
        SlidingTilesBoard board = new SlidingTilesBoard(row, col, tiles);
        assertEquals(board.getTile(0, 0), tiles.get(0));
    }
    @Test
    public void swapTile_isCorrect() {
        final int numTiles = row * col - 1;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new SlidingTilesTile(tileNum));
        }
        tiles.add(new SlidingTilesTile(24));
        SlidingTilesBoard board = new SlidingTilesBoard(4, 4, tiles);
        SlidingTilesTile t1 = board.getTile(1, 1);
        SlidingTilesTile t2 = board.getTile(0, 0);
        board.swapTiles(0, 0, 1, 1);
        assertEquals(board.getTile(0, 0), t1);
        assertEquals(board.getTile(1, 1), t2);
    }
}