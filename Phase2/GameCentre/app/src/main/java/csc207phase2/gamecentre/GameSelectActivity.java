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
    }

    /**
     * Activate the Sliding Tiles button.
     */
    private void addSlidingTilesButtonListener() {
        Button SlidingTilesButton = findViewById(R.id.SlidingTiles);
        SlidingTilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSlidingTiles();
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
                switchToMinesweeper();
            }
        });
    }

    /**
     * Switch to the SlidingTilesActivity to start playing Sliding Tiles.
     */
    private void switchToSlidingTiles() {
        Intent tmp = new Intent(this, SlidingTilesActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the MineSweeperMenuActivity to start playing Minesweeper.
     */
    private void switchToMinesweeper() {
        Intent tmp = new Intent(this, MinesweeperMenuActivity.class);
        startActivity(tmp);
    }
}

