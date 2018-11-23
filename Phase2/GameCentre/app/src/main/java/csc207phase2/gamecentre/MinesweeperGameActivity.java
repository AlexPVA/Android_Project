package csc207phase2.gamecentre;

import android.os.Bundle;

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
