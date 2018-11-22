package csc207phase2.gamecentre;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;
import android.os.Handler;

public class MemoryMovementController {

    private MemoryBoardManager memoryBoardManager = null;

    public MemoryMovementController() {
    }

    public void setMemoryBoardManager(MemoryBoardManager memoryBoardManager) {
        this.memoryBoardManager = memoryBoardManager;
    }

    public void processTapMovement(final Context context, final int position, boolean display) {
        if (memoryBoardManager.isValidTap(position)) {
            //updateTileButtons();
            if (memoryBoardManager.getFirstTile() == null) {
                memoryBoardManager.flipTile1(position);
                //updateTileButtons();
            } else if (memoryBoardManager.getSecondTile() == null) {
                memoryBoardManager.flipTile2(position);
                //updateTileButtons();
                    if (memoryBoardManager.getFirstTile().equals(memoryBoardManager.getSecondTile())) {
                        //memoryBoardManager.addSolved(MemoryBoardManager.firstTile);
                        //memoryBoardManager.addSolved(MemoryBoardManager.secondTile);
                        Toast.makeText(context, "CORRECT PAIR!", Toast.LENGTH_SHORT).show();
                        if (memoryBoardManager.puzzleSolved()) {
                            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            memoryBoardManager.setFirstTile(null);
                            memoryBoardManager.setSecondTile(null);
                        }
                    } else {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                memoryBoardManager.getMemoryBoard().flipTile(position);
                                memoryBoardManager.getMemoryBoard().flipTile(memoryBoardManager.getStoredPosition());
                                                    //memoryBoardManager.removeFlipped(MemoryBoardManager.secondTile);
                                                    //MemoryBoardManager.firstTile.flipBlank();
                                                    //MemoryBoardManager.secondTile.flipBlank();
                                memoryBoardManager.setFirstTile(null);
                                memoryBoardManager.setSecondTile(null);
                                Toast.makeText(context, "WRONG PAIR!", Toast.LENGTH_SHORT).show();
                                                    //updateTileButtons();
                            }
                        }, 500);
                    }
            }
// else {
//                memoryBoardManager.flipTile1(position);
//                MemoryBoardManager.secondTile = null;
//                updateTileButtons();
//            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }

//    private void updateTileButtons() {
//        MemoryBoard memoryBoard = memoryBoardManager.getMemoryBoard();
//        MemoryBoard blankMemoryBoard = memoryBoardManager.getBlankMemoryBoard();
//        int nextPos = 0;
//        for (Button b : MemoryGameActivity.tileButtons) {
//            int row = nextPos / memoryBoard.getNumRows();
//            int col = nextPos % memoryBoard.getNumCols();
//            b.setBackgroundResource(blankMemoryBoard.getTile(row, col).getBackground());
//            nextPos++;
//        }
//    }
}