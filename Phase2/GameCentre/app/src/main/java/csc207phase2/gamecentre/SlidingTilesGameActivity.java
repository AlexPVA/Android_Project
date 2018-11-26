package csc207phase2.gamecentre;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * The game activity.
 */
public class SlidingTilesGameActivity extends GameActivity {

    /**
     * The board manager.
     */
    private SlidingTilesManager boardManager;

    /**
     * The name of this game.
     */
    static final String NAME = "SlidingTiles";

    /**
     * This activity's content view.
     */
    static final int CONTENT_VIEW = R.layout.activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addUndoButtonListener();
    }

    @Override
    public void updateTileButtons() {
        super.updateTileButtons();
        if (boardManager.puzzleSolved()) {
            SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            ArrayList<String> scores = boardManager.getScoreBoard().getTopScore();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < scores.size(); i++) {
                sb.append(scores.get(i)).append(",");
            }
            editor.putString("SCOREBOARD", sb.toString());
            editor.commit();
        }
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
     * Sets the current BoardManager for this game.
     *
     * @param m The BoardManager for this game.
     */
    @Override
    void setBoardManager(BoardManager m){
        this.boardManager = (SlidingTilesManager)m;
    }

    /**
     * Returns the current BoardManager for this game.
     *
     * @return the current BoardManager.
     */
    @Override
    SlidingTilesManager getBoardManager(){
        return boardManager;
    }

    /**
     * Returns name of this game.
     *
     * @return this game's name.
     */
    @Override
    String getName(){
        return NAME;
    }

    @Override
    public int getContentView() {
        return CONTENT_VIEW;
    }

}
