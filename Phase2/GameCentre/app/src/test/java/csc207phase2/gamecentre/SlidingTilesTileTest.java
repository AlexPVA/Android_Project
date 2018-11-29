package csc207phase2.gamecentre;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SlidingTilesTileTest {
    private SlidingTilesTile tile = new SlidingTilesTile(-1);
    private SlidingTilesTile tile2 = new SlidingTilesTile(0);
    private ArrayList<SlidingTilesTile> slidingTilesTileArrayList = new ArrayList<>();
    private ArrayList<Integer> drawableArrayList = new ArrayList<>();

    public void generateTilesList() {
        int count = 0;
        while (count < 25) {
            slidingTilesTileArrayList.add(new SlidingTilesTile(count));
            count += 1;
        }
    }

    public void generateMGPicturesList() {
        drawableArrayList.add(R.drawable.tile_1);
        drawableArrayList.add(R.drawable.tile_2);
        drawableArrayList.add(R.drawable.tile_3);
        drawableArrayList.add(R.drawable.tile_4);
        drawableArrayList.add(R.drawable.tile_5);
        drawableArrayList.add(R.drawable.tile_6);
        drawableArrayList.add(R.drawable.tile_7);
        drawableArrayList.add(R.drawable.tile_8);
        drawableArrayList.add(R.drawable.tile_9);
        drawableArrayList.add(R.drawable.tile_10);
        drawableArrayList.add(R.drawable.tile_11);
        drawableArrayList.add(R.drawable.tile_12);
        drawableArrayList.add(R.drawable.tile_13);
        drawableArrayList.add(R.drawable.tile_14);
        drawableArrayList.add(R.drawable.tile_15);
        drawableArrayList.add(R.drawable.tile_16);
        drawableArrayList.add(R.drawable.tile_17);
        drawableArrayList.add(R.drawable.tile_18);
        drawableArrayList.add(R.drawable.tile_19);
        drawableArrayList.add(R.drawable.tile_20);
        drawableArrayList.add(R.drawable.tile_21);
        drawableArrayList.add(R.drawable.tile_22);
        drawableArrayList.add(R.drawable.tile_23);
        drawableArrayList.add(R.drawable.tile_24);
        drawableArrayList.add(R.drawable.tile_25);
    }

    @Test
    public void getBackground() {
        generateTilesList();
        generateMGPicturesList();
        int count;
        for (count = 0; count <= 24; count++) {
            assertEquals(slidingTilesTileArrayList.get(count).getBackground(), drawableArrayList.get(count).intValue());
        }
        assertEquals(tile.getBackground(), R.drawable.tile_25);
    }

    @Test
    public void getId() {
        generateTilesList();
        int count;
        for (count = 0; count <= 24; count++) {
            assertEquals(slidingTilesTileArrayList.get(count).getId(), count+1);
        }
        assertEquals(tile.getId(), 0);
    }

    @Test
    public void compareTo() {
        assertEquals(tile.compareTo(tile2), 1);
    }
}