package csc207phase2.gamecentre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import java.util.ArrayList;
import android.view.View;
import android.widget.Toast;

/**
 * The game activity for the memory game.
 */
public class MemoryGameActivity extends GameActivity {

    /**
     * The board manager.
     */
    private MemoryBoardManager memoryBoardManager;

    /**
     * The name of the current game.
     */
    static final String NAME = "MemoryGame";

    /**
     * This activity's content view.
     */
    static final int CONTENT_VIEW = R.layout.activity_main_memory;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        addUndoButtonListener();

    }

    /**
     * Activate the undo button.
     */
    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.MemoryUndo);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memoryBoardManager.undoable == 0) {
                    Toast.makeText(getApplicationContext(), "You used up your undoable's!", Toast.LENGTH_SHORT).show();
                }
                if (memoryBoardManager.undoable > 0) {
                    if (memoryBoardManager.getFirstTile() != null && memoryBoardManager.getSecondTile() == null) {
                        memoryBoardManager.undo();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Undo!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /**
     * Update the tiles, processing the tiles when being tapped.
     */
    @Override
    public void updateTileButtons() {
        super.updateTileButtons();
        if (memoryBoardManager.puzzleSolved()) {
            updateScoreBoard();
            updateUserScoreBoard();
        }
    }

    /**
     * Put an updated scoreboard into the shared preferences to be accessed by score view.
     */
    public void updateScoreBoard() {
        Score newScore = new Score(memoryBoardManager.getAccountName(),
                "Memory Game " + memoryBoardManager.getBoard().getNumRows());
        newScore.setScorePoint(memoryBoardManager.getNumMoves() + 1);
        MemoryBoardManager.getScoreBoard().addScore(newScore);
        SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        ArrayList<String> scores = MemoryBoardManager.getScoreBoard().getTopScore();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scores.size(); i++) {
            sb.append(scores.get(i)).append(",");
        }
        editor.putString("MEMORYSCOREBOARD", sb.toString());
        editor.commit();
    }

    /**
     * Put an updated user scoreboard into the shared preferences to be accessed by user score view.
     */
    public void updateUserScoreBoard() {
        SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String user = memoryBoardManager.getAccountName();
        Score userHighScore = MemoryBoardManager.getScoreBoard().getUserHighscore(user);
        String userScoreBoard = prefs.getString(user, null);
        if(userScoreBoard != null) {
            String[] scoreText = userScoreBoard.split(",");
            for (int i = 0; i < scoreText.length; i++) {
                if (scoreText[i].matches("(.*)Memory(.*)") || !scoreText[i].matches("(.*)Score:(.*)")) {
                    scoreText[i] = null;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int index = 0; index < scoreText.length; index++) {
                if (scoreText[index] != null) {
                    sb.append(scoreText[index]).append(index).append(",");
                }
            }
            if (sb.toString().equals("")) {
                String appendedNewScore = userHighScore.toString();
                editor.putString(user, appendedNewScore);
            }
            else {
                userScoreBoard = sb.toString();
                String appendedNewScore = userScoreBoard + ", " + userHighScore.toString();
                editor.putString(user, appendedNewScore);
            }
        }
        else {
            editor.putString(user, userHighScore.toString());
        }
        editor.commit();
    }

    @Override
    void setBoardManager(BoardManager m){
        this.memoryBoardManager = (MemoryBoardManager) m;
    }

    @Override
    MemoryBoardManager getBoardManager(){
        return memoryBoardManager;
    }

    @Override
    String getName(){
        return NAME;
    }

    @Override
    public int getContentView() {
        return CONTENT_VIEW;
    }

}
