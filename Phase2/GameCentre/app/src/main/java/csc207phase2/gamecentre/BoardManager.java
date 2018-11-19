package csc207phase2.gamecentre;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager extends GameManager {

    transient private FirebaseAuth mAuth;
    transient private FirebaseUser currentUser;
    private String accountName;
    private int count = 0;

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * The step saver for undo.
     */
    StepSaver stepSaver = new StepSaver();

    /**
     * The number of moves the boardmanager has processed.
     */
    private int numMoves;

    /**
     * Scoreboard stores the scores for sliding tiles game.
     */
    private static ScoreSorter<Score> scoreSorter = new MinimumSorter<Score>();
    private static ScoreBoard<Score> scores = new ScoreBoard<Score>(scoreSorter);

    /**
     * The game this is a part of.
     */
    transient private GameComponent game;

    /**
     * Return the current board.
     * @return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Return the number of moves this boardmanager has processed.
     * @return the number of moves this boardmanager has processed.
     */
    public int getNumMoves() {
        return numMoves;
    }

    /**
     * Return the score board for this game.
     * @return the score board for this game.
     */

    public static ScoreBoard<Score> getScoreBoard() {return scores;}

    public String getAccountName() {return this.accountName; }

    /**
     * Manage a new shuffled board.
     */
    BoardManager(int row, int col) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = row * col - 1;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(24));

        Collections.shuffle(tiles);
        this.board = new Board(row, col, tiles);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        accountName = currentUser.getEmail();
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        boolean solved = true;

        Iterator<Tile> iter = board.iterator();

        int count = 1;
        while (iter.hasNext()) {
            if (iter.next().getId() != count) {
                solved = false;
            }
            count++;
        }
        if (solved) {
            Score newScore = new Score(this.getAccountName(),
                    "Sliding Tiles: " + this.getBoard().getNumRows());
            newScore.setScorePoint(this.getNumMoves());
            this.scores.addScore(newScore);
            //SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
            //SharedPreferences.Editor editor = prefs.edit();
            //ArrayList<String> scores = this.getScoreBoard().getTopScore();
            //StringBuilder sb = new StringBuilder();
            //for (int i = 0; i < scores.size(); i++) {
            //    sb.append(scores.get(i)).append(",");
            //}
            //editor.putString("SCOREBOARD", sb.toString());
            //editor.commit();
        }

        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / board.getNumCols();
        int col = position % board.getNumCols();
        int blankId = 25;
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == board.getNumRows() - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == board.getNumCols() - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.Save current move.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / board.getNumRows();
        int col = position % board.getNumCols();
        int blankId = 25;
        if (count == 4) {
            autoSave(SlidingTilesActivity.SAVE_FILENAME);
            count = 0;

        } else {
            count += 1;
        }

        if (row > 0 && board.getTile(row - 1, col).getId() == blankId) {
            board.swapTiles(row, col, row - 1, col);
            stepSaver.recordMove(row, col, row - 1, col);
            numMoves++;
        } else if (row < board.getNumRows() - 1 && board.getTile(row + 1, col).getId() == blankId) {
            board.swapTiles(row + 1, col, row, col);
            stepSaver.recordMove(row + 1, col, row, col);
            numMoves++;
        } else if (col > 0 && board.getTile(row, col - 1).getId() == blankId) {
            board.swapTiles(row, col, row, col - 1);
            stepSaver.recordMove(row, col, row, col - 1);
            numMoves++;
        } else if (col < board.getNumCols() - 1 && board.getTile(row, col + 1).getId() == blankId) {
            board.swapTiles(row, col, row, col + 1);
            stepSaver.recordMove(row, col, row, col + 1);
            numMoves++;
        }

    }

    /**
     * Process an undo move with the stored steps.
     *
     * @param position the position
     */
    void undo(Integer[] position) {
        int a = position[0];
        int b = position[1];
        int c = position[2];
        int d = position[3];
        board.swapTiles(a,b,c,d);
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


}