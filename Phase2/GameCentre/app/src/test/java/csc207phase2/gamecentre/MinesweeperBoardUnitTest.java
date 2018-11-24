package csc207phase2.gamecentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinesweeperBoardUnitTest {
    @Test
    public void generateBoardEasy_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(9, 9);
        assertEquals(board.numBombs(), 10);
    }
    public void generateBoardMedium_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(16, 16);
        assertEquals(board.numBombs(), 40);
    }
    public void generateBoardHard_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(20, 20);
        assertEquals(board.numBombs(), 60);
    }
    public void tapTile_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(9, 9);
        board.tapTile(1,1);
        assertEquals(board.getTile(1,1).getBackground() != unclicked.tile);
        //not sure if this is the right way to check this
    }
    public void getNumRows_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(9, 9);
        asserEquals(board.getNumRows(), 9);
    }
    public void getNumCols_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(9, 9);
        asserEquals(board.getNumCols(), 9);
    }
}