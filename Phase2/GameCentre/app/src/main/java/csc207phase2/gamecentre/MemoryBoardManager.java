package csc207phase2.gamecentre;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

public class MemoryBoardManager extends GameManager{

    transient private FirebaseAuth mAuth;
    transient private FirebaseUser currentUser;
    private String accountName;

    private MemoryBoard memoryBoard;
    private MemoryBoard blankMemoryBoard;
    transient private GameComponent game;

    private ArrayList<MemoryTile> flipped = new ArrayList<>();
    private ArrayList<MemoryTile> solved = new ArrayList<>();

    public static MemoryTile firstTile;
    public static MemoryTile secondTile;

    MemoryBoard getMemoryBoard() {
        return memoryBoard;
    }

    MemoryBoardManager(int row, int col) {
        List<MemoryTile> memoryTiles = new ArrayList<>();
        List<MemoryTile> blankMemoryTiles = new ArrayList<>();
        final int numTiles = (row * col) / 2;
        for (int tileNum = 0; tileNum < numTiles; tileNum++) {
            memoryTiles.add(new MemoryTile(tileNum));
            memoryTiles.add(new MemoryTile(tileNum));
            blankMemoryTiles.add(new MemoryTile(999));
            blankMemoryTiles.add(new MemoryTile(999));
        }

        Collections.shuffle(memoryTiles);
        this.memoryBoard = new MemoryBoard(row, col, memoryTiles);
        this.blankMemoryBoard = new MemoryBoard(row, col, blankMemoryTiles);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        accountName = currentUser.getEmail();
    }

    boolean puzzleSolved() {
        boolean solved = true;
        if (this.solved.size() == memoryBoard.numTiles()) {
            return true;
        } else {
            return false;
        }
    }

    boolean matchingTile(MemoryTile tile1, MemoryTile tile2) {
        if (tile1.getBackground() == tile2.getBackground()) {
            return true;
        } else {
            return false;
        }
    }

    boolean isValidTap(int position) {

        int row = position / memoryBoard.getNumCols();
        int col = position % memoryBoard.getNumCols();

        MemoryTile current = memoryBoard.getTile(row, col);
        int currentId = current.getId();

        for (MemoryTile m: flipped) {
            if (m.getId() == currentId) {
                return false;
            }
        }
        return true;
    }

    void flipTile1(int position) {

        int row = position / memoryBoard.getNumCols();
        int col = position % memoryBoard.getNumCols();

        MemoryTile pictureTile = memoryBoard.getTile(row, col);
        MemoryTile current = blankMemoryBoard.getTile(row, col);
        current.flipPicture(pictureTile.getBackground());
        flipped.add(current);
        firstTile = current;
    }

    void flipTile2(int position) {
        int row = position / memoryBoard.getNumCols();
        int col = position % memoryBoard.getNumCols();

        MemoryTile pictureTile = memoryBoard.getTile(row, col);
        MemoryTile current = blankMemoryBoard.getTile(row, col);
        current.flipPicture(pictureTile.getBackground());
        flipped.add(current);
        secondTile = current;
    }

    void removeFlipped(MemoryTile tile) {
        flipped.remove(tile);
    }

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

}
