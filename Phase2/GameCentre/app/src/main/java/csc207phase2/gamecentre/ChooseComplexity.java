package csc207phase2.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Choosing for the sliding puzzle tile game.
 */
public class ChooseComplexity extends GameComponent {

    /**
     * The board manager.
     */
    private SlidingTilesManager boardManager;

    /**
     * The name of the game this is a part of
     */
    static final String NAME = "SlidingTiles";

    /**
     * The undo count. Default to be 3.
     */
    private EditText undoCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_complexity);
        add3ButtonListener();
        add4ButtonListener();
        add5ButtonListener();
    }

    /**
     * Activate the 3by3 button.
     */
    private void add3ButtonListener() {
        Button Button_3 = findViewById(R.id.threeButton);
        Button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoCount = findViewById(R.id.setUndo);
                String value = undoCount.getText().toString();
                int count = Integer.parseInt(value);
                if (count == 0) {
                    count = 3;
                    Toast.makeText(getApplicationContext(), "Can't set undo count to 0, set to default 3!", Toast.LENGTH_SHORT).show();
                }
                boardManager = new SlidingTilesManager(3, 3, count);
                switchToGame();
            }
        });
    }

    /**
     * Activate the 4by4 button.
     */
    private void add4ButtonListener() {
        Button Button_3 = findViewById(R.id.fourButton);
        Button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoCount = findViewById(R.id.setUndo);
                String value = undoCount.getText().toString();
                int count = Integer.parseInt(value);
                if (count == 0) {
                    count = 3;
                    Toast.makeText(getApplicationContext(), "Can't set undo count to 0, set to default 3!", Toast.LENGTH_SHORT).show();
                }
                boardManager = new SlidingTilesManager(4, 4, count);
                switchToGame();
            }
        });
    }

    /**
     * Activate the 5by5 button.
     */
    private void add5ButtonListener() {
        Button Button_4 = findViewById(R.id.fiveButton);
        Button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoCount = findViewById(R.id.setUndo);
                String value = undoCount.getText().toString();
                int count = Integer.parseInt(value);
                if (count == 0) {
                    count = 3;
                    Toast.makeText(getApplicationContext(), "Can't set undo count to 0, set to default 3!", Toast.LENGTH_SHORT).show();
                }
                boardManager = new SlidingTilesManager(5, 5, count);
                switchToGame();
            }
        });
    }

    /**
     * Switch to the SlidingTilesGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
        saveToFile(ChooseComplexity.TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    @Override
    void setBoardManager(BoardManager m) {
        boardManager = (SlidingTilesManager) m;
    }

    @Override
    BoardManager getBoardManager() {
        return boardManager;
    }

    @Override
    String getName() {
        return NAME;
    }
}
