package csc207phase2.gamecentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryTileTest {
    //Should coverage include all the switch statements?
    MemoryTile tile = new MemoryTile(-999);
    MemoryTile tile2 = new MemoryTile(17);
    MemoryTile tile3 = new MemoryTile(17);
    @Test
    public void getPicture() {
        assertEquals(tile.getPicture(), R.drawable.mg_tile_0); //Note should a tile always have a picture or should we allow a blank image?
        assertEquals(tile2.getPicture(), R.drawable.mg_tile_18);
    }

    @Test
    public void getBackground() {
        assertEquals(tile.getBackground(), R.drawable.mg_tile_0);
        assertEquals(tile2.getBackground(), R.drawable.mg_tile_0);
    }

    @Test
    public void getId() {
        assertEquals(tile.getId(), -998);
        assertEquals(tile2.getId(), 18);

    }

    @Test
    public void getFlipped() {
        assertFalse(tile2.getFlipped());
        tile2.setFlipped(true);
        assertTrue(tile2.getFlipped());
        tile2.setFlipped(false);
    }

    @Test
    public void setFlipped() {
        tile.setFlipped(true);
        assertTrue(tile.getFlipped());
        tile.setFlipped(false);
        assertFalse(tile.getFlipped());
    }

    @Test
    public void flipPicture() {
        tile2.flipPicture();
        assertEquals(tile2.getBackground(), tile2.getPicture());
    }

    @Test
    public void flipBlank() {
        tile2.flipBlank();
        assertEquals(tile2.getBackground(), R.drawable.mg_tile_0);
    }

    @Test
    public void compareTo() {
        assertEquals(tile.compareTo(tile2), 1016);
    }

    @Test
    public void equals() {
        assertTrue(tile2.equals(tile3));
    }
}