package csc207phase2.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Choosing complexity for the Minesweeper game.
 */
public class MinesweeperComplexity extends GameComponent{

    /**
     * The board manager.
     */
    private MinesweeperManager manager;

    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_minesweeper_complexity);
        addEasyButtonListener();
        addMediumButtonListener();
        addHardButtonListener();
    }

    /**
     * Activate the Easy button.
     */
    private void addEasyButtonListener() {
        Button easyButton = findViewById(R.id.easyButton);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager = new MinesweeperManager(9, 9);
                switchToGame();
            }
        });
    }

    /**
     * Activate the Medium button.
     */
    private void addMediumButtonListener() {
        Button mediumButton = findViewById(R.id.mediumButton);
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager = new MinesweeperManager(16, 16);
                switchToGame();
            }
        });
    }

    /**
     * Activate the Hard button.
     */
    private void addHardButtonListener() {
        Button hardButton = findViewById(R.id.hardButton);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager = new MinesweeperManager(20,20);
                switchToGame();
            }
        });
    }

    /**
     * Switch to MinesweeperGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, MinesweeperGameActivity.class);
        saveToFile(TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }


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
        return null;
    }
}
