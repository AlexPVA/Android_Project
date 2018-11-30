package csc207phase2.gamecentre;

import android.content.Context;
import android.widget.Toast;
/**
 * The movement controller which handles tap movement events sends them to boardManager
 */
public class MovementController {

    /**
     * The BoardManager to send touchMove events to
     */
    private BoardManager boardManager = null;

    public MovementController() {
    }

    /**
     * Sets the BoardManager to send touchMove events to
     */
    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Process a tap at the given position
     */
    public void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
