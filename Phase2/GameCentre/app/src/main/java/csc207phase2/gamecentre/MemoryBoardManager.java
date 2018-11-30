package csc207phase2.gamecentre;

import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

/**
 * Manage a Memory Game board
 */

public class MemoryBoardManager extends BoardManager {

    /**
     * The Memory Game board currently managed.
     */
    private MemoryBoard memoryBoard;

    /**
     * The game this is a part of.
     */
    transient private GameComponent game;

    /**
     * The first tile that is flipped.
     */
    private MemoryTile firstTile;

    /**
     * The second tile that is flipped.
     */
    private MemoryTile secondTile;

    /**
     * The stored position.
     */
    private int storedPosition;

    /**
     * The number of moves the boardmanager has processed.
     */
    private int numMoves;

    /**
     * Sorter for the ScoreBoard
     */
    private static ScoreSorter<Score> scoreSorter = new MinimumSorter<Score>();

    /**
     * Stores the scores for sliding tiles game.
     */
    private static ScoreBoard scores = new ScoreBoard(scoreSorter, 6);

    /**
     * Return the number of moves this boardmanager has processed.
     *
     * @return the number of moves this boardmanager has processed.
     */
    public int getNumMoves() {
        return numMoves;
    }

    /**
     * Return the score board for this game.
     *
     * @return the score board for this game.
     */
    public static ScoreBoard getScoreBoard() {return scores;}

    /**
     * The first tile which is undoable.
     */
    private int undoPosition;

    /**
     * The undoable steps for player.
     */
    int undoable = 3;

    /**
     * Return the first tile that is flipped.
     *
     * @return the first tile that is flipped
     */
    public MemoryTile getFirstTile(){
        return firstTile;
    }

    /**
     * Return the second tile that is flipped.
     *
     * @return the second tile that is flipped
     */
    public MemoryTile getSecondTile() {
        return secondTile;
    }

    /**
     * Set the first tile that is flipped.
     *
     * @param firstTile the first tile that is flipped
     */
    public void setFirstTile(MemoryTile firstTile) {
        this.firstTile = firstTile;
    }

    /**
     * Set the second tile that is flipped.
     *
     * @param secondTile the second tile that is flipped
     */
    public void setSecondTile(MemoryTile secondTile) {
        this.secondTile = secondTile;
    }

    /**
     * Returns the stored position.
     *
     * @return stored position
     */
    public int getStoredPosition() {
        return storedPosition;
    }

    /**
     * Returns the current Memory Game board.
     *
     * @return current Memory Game board
     */
    MemoryBoard getBoard(){
        return memoryBoard;
    }


    /**
     * Manage a shuffled Memory Game board.
     *
     * @param col the number of cols
     * @param row the number of rows
     */
    MemoryBoardManager(int row, int col) {
        List<MemoryTile> memoryTiles = new ArrayList<>();
        final int numTiles = (row * col) / 2;
        for (int tileNum = 0; tileNum < numTiles; tileNum++) {
            memoryTiles.add(new MemoryTile(tileNum));
            memoryTiles.add(new MemoryTile(tileNum));
        }

        Collections.shuffle(memoryTiles);
        this.memoryBoard = new MemoryBoard(row, col, memoryTiles);

    }

    /**
     * Returns whether the game has been solved.
     *
     * @return whether the game has been solved
     */
    boolean puzzleSolved() {
        boolean solved = true;

        Iterator<MemoryTile> iter = memoryBoard.iterator();

        while (iter.hasNext()) {
            if (!iter.next().getFlipped()) {
                solved = false;
            }
        }
        return solved;

    }

    /**
     * Returns whether the tap is valid (the tile is not current flipped).
     *
     * @param position the position of the tile to be checked
     * @return whether the tap is valid
     */
    boolean isValidTap (int position) {

        int row = position / memoryBoard.getNumRows();
        int col = position % memoryBoard.getNumCols();

        MemoryTile current = memoryBoard.getTile(row, col);

        return !current.getFlipped();
    }

    /**
     * Flips the first tile.
     *
     * @param position the position of the first tile to be flipped
     */
    void flipTile1(int position) {

        int row = position / memoryBoard.getNumRows();
        int col = position % memoryBoard.getNumCols();

        memoryBoard.flipTile(position);
        this.setFirstTile(memoryBoard.getTile(row, col));
        this.storedPosition = position;
        numMoves++;
    }

    /**
     * Flips the second tile.
     *
     * @param position the position of the second tile to be flipped
     */
    void flipTile2(int position) {
        int row = position / memoryBoard.getNumRows();
        int col = position % memoryBoard.getNumCols();

        memoryBoard.flipTile(position);
        this.setSecondTile(memoryBoard.getTile(row, col));
        numMoves++;
    }


    /**
     * Autosaves the game manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void autoSave(String fileName) {
        game.saveToFile(fileName);
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
     * Processes a touch at position in the board
     *
     * @param position the position to be processed
     */
    public void touchMove(int position){

        if (getFirstTile() == null) {
            flipTile1(position);
            undoPosition = position;
        } else if (getSecondTile() == null) {
            flipTile2(position);
            if (getFirstTile().equals(getSecondTile())) {
                if (puzzleSolved()) {
                    Toast.makeText(game.getApplicationContext(), "YOU WIN! Total Moves: " + getNumMoves(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(game.getApplicationContext(), "CORRECT PAIR!", Toast.LENGTH_SHORT).show();
                    setFirstTile(null);
                    setSecondTile(null);
                }
            } else {
                final Handler handler = new Handler();
                final int finalPosition = position;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getBoard().flipTile(finalPosition);
                        getBoard().flipTile(getStoredPosition());
                        setFirstTile(null);
                        setSecondTile(null);
                        Toast.makeText(game.getApplicationContext(), "WRONG PAIR!", Toast.LENGTH_SHORT).show();
                    }
                }, 500);
            }
        }
    }


    /**
     * Undo the first flipped tile when there is an undoable.
     */
    void undo() {
        memoryBoard.flipTile(undoPosition);
        undoable--;
        setFirstTile(null);
    }
}
