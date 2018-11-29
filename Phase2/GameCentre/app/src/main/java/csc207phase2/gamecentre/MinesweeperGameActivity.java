package csc207phase2.gamecentre;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

public class MinesweeperGameActivity extends GameActivity {

    /**
     * The name of this game.
     */
    static final String NAME = "Minesweeper";

    /**
     * The game's manager.
     */
    private MinesweeperManager manager;

    /**
     * This activity's content view.
     */
    static final int CONTENT_VIEW = R.layout.activity_minesweeper_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void updateTileButtons() {
        super.updateTileButtons();
        if (manager.puzzleSolved()) {
            updateScoreBoard();
            updateUserScoreBoard();
        }
    }

    /**
     * Put an updated scoreboard into the shared preferences to be accessed by score view.
     */
    public void updateScoreBoard() {
        Score newScore = new Score(manager.getAccountName(),
                "Minesweeper " + manager.getBoard().getNumRows());
        newScore.setScorePoint(manager.getNumMoves() + 1);
        MinesweeperManager.getScoreBoard().addScore(newScore);
        SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        ArrayList<String> scores = MinesweeperManager.getScoreBoard().getTopScore();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scores.size(); i++) {
            sb.append(scores.get(i)).append(",");
        }
        editor.putString("MINESWEEPERSCOREBOARD", sb.toString());
        editor.commit();
    }

    /**
     * Put an updated user scoreboard into the shared preferences to be accessed by user score view.
     */
    public void updateUserScoreBoard() {
        SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String user = manager.getAccountName();
        Score userHighScore = MinesweeperManager.getScoreBoard().getUserHighscore(user);
        String userScoreBoard = prefs.getString(user, null);
        if(userScoreBoard != null) {
            String[] scoreText = userScoreBoard.split(",");
            for (int i = 0; i < scoreText.length; i++) {
                if (scoreText[i].matches("(.*)Minesweeper(.*)") || !scoreText[i].matches("(.*)Score:(.*)")) {
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
    void setBoardManager(BoardManager m) {
        this.manager = (MinesweeperManager) m;
    }

    @Override
    BoardManager getBoardManager() {
        return manager;
    }

    @Override
    String getName() {
        return NAME;
    }

    @Override
    public int getContentView(){
        return CONTENT_VIEW;
    }

}
