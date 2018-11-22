package csc207phase2.gamecentre;

import android.content.Context;
import android.widget.Button;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class MinesweeperTile implements Comparable<MinesweeperTile>, Serializable, Observer {

    /**
     * The background id to find the tile image.
     */
    private int background = R.drawable.mine_exploded;

    /**
     * Id representing the type of tile (not unique)
     */
    private int id;

    /**
     * If the tile has been tapped
     */
    private boolean tapped = false;

    /**
     * The ID of a bomb.
     */
    static final int BOMB_ID = 9;

    /**
     * The ID of an exploded bomb.
     */
    static final int EXPLODED_BOMB_ID = 10;

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
     *
     * @return if the tile has been tapped
     */
    public boolean isTapped() { return tapped; }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param id the id
     */
    public MinesweeperTile(int id) {
        this.id = id;
        updateBackground(id);
    }

    private void updateBackground(int id){
        if (!tapped) {
            background = R.drawable.unclicked_tile;
            return;
        }

        switch (id) {
            case 0:
                background = R.drawable.blank;
                break;
            case 1:
                background = R.drawable.ms_tile_1;
                break;
            case 2:
                background = R.drawable.ms_tile_2;
                break;
            case 3:
                background = R.drawable.ms_tile_3;
                break;
            case 4:
                background = R.drawable.ms_tile_4;
                break;
            case 5:
                background = R.drawable.ms_tile_5;
                break;
            case 6:
                background = R.drawable.ms_tile_6;
                break;
            case 7:
                background = R.drawable.ms_tile_7;
                break;
            case 8:
                background = R.drawable.ms_tile_8;
                break;
            case 9:
                background = R.drawable.mine_unexploded;
                break;
            default:
                background = R.drawable.mine_exploded;
        }
    }

    @Override
    public int compareTo(MinesweeperTile o) {
        return o.id - this.id;
    }

    public void setTapped(boolean tapped){
        this.tapped = tapped;
        updateBackground(id);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateBackground(id);
    }
}
