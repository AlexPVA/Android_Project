package csc207phase2.gamecentre;

import android.support.v7.app.AppCompatActivity;
import java.io.Serializable;

/**
 * Handles commonalities between all game managing classes.
 */
abstract class GameManager extends AppCompatActivity implements Serializable {

    /**
     * Autosaves the game manager to fileName.
     *
     * @param fileName the name of the file
     */
    abstract void autoSave(String fileName);

    /**
     * Sets the game this is a part of.
     *
     * @param game the game object this is a part of
     */
    abstract public void setGame(GameComponent game);

    /**
     * Checks the validity of a tap at a given position.
     *
     * @param position the position to check
     */
    abstract boolean isValidTap(int position);

    void touchMove(int position){

    }

    /**
     * Returns true when the game's puzzle has been completed.
     *
     * @return whether the game's puzzle has been completed
     */
    abstract boolean puzzleSolved();



}
