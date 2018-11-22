package csc207phase2.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MinesweeperMenuActivity extends GameComponent{

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";

    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    /**
     * The game's manager.
     */
    private MinesweeperManager manager;

    /**
     * The name of this game.
     */
    static final String NAME = "Minesweeper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saveToFile(TEMP_SAVE_FILENAME);
        setContentView(R.layout.activity_minesweeper_menu);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addScoreButtonListener();
    }

    /**
     * Activate the start button.
     */
    private void addScoreButtonListener() {
        Button scoreButton = findViewById(R.id.scoreButton);
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreView();
            }
        });
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.NewGame);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToComplexity();
            }
        });
    }

    /**
     * Goes to complexity selection activity.
     */
    private void goToComplexity(){
        Intent chooseComplex = new Intent(this, MinesweeperComplexity.class);
        startActivity(chooseComplex);
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile(SAVE_FILENAME);
                if(manager != null){
                    saveToFile(TEMP_SAVE_FILENAME);
                    makeToastLoadedText();
                    switchToGame();
                }else{
                    makeToastNoSaveText();
                }

            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that they attempted to load a game that didn't exist.
     */
    private void makeToastNoSaveText() {
        Toast.makeText(this, "No save file to load.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(SAVE_FILENAME);
                saveToFile(TEMP_SAVE_FILENAME);
                makeToastSavedText();
            }
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadFromFile(TEMP_SAVE_FILENAME);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, MinesweeperGameActivity.class);
        saveToFile(SlidingTilesActivity.TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    /**
     * Switch to scoreViewActivity view scores.
     */
    private void switchToScoreView() {
        List topScores = BoardManager.getScoreBoard().getListScores();
        String[] scoreText = new String[topScores.size()];
        for(int i = 0; i < topScores.size(); i ++){
            scoreText[i] = topScores.get(i).toString();
        }
        Intent tmp = new Intent(this, ScoreViewActivity.class);
        tmp.putExtra("scoreText", scoreText);
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
        return NAME;
    }
}