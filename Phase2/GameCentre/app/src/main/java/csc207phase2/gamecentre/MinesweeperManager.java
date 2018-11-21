package csc207phase2.gamecentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

/**
 * Manage a board
 */
class MinesweeperManager implements Serializable {

    /**
     * The board being managed.
     */
    private MinesweeperBoard board;

    /**
     * Account name of the current user.
     */
    private String accountName;

    /**
     * The number of moves the boardmanager has processed.
     */
    private int numMoves;

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    MinesweeperManager(MinesweeperBoard board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    MinesweeperBoard getBoard() {
        return board;
    }

    /**
     * Sorter for the ScoreBoard
     */
    private static ScoreSorter<Score> scoreSorter = new MinimumSorter<Score>();

    /**
     * Stores the scores for sliding tiles game.
     */
    private static ScoreBoard<Score> scores = new ScoreBoard<Score>(scoreSorter);

    /**
     * The game this is a part of.
     */
    transient private GameComponent game;

    /**
     * Manage a new shuffled board.
     */
    MinesweeperManager(int row, int col) {
        this.board = new MinesweeperBoard(row, col);
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        int untapped = 0;
        boolean result = false;

        //find the number of untapped tiles
        for (MinesweeperTile tile: board) {
            if (tile.getId() == 10) {
                untapped++;
            }
        }
        if (board.numBombs() == untapped) {
            result = true;
            Score newScore = new Score(this.getAccountName(),
                    "Sliding Tiles: " + this.getBoard().getNumRows());
            newScore.setScorePoint(this.getNumMoves());
            this.scores.addScore(newScore);
        }

        //if number of bombs == number of untapped spaces the game ends
        return board.numBombs() == untapped;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return
     */
    boolean isValidTap(int position) {
        int row = position / board.getNumCols();
        int col = position / board.getNumRows();

        //if it hasn't been tapped before
        return board.getTile(row, col).getId() == 10;
    }

    /**
     * Process a touch at position in the board.
     *
     * @param position the position
     */
    void touchMove(int position) {
        int row = position / board.getNumCols();
        int col = position / board.getNumRows();
        //if a player touches a bomb it ends

        MinesweeperTile tile = board.getTile(row, col);

        if (tile.getId() == 9) {
            //ends the game
            tile.setTapped(true);
            numMoves++;
        }
        //if a player touches a number it shows up
        if (tile.getId() != 9 || tile.getId() != 10) {
            tile.setTapped(true);
            numMoves++;
        }
        //if the player touches a blank it shows all blank tiles until it reaches a number

        //recursion


    }

    /**
     * Sets the game this is a part of.
     *
     * @param game the game object this is a part of
     */
    public void setGame(GameComponent game){
        this.game = game;
    }

    /**
     * Return the name of the current user account.
     * @return the name of the current user account.
     */
    public String getAccountName() {return this.accountName; }

    /**
     * Return the number of moves this boardmanager has processed.
     * @return the number of moves this boardmanager has processed.
     */
    public int getNumMoves() {
        return numMoves;
    }

}