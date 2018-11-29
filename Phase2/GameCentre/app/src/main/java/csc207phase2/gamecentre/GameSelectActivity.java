package csc207phase2.gamecentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The game select activity.
 */
public class GameSelectActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);

        addSlidingTilesButtonListener();
        addMinesweeperButtonListener();
        addMemoryGameButtonListener();
    }

    /**
     * Activate the Sliding Tiles button.
     */
    private void addSlidingTilesButtonListener() {
        Button SlidingTilesButton = findViewById(R.id.SlidingTiles);
        SlidingTilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(SlidingTilesActivity.class);
            }
        });
    }

    /**
     * Activate the Minesweeper button.
     */
    private void addMinesweeperButtonListener() {
        Button SlidingTilesButton = findViewById(R.id.Minesweeper);
        SlidingTilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(MinesweeperMenuActivity.class);
            }
        });
    }

    /**
     * Activate the Memory Game button.
     */
    private void addMemoryGameButtonListener() {
        Button MemoryGameButton = findViewById(R.id.MemoryGame);
        MemoryGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(MemoryGameStartActivity.class);
            }
        });
    }

    /**
     * Switch to the specified game.
     */
    private void switchToGame(Class targetClass){
        Intent tmp = new Intent(this, targetClass);
        startActivity(tmp);
    }

}

