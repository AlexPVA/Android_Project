package csc207phase2.gamecentre;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The Memory Game board
 */

public class MemoryBoard extends Board<MemoryTile>{

    /**
     * The number of rows.
     */
    private int numRows;

    /**
     * The number of rows.
     */
    private int numCols;

    /**
     * The MemoryTiles on the board.
     */
    private MemoryTile[][] memoryTiles;

    /**
     * A new board of MemoryTiles.
     * Precondition: len(memoryTiles) == numRows * numCols
     *
     * @param rows the number of rows.
     * @param cols the number of columns.
     * @param memoryTiles the memoryTiles for the board.
     */
    MemoryBoard(int rows, int cols, List<MemoryTile> memoryTiles) {
        setNumRows(rows);
        setNumCols(cols);
        this.memoryTiles = new MemoryTile[numRows][numCols];
        Iterator<MemoryTile> iter = memoryTiles.iterator();


        for (int row = 0; row != numRows; row++) {
            for (int col = 0; col != numCols; col++) {
                this.memoryTiles[row][col] = iter.next();
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
    MemoryTile getTile(int row, int col) {
        return memoryTiles[row][col];
    }

    /**
     * Flip the tile at (row, col).
     *
     * @param position the position of the tile to flip
     */
    void flipTile(int position) {
        int row = position / this.getNumRows();
        int col = position % this.getNumCols();

        if (memoryTiles[row][col].getFlipped()){
            memoryTiles[row][col].flipBlank();
            memoryTiles[row][col].setFlipped(false);
        }
        else {
        memoryTiles[row][col].flipPicture();
        memoryTiles[row][col].setFlipped(true);
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Returns the string representation of the Memory Game board.
     *
     * @return string representation of the Memory Game board
     */
    @Override
    public String toString() {
        return "MemoryBoard{" +
                "MemoryTiles=" + Arrays.toString(memoryTiles) +
                '}';
    }


}
