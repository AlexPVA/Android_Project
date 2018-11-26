package csc207phase2.gamecentre;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        addUndoButtonListener();

    }

    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.MemoryUndo);
        undoButton.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) {
            if (memoryBoardManager.undoAllowance == 0){
                Toast.makeText(getApplicationContext(), "You used up your undo allowance!", Toast.LENGTH_SHORT).show();
            }
            if(memoryBoardManager.undoAllowance > 0){
            if (memoryBoardManager.getFirstTile() != null && memoryBoardManager.getSecondTile() == null)
            {memoryBoardManager.undo();}
             else {
                Toast.makeText(getApplicationContext(), "Invalid Undo!", Toast.LENGTH_SHORT).show();}}
        }
        });
   }

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
