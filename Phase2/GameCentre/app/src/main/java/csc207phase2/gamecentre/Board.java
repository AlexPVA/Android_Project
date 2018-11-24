package csc207phase2.gamecentre;


import java.io.Serializable;
import java.util.Iterator;
import java.util.Observable;

abstract class Board<T extends Tile> extends Observable implements Serializable, Iterable<T>{

    abstract int getNumRows();

    abstract int getNumCols();

    abstract T getTile(int x, int y);

    public Iterator<T> iterator() {
        return new Board<T>.BoardIterator();
    }

    /**
     * Iterates through the Tiles in the MemoryBoard in row-major order.
     */
    private class BoardIterator implements Iterator<T> {

        /**
         * The current location of the next MemoryTile in the board
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
