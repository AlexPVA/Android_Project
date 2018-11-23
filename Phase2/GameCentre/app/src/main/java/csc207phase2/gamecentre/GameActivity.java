package csc207phase2.gamecentre;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

abstract class GameActivity extends GameComponent implements Observer {

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        loadFromFile(TEMP_SAVE_FILENAME);
        createTileButtons(this);
        setContentView(getContentView());
        BoardManager manager = getBoardManager();

        manager.setGame(this);

        final int num_cols = manager.getBoard().getNumCols();
        final int num_rows = manager.getBoard().getNumRows();

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(num_cols);
        gridView.setBoardManager(manager);
        manager.getBoard().addObserver(this);
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
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = getBoardManager().getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getNumRows(); row++) {
            for (int col = 0; col != board.getNumCols(); col++) {
                Button tmp = new Button(context);
                Log.e("ID_TEST", ""+board.getTile(row, col).getId());
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        BoardManager manager = getBoardManager();
        Board board = manager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / board.getNumRows();
            int col = nextPos % board.getNumCols();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
        if (manager.puzzleSolved()) {
            SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            ArrayList<String> scores = SlidingTilesManager.getScoreBoard().getTopScore();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < scores.size(); i++) {
                sb.append(scores.get(i)).append(",");
            }
            editor.putString("SCOREBOARD", sb.toString());
            editor.commit();
        }
    }

    abstract public int getContentView();

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(TEMP_SAVE_FILENAME);
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }


}
