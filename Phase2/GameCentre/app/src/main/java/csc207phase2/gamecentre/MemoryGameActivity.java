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
 * The game activity for the memory game.
 */
public class MemoryGameActivity extends GameComponent implements Observer {

    /**
     * The board manager.
     */
    private MemoryBoardManager memoryBoardManager;

    /**
     * The buttons to display.
     */
    public static ArrayList<Button> tileButtons;

    /**
     * The name of the current game.
     */
    static final String NAME = "MemoryGame";

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
        loadFromFile(MemoryGameStartActivity.TEMP_SAVE_FILENAME);
        createTileButtons(this);
        setContentView(R.layout.activity_main_memory);
        //addUndoButtonListener();

        memoryBoardManager.setGame(this);

        final int num_cols = memoryBoardManager.getMemoryBoard().getNumCols();
        final int num_rows = memoryBoardManager.getMemoryBoard().getNumRows();

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(num_cols);
        gridView.setBoardManager(memoryBoardManager);
        memoryBoardManager.getMemoryBoard().addObserver(this);
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

//    /**
//     * Activate the undo button.
//     */
//    private void addUndoButtonListener() {
//    Button undoButton = findViewById(R.id.Undo);
//     undoButton.setOnClickListener(new View.OnClickListener() {@Override
//        public void onClick(View v) {
//             if (!slidingTilesBoardManager.stepSaver.empty()){
//             slidingTilesBoardManager.undo(slidingTilesBoardManager.stepSaver.undo());}
//          }
//       });
//    }
    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        MemoryBoard memoryBoard = memoryBoardManager.getMemoryBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != memoryBoard.getNumRows(); row++) {
            for (int col = 0; col != memoryBoard.getNumCols(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(memoryBoard.getTile(row, col).getBackground());
                tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        MemoryBoard memoryBoard = memoryBoardManager.getMemoryBoard();
        //MemoryBoard blankMemoryBoard = memoryBoardManager.getBlankMemoryBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / memoryBoard.getNumRows();
            int col = nextPos % memoryBoard.getNumCols();
            b.setBackgroundResource(memoryBoard.getTile(row, col).getBackground());
            nextPos++;
        }
//        if (memoryBoardManager.puzzleSolved()) {
//            SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = prefs.edit();
//            ArrayList<String> scores = SlidingTilesBoardManager.getScoreBoard().getTopScore();
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < scores.size(); i++) {
//                sb.append(scores.get(i)).append(",");
//            }
//            editor.putString("SCOREBOARD", sb.toString());
//            editor.commit();
//        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(SlidingTilesActivity.TEMP_SAVE_FILENAME);
    }

    @Override
    void setGameManager(GameManager m){
        this.memoryBoardManager = (MemoryBoardManager) m;
    }

    @Override
    MemoryBoardManager getGameManager(){
        return memoryBoardManager;
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    @Override
    String getName(){
        return NAME;
    }
}
