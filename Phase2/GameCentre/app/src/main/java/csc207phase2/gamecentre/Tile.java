package csc207phase2.gamecentre;

/**
 * Parent class for all tiles.
 */
abstract class Tile {

    /**
     * Returns the ID of the tile (not necessarily unique)
     *
     * @return the ID of the tile
     */
    abstract int getId();

    /**
     * Returns the background of the tile.
     *
     * @return the background of the tile
     */
    abstract int getBackground();

}
