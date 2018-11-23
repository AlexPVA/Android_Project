package csc207phase2.gamecentre;


import java.util.Observable;

abstract class Board extends Observable{

    abstract int getNumRows();

    abstract int getNumCols();

    abstract Tile getTile(int x, int y);

}
