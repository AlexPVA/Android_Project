package csc207phase2.gamecentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinesweeperBoardUnitTest {
    @Test
    public void generateBoardEasy_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(9, 9);
        assertEquals(board.numBombs(), 10);
    }
    @Test
    public void generateBoardMedium_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(16, 16);
        assertEquals(board.numBombs(), 40);
    }
    @Test
    public void generateBoardHard_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(20, 20);
        assertEquals(board.numBombs(), 60);
    }
    @Test
    public void tapTile_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(9, 9);
        board.tapTile(1,1);
        assertEquals(board.getTile(1,1).getBackground() != MinesweeperTile.unclicked_tile);
    }
    @Test
    public void getNumRows_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(9, 9);
        assertEquals(board.getNumRows(), 9);
    }
    @Test
    public void getNumCols_isCorrect() {
        MinesweeperBoard board = new MinesweeperBoard(9, 9);
        assertEquals(board.getNumCols(), 9);
    }
}