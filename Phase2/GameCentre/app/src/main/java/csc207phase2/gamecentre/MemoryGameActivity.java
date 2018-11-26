package csc207phase2.gamecentre;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import java.util.ArrayList;

/**
 * The game activity for the memory game.
 */
public class MemoryGameActivity extends GameActivity {

    /**
     * The board manager.
     */
    private MemoryBoardManager memoryBoardManager;

    /**
     * The name of the current game.
     */
    static final String NAME = "MemoryGame";

    /**
     * This activity's content view.
     */
    static final int CONTENT_VIEW = R.layout.activity_main_memory;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //addUndoButtonListener();

    }

    @Override
    public void updateTileButtons() {
        super.updateTileButtons();
        if (memoryBoardManager.puzzleSolved()) {
            SharedPreferences prefs = this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            ArrayList<String> scores = memoryBoardManager.getScoreBoard().getTopScore();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < scores.size(); i++) {
                sb.append(scores.get(i)).append(",");
            }
            editor.putString("MEMORYSCOREBOARD", sb.toString());
            editor.commit();
        }
    }

//    /**
//     * Activate the undo button.
//     */
//    private void addUndoButtonListener() {
//    Button undoButton = findViewById(R.id.Undo);
//     undoButton.setOnClickListener(new View.OnClickListener() {@Override
//        public void onClick(View v) {
//             if (!slidingTilesBoardManager.stepSaver.empty()){
//             slidingTilesBoardManager.undo(slidingTilesBoardManager.stepSaver.undo());}
//          }
//       });
//    }

    @Override
    void setBoardManager(BoardManager m){
        this.memoryBoardManager = (MemoryBoardManager) m;
    }

    @Override
    MemoryBoardManager getBoardManager(){
        return memoryBoardManager;
    }

    @Override
    String getName(){
        return NAME;
    }

    @Override
    public int getContentView() {
        return CONTENT_VIEW;
    }

}
