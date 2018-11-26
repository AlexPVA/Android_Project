package csc207phase2.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class MemoryGameStartActivity extends GameComponent {

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";
    /**
     * The board manager.
     */
    private MemoryBoardManager memoryBoardManager;

    static final String NAME = "MemoryGame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saveToFile(TEMP_SAVE_FILENAME);
        setContentView(R.layout.activity_starting_memory);
        addStartButtonListener();
        //addLoadButtonListener();
        //addSaveButtonListener();
        addScoreButtonListener();
    }

    /**
     * Activate the score button.
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

    private void startGame(){
        Intent tmp = new Intent(this, MemoryGameActivity.class);
        saveToFile(MemoryGameStartActivity.TEMP_SAVE_FILENAME);
        startActivity(tmp);
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
                if(memoryBoardManager != null){
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
     * Switch to the SlidingTilesGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
        saveToFile(SlidingTilesActivity.TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    /**
     * Goes to complexity selection activity.
     */
    private void goToComplexity(){
        Intent chooseComplex = new Intent(this, MemoryChooseComplexity.class);
        startActivity(chooseComplex);
    }

    /**
     * Switch to scoreViewActivity view scores.
     */
    private void switchToScoreView() {
        List topScores = MemoryBoardManager.getScoreBoard().getListScores();
        String[] scoreText = new String[topScores.size()];
        for (int i = 0; i < topScores.size(); i++) {
            scoreText[i] = topScores.get(i).toString();
        }
        Intent tmp = new Intent(this, MemoryScoreActivity.class);
        tmp.putExtra("scoreText", scoreText);
        startActivity(tmp);
    }

    /**
     * Sets the current board manager for the game.
     *
     * @param m the manager for the game
     */
    void setBoardManager(BoardManager m){
        this.memoryBoardManager = (MemoryBoardManager) m;
    }

    /**
     * Returns the current board manager for the game.
     *
     * @return the current board manager
     */
    MemoryBoardManager getBoardManager(){
        return memoryBoardManager;
    }

    /**
     * Returns the game's name.
     *
     * @return the game's name
     */
    String getName(){
        return NAME;
    }

}
