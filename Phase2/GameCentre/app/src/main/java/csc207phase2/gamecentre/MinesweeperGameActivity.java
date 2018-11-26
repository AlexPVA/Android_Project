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
