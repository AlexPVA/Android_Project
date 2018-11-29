package csc207phase2.gamecentre;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Draws scores to the screen.
 */
public class ScoreActivity extends AppCompatActivity {

    /**
     * The linear layout to draw to.
     */
    LinearLayout layout;

    /**
     * The size of the title.
     */
    static final float TITLE_SIZE = 30;

    /**
     * The size of each line of text drawn to the layout (other than the title).
     */
    static final float SCORE_SIZE = 20;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_view);
        View linearLayout = findViewById(R.id.scores);
        layout = (LinearLayout)linearLayout;

        String game = getIntent().getStringExtra("NAME");
        String key;

        if(game.equals("MemoryGame")){
            key = "MEMORYSCOREBOARD";
        }else if(game.equals("Minesweeper")){
            key = "MINESWEEPERSCOREBOARD";
        }else{
            key = "SCOREBOARD";
        }

        SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        String scores = prefs.getString(key, null);

        drawText("Top Scores:", TITLE_SIZE);
        if(scores != null) {
            String[] scoreText = scores.split(",");
            for (String score : scoreText) {
                drawText("__________________________________", 20);
                drawText(score, SCORE_SIZE);
            }
        }
    }

    /**
     * Adds the text to the layout at the given font size.
     *
     * @param text the text to draw
     * @param size the font size
     */
    private void drawText(String text, float size){
        TextView value = new TextView(this);
        value.setText(text);
        value.setId(View.generateViewId());
        value.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT));
        value.setTextSize(size);
        layout.addView(value);
    }

}