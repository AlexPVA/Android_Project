package csc207phase2.gamecentre;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The game activity.
 */
public class GameActivity extends GameComponent implements Observer {

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    static final String NAME = "SlidingTiles";

    /**
     * Constants for swiping directions. Should be an enum, probably.
     */
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        loadFromFile(ChooseComplexity.TEMP_SAVE_FILENAME);
        createTileButtons(this);
        setContentView(R.layout.activity_main);
        addUndoButtonListener();

        boardManager.setGame(this);

        final int num_cols = boardManager.getBoard().getNumCols();
        final int num_rows = boardManager.getBoard().getNumRows();

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(num_cols);
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / num_cols;
                        columnHeight = displayHeight / num_rows;

                        display();
                    }
                });

    }

    /**
     * Activate the undo button.
     */
    private void addUndoButtonListener() {
    Button undoButton = findViewById(R.id.Undo);
     undoButton.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) {
             if (!boardManager.stepSaver.empty()){
             boardManager.undo(boardManager.stepSaver.undo());}
          }
       });
    }
    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getNumRows(); row++) {
            for (int col = 0; col != board.getNumCols(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        Board board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / board.getNumRows();
            int col = nextPos % board.getNumCols();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
        if (boardManager.puzzleSolved()) {
            SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            ArrayList<String> scores = BoardManager.getScoreBoard().getTopScore();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < scores.size(); i++) {
                sb.append(scores.get(i)).append(",");
            }
            editor.putString("SCOREBOARD", sb.toString());
            editor.commit();
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(SlidingTilesActivity.TEMP_SAVE_FILENAME);
    }

    void setGameManager(GameManager m){
        this.boardManager = (BoardManager)m;
    }
    BoardManager getGameManager(){
        return boardManager;
    }



    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    String getName(){
        return NAME;
    }
}
