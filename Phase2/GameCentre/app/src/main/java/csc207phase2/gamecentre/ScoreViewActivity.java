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
 * Displays the scores for a given game/user
 */
public class ScoreViewActivity extends AppCompatActivity {

    /**
     * The linear layout to draw to.
     */
    private LinearLayout layout;

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
        drawText("Top Scores:", TITLE_SIZE);

        Intent getIntent = getIntent();
        SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        String scores = prefs.getString("SCOREBOARD", null);

        //String[] scoreText = getScoreText();
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

    /**
     * Get the array of text to draw to the layout from the intent extras.
     *
     * @return The array of text to draw to the layout
     */
    //String[] getScoreText(){
        //SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = prefs.edit();
        //BoardManager board = new BoardManager(3, 3);
        //ArrayList<String> scores = board.getScoreBoard().getTopScore();
        //StringBuilder sb = new StringBuilder();
        //for (int i = 0; i < scores.size(); i++) {
        //    sb.append(scores.get(i)).append(",");
        //}
        //editor.putString("SCOREBOARD", sb.toString());
        //editor.commit();
        //String[] scoreText = {"a", "b", "c"};//getIntent().getStringArrayExtra("scoreText");
        //if(scoreText == null) {
        //    Log.e("score_view", "scoreText is null (ScoreViewActivity)");
        //}
        //return scoreText;
    //}

}
