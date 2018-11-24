package csc207phase2.gamecentre;


import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Observable;

/**
 * Holds common features for all boards.
 */
abstract class Board<T extends Tile> extends Observable implements Serializable, Iterable<T>{

    /**
     * Return the number of rows on the board.
     *
     * @return the number of rows on the board
     */
    abstract int getNumRows();

    /**
     * Return the number of columns on the board.
     *
     * @return the number of columns on the board
     */
    abstract int getNumCols();

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    abstract T getTile(int row, int col);

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new Board<T>.BoardIterator();
    }

    /**
     * Iterates through the Tiles in row-major order.
     */
    private class BoardIterator implements Iterator<T> {

        /**
         * The current location of the next Tile in the board
         */
        private int rowIndex = 0, colIndex = 0;

        @Override
        public boolean hasNext() {
            return rowIndex < getNumRows() - 1 || colIndex < getNumCols() - 1;
        }

        @Override
        public T next() {
            T result = getTile(rowIndex, colIndex);

            if (colIndex == getNumCols() - 1) {
                colIndex = 0;
                rowIndex++;
            } else {
                colIndex++;
            }

            return result;
        }
    }



}
