package csc207phase2.gamecentre;

import android.content.Context;
import android.widget.Button;

import java.io.Serializable;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class MinesweeperTile implements Comparable<MinesweeperTile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * The unique id.
     */
    private int id;

    /**
     * If the tile has been tapped
     */
    private boolean tapped;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of a tile
     *
     * @param i the new id
     */

    public void setId(int i) { id = i; }

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    public MinesweeperTile(int id, int background) {
        this.id = id;
        this.background = background;
    }

    /**
     *
     * @return if the tile has been tapped
     */
    public boolean isTapped() { return tapped; }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the background id
     */
    public MinesweeperTile(int backgroundId) {
        //boolean to show that its been tapped or not, if not show blank
        if (!tapped) {
            background = R.drawable.tile_10;
        }
        //if tapped show id
        id = backgroundId + 1;
        // This looks so ugly.
        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.tile_1;
                break;
            case 2:
                background = R.drawable.tile_2;
                break;
            case 3:
                background = R.drawable.tile_3;
                break;
            case 4:
                background = R.drawable.tile_4;
                break;
            case 5:
                background = R.drawable.tile_5;
                break;
            case 6:
                background = R.drawable.tile_6;
                break;
            case 7:
                background = R.drawable.tile_7;
                break;
            case 8:
                background = R.drawable.tile_8;
                break;
            case 9:
                background = R.drawable.tile_9;
                break;
            case 10:
                background = R.drawable.tile_10;
                break;
            default:
                background = R.drawable.tile_10;
        }
    }

    @Override
    public int compareTo(MinesweeperTile o) {
        return o.id - this.id;
    }

    public void setTapped(boolean tapped){
        this.tapped = tapped;
    }

}
