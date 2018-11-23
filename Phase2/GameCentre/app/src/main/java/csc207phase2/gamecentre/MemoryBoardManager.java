package csc207phase2.gamecentre;

import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

public class MemoryBoardManager extends BoardManager {

    transient private FirebaseAuth mAuth;
    transient private FirebaseUser currentUser;
    private String accountName;

    private MemoryBoard memoryBoard;
    //private MemoryBoard blankMemoryBoard;
    transient private GameComponent game;

    //private ArrayList<MemoryTile> flipped = new ArrayList<>();
    //private ArrayList<MemoryTile> solved = new ArrayList<>();

    private MemoryTile firstTile;
    private MemoryTile secondTile;

    public MemoryTile getFirstTile(){
        return firstTile;
    }

    public MemoryTile getSecondTile() {
        return secondTile;
    }

    public void setFirstTile(MemoryTile firstTile) {
        this.firstTile = firstTile;
    }

    public void setSecondTile(MemoryTile secondTile) {
        this.secondTile = secondTile;
    }

    private int storedPosition;

    public int getStoredPosition() {
        return storedPosition;
    }

    MemoryBoard getMemoryBoard() {
        return memoryBoard;
    }

    //MemoryBoard getBlankMemoryBoard() {return blankMemoryBoard;}

    MemoryBoardManager(int row, int col) {
        List<MemoryTile> memoryTiles = new ArrayList<>();
        //List<MemoryTile> blankMemoryTiles = new ArrayList<>();
        final int numTiles = (row * col) / 2;
        for (int tileNum = 0; tileNum < numTiles; tileNum++) {
            memoryTiles.add(new MemoryTile(tileNum));
            memoryTiles.add(new MemoryTile(tileNum));
            //blankMemoryTiles.add(new MemoryTile(-1));
            //blankMemoryTiles.add(new MemoryTile(-1));
        }

        Collections.shuffle(memoryTiles);
        this.memoryBoard = new MemoryBoard(row, col, memoryTiles);
        //this.blankMemoryBoard = new MemoryBoard(row, col, blankMemoryTiles);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        accountName = currentUser.getEmail();
    }

    boolean puzzleSolved() {
        boolean solved = true;

        Iterator<MemoryTile> iter = memoryBoard.iterator();

        while (iter.hasNext()) {
            if (!iter.next().getFlipped()) {
                solved = false;
            }
        }
        return solved;
            //if (this.solved.size() == memoryBoard.numTiles() && this.flipped.size() == memoryBoard.numTiles()) {
            //    return true;
            //} else {
            //    return false;
            //}

    }

    boolean isValidTap (int position) {

        int row = position / memoryBoard.getNumRows();
        int col = position % memoryBoard.getNumCols();

        MemoryTile current = memoryBoard.getTile(row, col);

        //for (MemoryTile m: flipped) {
        //    if (m == current || m == blankMemoryBoard.getTile(row, col)) {
        //        return false;
        //    }
        //}
        return !current.getFlipped();
    }

    void flipTile1(int position) {

        int row = position / memoryBoard.getNumRows();
        int col = position % memoryBoard.getNumCols();

        memoryBoard.flipTile(position);
        //MemoryTile current = blankMemoryBoard.getTile(row, col);
        //current.flipPicture(pictureTile.getBackground());
        //flipped.add(current);
        this.setFirstTile(memoryBoard.getTile(row, col));
        this.storedPosition = position;
    }

    void flipTile2(int position) {
        int row = position / memoryBoard.getNumRows();
        int col = position % memoryBoard.getNumCols();

        memoryBoard.flipTile(position);
        //MemoryTile pictureTile = memoryBoard.getTile(row, col);
        //MemoryTile current = blankMemoryBoard.getTile(row, col);
        //current.flipPicture(pictureTile.getBackground());
       // flipped.add(current);
        this.setSecondTile(memoryBoard.getTile(row, col));
    }

//    void removeFlipped(MemoryTile tile) {
//        MemoryTile toBeRemoved = null;
//        for (MemoryTile m: flipped) {
//            if (m.getBackground() == tile.getBackground()) {
//                toBeRemoved = m;
//            }
//        }
//        flipped.remove(toBeRemoved);
//    }

//    void addSolved(MemoryTile memoryTile) {
//        this.solved.add(memoryTile);
//    }

    /**
     * Autosaves the game manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void autoSave(String fileName) {
        game.saveToFile(fileName);
    }

    /**
     * Sets the game this is a part of.
     *
     * @param game the game object this is a part of
     */
    public void setGame(GameComponent game){
        this.game = game;
    }

    /**
     * Return the name of the current user account.
     * @return the name of the current user account.
     */
    public String getAccountName(){
        return accountName;
    }

    public void touchMove(int position){
        //updateTileButtons();
        if (getFirstTile() == null) {
            flipTile1(position);
            //updateTileButtons();
        } else if (getSecondTile() == null) {
            flipTile2(position);
            //updateTileButtons();
            if (getFirstTile().equals(getSecondTile())) {
                //addSolved(firstTile);
                //addSolved(secondTile);
                Toast.makeText(game.getApplicationContext(), "CORRECT PAIR!", Toast.LENGTH_SHORT).show();
                if (puzzleSolved()) {
                    Toast.makeText(game.getApplicationContext(), "YOU WIN!", Toast.LENGTH_SHORT).show();
                }
                else {
                    setFirstTile(null);
                    setSecondTile(null);
                }
            } else {
                final Handler handler = new Handler();
                final int finalPosition = position;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getMemoryBoard().flipTile(finalPosition);
                        getMemoryBoard().flipTile(getStoredPosition());
                        //removeFlipped(secondTile);
                        //firstTile.flipBlank();
                        //secondTile.flipBlank();
                        setFirstTile(null);
                        setSecondTile(null);
                        Toast.makeText(game.getApplicationContext(), "WRONG PAIR!", Toast.LENGTH_SHORT).show();
                        //updateTileButtons();
                    }
                }, 500);
            }
        }
// else {
//                flipTile1(position);
//                secondTile = null;
//                updateTileButtons();
//            }


    }
}
