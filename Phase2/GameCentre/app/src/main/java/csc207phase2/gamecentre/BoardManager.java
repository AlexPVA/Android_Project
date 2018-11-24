package csc207phase2.gamecentre;

import java.io.Serializable;

/**
 * Handles commonalities between all game managing classes.
 */
abstract class BoardManager implements Serializable {

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

    /**
     * Process a touch at the given position.
     *
     * @param position the position to process the touch at
     */
    abstract void touchMove(int position);

    /**
     * Returns true when the game's puzzle has been completed.
     *
     * @return whether the game's puzzle has been completed
     */
    abstract boolean puzzleSolved();

    /**
     * Return the name of the current user account.
     * @return the name of the current user account
     */
    abstract public String getAccountName();

    /**
     * Return the name of the board this manager is managing.
     * @return the board this manager is managing
     */
    abstract Board getBoard();

}
