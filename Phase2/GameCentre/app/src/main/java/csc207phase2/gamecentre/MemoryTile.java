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
     * A MemoryTile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    public MemoryTile(int id, int background) {
        this.id = id;
        this.background = background;
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId
     */
    public MemoryTile(int backgroundId) {
        id = backgroundId + 1;
        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.mg_tile_1;
                break;
            case 2:
                background = R.drawable.mg_tile_2;
                break;
            default:
                background = R.drawable.mg_tile_0;
        }
    }

    void flipPicture(int background_id) {
        background = background_id;
    }

    void flipBlank(){
        background = R.drawable.mg_tile_0;
    }

    @Override
    public int compareTo(@NonNull MemoryTile o) {
        return o.id - this.id;
    }
}
