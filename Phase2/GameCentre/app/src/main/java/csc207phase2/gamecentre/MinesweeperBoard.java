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
    private int NUM_ROWS;

    /**
     * The number of rows.
     */
    private int NUM_COLS;

    /**
     * The number of bombs.
     */
    private int NUM_BOMBS;

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
            if (tiles[i][j] == null || tiles[i][j].getId() != MinesweeperTile.BOMB_ID) {
                tiles[i][j] = new MinesweeperTile(MinesweeperTile.BOMB_ID);
                NUM_BOMBS -= 1;
            }
        }
        //Add values that correspond to the bombs around it
        setNums();
    }

    void tapTile(int x, int y){
        getTile(x, y).setTapped(true);
        setChanged();
        notifyObservers();
    }

    /**
     * Goes through each tile, checks the number of bombs around it,
     * sets the tile number to the number of bombs
     */
    private void setNums() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if(tiles[i][j] == null || tiles[i][j].getId() != MinesweeperTile.BOMB_ID) {
                    tiles[i][j] = new MinesweeperTile(countBombs(i, j)); //background id???
                }
            }
        }
    }

    /**
     * Counts the number of bombs around a given tile
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the number of bombs around the given tile
     */
    private int countBombs(int x, int y){
        int count = 0;
        for(int i = Math.max(0, x - 1); i < Math.min(x + 2, NUM_ROWS); i ++){
            for(int j = Math.max(0, y - 1); j < Math.min(y + 2, NUM_ROWS); j ++){
                if(tiles[i][j] != null && tiles[i][j].getId() == MinesweeperTile.BOMB_ID){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Set the number of Rows
     * @param i the number of rows
     */
    private void setNumRows(int i) {NUM_ROWS = i;}

    /**
     * Set the number of Cols
     * @param i the number of columbs
     */
    private void setNumCols(int i) {NUM_COLS = i;}

    /**
     * Set the number of bombs
     * @param i the number of bombs
     */
    private void setNumBombs(int i) {NUM_BOMBS = i;}

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