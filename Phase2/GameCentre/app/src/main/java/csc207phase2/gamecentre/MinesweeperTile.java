package csc207phase2.gamecentre;

import java.io.Serializable;

/**
 * A SlidingTilesTile in the minesweeper game.
 */
public class MinesweeperTile implements Serializable{

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
        updateBackground();
    }

    /**
     * Updates the tile's background.
     */
    private void updateBackground(){
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

    /**
     * Set whether the tile has been tapped, then update the background accordingly.
     *
     * @param tapped represents whether the tile has been tapped.
     */
    public void setTapped(boolean tapped){
        this.tapped = tapped;
        updateBackground();
    }

}
