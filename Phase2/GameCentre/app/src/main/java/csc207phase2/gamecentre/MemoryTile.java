package csc207phase2.gamecentre;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class MemoryTile implements Comparable<MemoryTile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * The unique id.
     */
    private int id;

    /**
     *  Whether this tile is flipped.
     */
    private boolean flipped = false;

    /**
     *  The picture of the front of the tile when flipped.
     */
    private int picture;

    /**
     *  Return the picture of the front of the tile when flipped.
     * @return the picture of the front of the tile when flipped.
     */
    public int getPicture() {return picture;}

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
     * Return if the tile is flipped.
     * @return whether the tile is flipped.
     */
    public boolean getFlipped() {return flipped;}

    /**
     * Set whether this tile is flipped or not.
     * @param flip whether this tile is set to flipped or not.
     */
    public void setFlipped(boolean flip) {
        this.flipped = flip;
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the background id
     */
    public MemoryTile(int backgroundId) {
        background = R.drawable.mg_tile_0;
        id = backgroundId + 1;
        switch (backgroundId + 1) {
            case 1:
                picture = R.drawable.mg_tile_1;
                break;
            case 2:
                picture = R.drawable.mg_tile_2;
                break;
            default:
                picture = R.drawable.mg_tile_0;
        }
    }

    void flipPicture() {
        background = picture;
    }

    void flipBlank(){
        background = R.drawable.mg_tile_0;
    }

    @Override
    public int compareTo(@NonNull MemoryTile o) {
        return o.id - this.id;
    }

    @Override
    public boolean equals(Object otherMemoryTile) {
        if (getClass() != otherMemoryTile.getClass()){
            return false;
        }
        return this.getPicture() == ((MemoryTile) otherMemoryTile).getPicture();
    }
}
