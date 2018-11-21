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
}
