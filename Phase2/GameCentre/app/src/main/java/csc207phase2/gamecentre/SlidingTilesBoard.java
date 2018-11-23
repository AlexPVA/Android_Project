package csc207phase2.gamecentre;

import java.util.Observable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import android.support.annotation.NonNull;

/**
 * The sliding tiles board.
 */
public class SlidingTilesBoard extends Board implements Serializable, Iterable<SlidingTilesTile> {

    /**
     * The number of rows.
     */
    private int numRows;

    /**
     * The number of rows.
     */
    private int numCols;

    /**
     * The tiles on the board in row-major order.
     */
    private SlidingTilesTile[][] tiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == numRows * numCols
     *
     * @param tiles the tiles for the board
     */
    SlidingTilesBoard(int rows, int cols, List<SlidingTilesTile> tiles) {
        setNumRows(rows);
        setNumCols(cols);
        this.tiles = new SlidingTilesTile[numRows][numCols];
        Iterator<SlidingTilesTile> iter = tiles.iterator();

        for (int row = 0; row != numRows; row++) {
            for (int col = 0; col != numCols; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Set the number of Rows
     *
     * @param i the number of rows
     */
    private void setNumRows(int i) {
        numRows = i;
    }

    /**
     * Set the number of Cols
     *
     * @param i the number of columbs
     */
    private void setNumCols(int i) {
        numCols = i;
    }

    /**
     * Return the number of rows on the board.
     *
     * @return the number of rows on the board
     */
    public int getNumRows(){
        return numRows;
    }

    /**
     * Return the number of columns on the board.
     *
     * @return the number of columns on the board
     */
    public int getNumCols(){
        return numCols;
    }


    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    SlidingTilesTile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {

        SlidingTilesTile temp = getTile(row1, col1);
        tiles[row1][col1] = getTile(row2, col2);
        tiles[row2][col2] = temp;

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "SlidingTilesBoard{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    @NonNull
    @Override
    public Iterator<SlidingTilesTile> iterator() {
        return new BoardIterator();
    }

    /**
     * Iterates through the Tiles in the SlidingTilesBoard in row-major order.
     */
    private class BoardIterator implements Iterator<SlidingTilesTile> {

        /**
         * The current location of the next SlidingTilesTile in the board
         */
        private int rowIndex = 0, colIndex = 0;

        @Override
        public boolean hasNext() {
            return rowIndex < numRows - 1 || colIndex < numCols - 1;
        }

        @Override
        public SlidingTilesTile next() {
            SlidingTilesTile result = tiles[rowIndex][colIndex];

            if (colIndex == numCols - 1) {
                colIndex = 0;
                rowIndex++;
            } else {
                colIndex++;
            }

            return result;
        }
    }
}