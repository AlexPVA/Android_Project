package csc207phase2.gamecentre;

import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * The sliding tiles board.
 */
public class MinesweeperBoard extends Observable implements Serializable, Iterable<MinesweeperTile> {

    /**
     * The number of rows.
     */
    private static int NUM_ROWS;

    /**
     * The number of rows.
     */
    private static int NUM_COLS;

    /**
     * The number of bombs.
     */
    private static int NUM_BOMBS;

    /**
     * The tiles on the board in row-major order.
     */
    private MinesweeperTile[][] tiles;

    /**
     * A new board
     */
    MinesweeperBoard(int rows, int cols) {
        setNumRows(rows);
        setNumCols(cols);

        //set number of bombs
        if (cols == 9) {
            setNumBombs(10);
        } else if (cols == 16) {
            setNumBombs(40);
        } else {
            setNumBombs(60);
        }

        tiles = new MinesweeperTile[NUM_ROWS][NUM_COLS];

        //populate the board with bombs
        while (NUM_BOMBS != 0) {
            Random random = new Random();
            int i = random.nextInt(NUM_ROWS);
            int j = random.nextInt(NUM_COLS);
            //find the background id
            if (tiles[i][j].getId() != 9) {
                tiles[i][j] = new MinesweeperTile(9, findBackground(i, j)); //find the background id given the diff board sizes
                NUM_BOMBS -= 1;
            }
        }
        //Add values that correspond to the bombs around it
        setNums();
    }

    /**
     * Goes through each tile, checks the number of bombs around it,
     * sets the tile number to the number of bombs
     */
    private void setNums() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                int count = 0;
                if (tiles[i-1][j].getId() == 9) {
                    count++;

                } else if (tiles[i-1][j+1].getId() == 9) {
                    count++;

                } else if (tiles[i-1][j-1].getId() == 9) {
                    count++;

                } else if (tiles[i][j-1].getId() == 9) {
                    count++;

                } else if (tiles[i][j+1].getId() == 9) {
                    count++;

                } else if (tiles[i+1][j].getId() == 9) {
                    count++;

                } else if (tiles[i+1][j-1].getId() == 9) {
                    count++;

                } else if (tiles[i+1][j+1].getId() == 9) {
                    count++;
                }
                tiles[i][j] = new MinesweeperTile(count, findBackground(i, j)); //background id???
            }
        }
    }

    /**
     * Set the number of Rows
     * @param i the number of rows
     */
    private static void setNumRows(int i) {NUM_ROWS = i;}

    /**
     * Set the number of Cols
     * @param i the number of columbs
     */
    private static void setNumCols(int i) {NUM_COLS = i;}

    /**
     * Set the number of bombs
     * @param i the number of bombs
     */
    private static void setNumBombs(int i) {NUM_BOMBS = i;}

    /**
     * find the background id of a tile given the i,j positions
     */
    private int findBackground(int row, int col) {
        int background = 0;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (i == row && j == col) {
                    return background;
                }
                background++;
            }
        }
        return background;
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    public int numBombs() {
        return NUM_BOMBS;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    MinesweeperTile getTile(int row, int col) {
        return tiles[row][col];
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    @Override
    public Iterator<MinesweeperTile> iterator() {
        return new BoardIterator();
    }

    /**
     * Iterates through the Tiles in the Board in row-major order.
     */
    private class BoardIterator implements Iterator<MinesweeperTile> {

        /**
         * The current location of the next Tile in the board
         */
        private int rowIndex = 0, colIndex = 0;

        @Override
        public boolean hasNext() {
            return rowIndex < NUM_ROWS - 1 || colIndex < NUM_COLS - 1;
        }

        @Override
        public MinesweeperTile next() {
            MinesweeperTile result = tiles[rowIndex][colIndex];

            if (colIndex == NUM_COLS - 1) {
                colIndex = 0;
                rowIndex++;
            } else {
                colIndex++;
            }

            return result;
        }
    }

    int getNumRows(){
        return NUM_ROWS;
    }

    int getNumCols(){
        return NUM_COLS;
    }

}