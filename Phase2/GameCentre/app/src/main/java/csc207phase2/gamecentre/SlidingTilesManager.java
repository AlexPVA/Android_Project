package csc207phase2.gamecentre;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class SlidingTilesManager extends BoardManager {

    /**
     * Firebase authentication.
     */
    transient private FirebaseAuth mAuth;

    /**
     * Firebase user currently signed in.
     */
    transient private FirebaseUser currentUser;

    /**
     * Account name of the current user.
     */
    private String accountName;

    /**
     * Count for keeping track of when to autosave.
     */
    private int autosaveCount = 0;

    /**
     * The board being managed.
     */
    private SlidingTilesBoard board;

    /**
     * The step saver for undo.
     */
    StepSaver stepSaver = new StepSaver();

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
    private static ScoreBoard scores = new ScoreBoard(scoreSorter);

    /**
     * The game this is a part of.
     */
    transient private GameComponent game;

    /**
     * Return the current board.
     *
     * @return the current board.
     */
    SlidingTilesBoard getBoard() {
        return board;
    }

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
     * Return the name of the current user account.
     *
     * @return the name of the current user account.
     */
    public String getAccountName() {return this.accountName; }

    /**
     * Manage a new shuffled board.
     *
     * @param row the number of rows.
     * @param col the number of cols.
     */
    SlidingTilesManager(int row, int col) {
        List<SlidingTilesTile> tiles = new ArrayList<>();
        final int numTiles = row * col - 1;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new SlidingTilesTile(tileNum));
        }
        tiles.add(new SlidingTilesTile(24));

        Collections.shuffle(tiles);
        this.board = new SlidingTilesBoard(row, col, tiles);
        while (!this.boardSolvability(board)){
            this.board = new SlidingTilesBoard(row, col, tiles);
        }

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

        Iterator<SlidingTilesTile> iter = board.iterator();

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
        SlidingTilesTile above = row == 0 ? null : board.getTile(row - 1, col);
        SlidingTilesTile below = row == board.getNumRows() - 1 ? null : board.getTile(row + 1, col);
        SlidingTilesTile left = col == 0 ? null : board.getTile(row, col - 1);
        SlidingTilesTile right = col == board.getNumCols() - 1 ? null : board.getTile(row, col + 1);
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
        if (autosaveCount == 4) {
            autoSave(SlidingTilesActivity.SAVE_FILENAME);
            autosaveCount = 0;

        } else {
            autosaveCount += 1;
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
    @Override
    public void autoSave(String fileName) {
        game.saveToFile(fileName);
    }

    /**
     * Sets the game this is a part of.
     *
     * @param game the game object this is a part of
     */
    @Override
    public void setGame(GameComponent game){
        this.game = game;
    }



    private boolean boardSolvability(SlidingTilesBoard board){
        boolean solvable = false;

    return solvable;
    }

}