package csc207phase2.gamecentre;

import android.content.Context;
import android.widget.Toast;

public class MemoryMovementController {

    private MemoryBoardManager memoryBoardManager = null;

    public MemoryMovementController() {}

    public void setMemoryBoardManager(MemoryBoardManager memoryBoardManager) {
        this.memoryBoardManager = memoryBoardManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (memoryBoardManager.isValidTap(position)) {
            if (MemoryBoardManager.firstTile == null) {
                memoryBoardManager.flipTile1(position);
            } else if (MemoryBoardManager.secondTile == null) {
                memoryBoardManager.flipTile2(position);
                if (memoryBoardManager.matchingTile(MemoryBoardManager.firstTile, MemoryBoardManager.secondTile)) {
                    memoryBoardManager.addSolved(MemoryBoardManager.firstTile);
                    memoryBoardManager.addSolved(MemoryBoardManager.secondTile);
                    if (memoryBoardManager.puzzleSolved()) {
                        Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    memoryBoardManager.removeFlipped(MemoryBoardManager.firstTile);
                    memoryBoardManager.removeFlipped(MemoryBoardManager.secondTile);
                    MemoryBoardManager.firstTile.flipBlank();
                    MemoryBoardManager.secondTile.flipBlank();
                }
            } else {
                memoryBoardManager.flipTile1(position);
                MemoryBoardManager.secondTile = null;
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
