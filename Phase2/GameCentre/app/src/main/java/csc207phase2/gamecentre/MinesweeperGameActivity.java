package csc207phase2.gamecentre;

public class MinesweeperGameActivity extends GameComponent{

    /**
     * The name of this game.
     */
    static final String NAME = "Minesweeper";

    /**
     * The game's manager.
     */
    private MinesweeperManager manager;


    @Override
    void setGameManager(GameManager m) {
        this.manager = (MinesweeperManager) m;
    }

    @Override
    GameManager getGameManager() {
        return manager;
    }

    @Override
    String getName() {
        return NAME;
    }

}
