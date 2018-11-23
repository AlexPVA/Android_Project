package csc207phase2.gamecentre;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

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
