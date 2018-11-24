package csc207phase2.gamecentre;

import java.util.Arrays;
import java.util.Random;

/**
 * The Minesweeper tiles board.
 */
public class MinesweeperBoard extends Board<MinesweeperTile>{

    /**
     * The number of rows.
     */
    private int numRows;

    /**
     * The number of rows.
     */
    private int numCols;

    /**
     * The number of bombs.
     */
    private int numBombs;

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
        generateBoard(rows, cols);
    }

    /**
     * Generates A new board and notifies the observers
     *
     * @param rows the number of rows
     * @param cols the number of cols
     */
    void generateBoard(int rows, int cols){
        tiles = new MinesweeperTile[numRows][numCols];

        //set number of bombs
        if (cols == 9) {
            setNumBombs(10);
        } else if (cols == 16) {
            setNumBombs(40);
        } else {
            setNumBombs(60);
        }
        //populate the board with bombs

        int bombsLeft = numBombs;
        while (bombsLeft != 0) {
            Random random = new Random();
            int i = random.nextInt(numRows);
            int j = random.nextInt(numCols);
            //find the background id
            if (tiles[i][j] == null || tiles[i][j].getId() != MinesweeperTile.BOMB_ID) {
                tiles[i][j] = new MinesweeperTile(MinesweeperTile.BOMB_ID);
                bombsLeft -= 1;
            }
        }
        //Add values that correspond to the bombs around it
        setNums();
        setChanged();
        notifyObservers();
    }

    /**
     * Taps the tile at position (x,y) and sets changed and notifies observers
     *
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     */
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
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if(tiles[i][j] == null || tiles[i][j].getId() != MinesweeperTile.BOMB_ID) {
                    tiles[i][j] = new MinesweeperTile(countBombs(i, j));
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
        for(int i = Math.max(0, x - 1); i < Math.min(x + 2, numRows); i ++){
            for(int j = Math.max(0, y - 1); j < Math.min(y + 2, numRows); j ++){
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
    private void setNumRows(int i) {
        numRows = i;}

    /**
     * Set the number of Cols
     * @param i the number of columbs
     */
    private void setNumCols(int i) {
        numCols = i;}

    /**
     * Set the number of bombs
     * @param i the number of bombs
     */
    private void setNumBombs(int i) {
        numBombs = i;}

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    public int numBombs() {
        return numBombs;
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

    /**
     * Return the number of rows on the board.
     *
     * @return the number of rows on the board
     */
    int getNumRows(){
        return numRows;
    }

    /**
     * Return the number of columns on the board.
     *
     * @return the number of columns on the board
     */
    int getNumCols(){
        return numCols;
    }

    @Override
    public String toString() {
        return "SlidingTilesBoard{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }


}