package csc207phase2.gamecentre;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;

public class MemoryMovementController {

    private MemoryBoardManager memoryBoardManager = null;

    public MemoryMovementController() {
    }

    public void setMemoryBoardManager(MemoryBoardManager memoryBoardManager) {
        this.memoryBoardManager = memoryBoardManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (memoryBoardManager.isValidTap(position)) {
            updateTileButtons();
            if (MemoryBoardManager.firstTile == null) {
                memoryBoardManager.flipTile1(position);
                updateTileButtons();
            } else if (MemoryBoardManager.secondTile == null) {
                memoryBoardManager.flipTile2(position);
                updateTileButtons();
                    if (memoryBoardManager.matchingTile(MemoryBoardManager.firstTile, MemoryBoardManager.secondTile)) {
                        memoryBoardManager.addSolved(MemoryBoardManager.firstTile);
                        memoryBoardManager.addSolved(MemoryBoardManager.secondTile);
                        Toast.makeText(context, "CORRECT PAIR!", Toast.LENGTH_SHORT).show();
                        if (memoryBoardManager.puzzleSolved()) {
                            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        memoryBoardManager.removeFlipped(MemoryBoardManager.firstTile);
                        memoryBoardManager.removeFlipped(MemoryBoardManager.secondTile);
                        MemoryBoardManager.firstTile.flipBlank();
                        MemoryBoardManager.secondTile.flipBlank();
//                        MemoryBoardManager.firstTile = null;
//                        MemoryBoardManager.secondTile = null;
                        Toast.makeText(context, "WRONG PAIR!", Toast.LENGTH_SHORT).show();
//                        updateTileButtons();
                    }
            } else {
                memoryBoardManager.flipTile1(position);
                MemoryBoardManager.secondTile = null;
                updateTileButtons();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTileButtons() {
        MemoryBoard memoryBoard = memoryBoardManager.getMemoryBoard();
        MemoryBoard blankMemoryBoard = memoryBoardManager.getBlankMemoryBoard();
        int nextPos = 0;
        for (Button b : MemoryGameActivity.tileButtons) {
            int row = nextPos / memoryBoard.getNumRows();
            int col = nextPos % memoryBoard.getNumCols();
            b.setBackgroundResource(blankMemoryBoard.getTile(row, col).getBackground());
            nextPos++;
        }
    }
}