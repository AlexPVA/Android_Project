package csc207phase2.gamecentre;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MemoryTileTest {

    private MemoryTile tile = new MemoryTile(-1);
    private MemoryTile tile2 = new MemoryTile(0);
    private Object stuff = new Object();
    private ArrayList<MemoryTile> memoryTileArrayList = new ArrayList<>();
    private ArrayList<Integer> drawableArrayList = new ArrayList<>();

    public void generateTilesList() {
        int count = 0;
        while (count < 18) {
            memoryTileArrayList.add(new MemoryTile(count));
            count += 1;
        }
    }

    public void generateMGPicturesList() {
        drawableArrayList.add(R.drawable.mg_tile_1);
        drawableArrayList.add(R.drawable.mg_tile_2);
        drawableArrayList.add(R.drawable.mg_tile_3);
        drawableArrayList.add(R.drawable.mg_tile_4);
        drawableArrayList.add(R.drawable.mg_tile_5);
        drawableArrayList.add(R.drawable.mg_tile_6);
        drawableArrayList.add(R.drawable.mg_tile_7);
        drawableArrayList.add(R.drawable.mg_tile_8);
        drawableArrayList.add(R.drawable.mg_tile_9);
        drawableArrayList.add(R.drawable.mg_tile_10);
        drawableArrayList.add(R.drawable.mg_tile_11);
        drawableArrayList.add(R.drawable.mg_tile_12);
        drawableArrayList.add(R.drawable.mg_tile_13);
        drawableArrayList.add(R.drawable.mg_tile_14);
        drawableArrayList.add(R.drawable.mg_tile_15);
        drawableArrayList.add(R.drawable.mg_tile_16);
        drawableArrayList.add(R.drawable.mg_tile_17);
        drawableArrayList.add(R.drawable.mg_tile_18);
    }

    @Test
    public void getPicture() {
        generateTilesList();
        generateMGPicturesList();
        int count;
        for (count = 0; count <= 17; count++) {
            assertEquals(memoryTileArrayList.get(count).getPicture(), drawableArrayList.get(count).intValue());
        }
        assertEquals(tile.getPicture(), R.drawable.mg_tile_0);
    }

    @Test
    public void getBackground() {
        generateTilesList();
        int count;
        for (count = 0; count <= 17; count++) {
            assertEquals(memoryTileArrayList.get(count).getBackground(), R.drawable.mg_tile_0);
        }
        assertEquals(tile.getBackground(), R.drawable.mg_tile_0);
    }

    @Test
    public void getId() {
        generateTilesList();
        int count;
        for (count = 0; count <= 17; count++) {
            assertEquals(memoryTileArrayList.get(count).getId(), count+1);
        }

    }

    @Test
    public void getFlipped() {
        generateTilesList();
        int count;
        for (count = 0; count <= 17; count++) {
            assertFalse(memoryTileArrayList.get(count).getFlipped());
            memoryTileArrayList.get(count).setFlipped(true);
            assertTrue(memoryTileArrayList.get(count).getFlipped());
        }
    }

    @Test
    public void setFlipped() {
        generateTilesList();
        int count;
        for (count = 0; count <= 17; count++) {
            memoryTileArrayList.get(count).setFlipped(true);
            assertTrue(memoryTileArrayList.get(count).getFlipped());
            memoryTileArrayList.get(count).setFlipped(false);
            assertFalse(memoryTileArrayList.get(count).getFlipped());
        }
    }

    @Test
    public void flipPicture() {
        generateTilesList();
        int count;
        for (count = 0; count <= 17; count++) {
            memoryTileArrayList.get(count).flipPicture();
            assertEquals(memoryTileArrayList.get(count).getBackground(), memoryTileArrayList.get(count).getPicture());
        }
    }

    @Test
    public void flipBlank() {
        generateTilesList();
        int count;
        for (count = 0; count <= 17; count++) {
            memoryTileArrayList.get(count).flipBlank();
            assertEquals(memoryTileArrayList.get(count).getBackground(), R.drawable.mg_tile_0);
        }
    }

    @Test
    public void compareTo() {
        assertEquals(tile.compareTo(tile2), 1);
    }

    @Test
    public void equals() {
        assertFalse(tile2.equals(tile));
        assertFalse(tile2.equals(stuff));
    }
}