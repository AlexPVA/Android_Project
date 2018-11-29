package csc207phase2.gamecentre;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ScoreBoardTest {
    private Score score1 = new Score("A", "G1");
    private Score score2 = new Score("B", "G2");
    private Score score3 = new Score("C", "G3");
    private Score score4 = new Score("D", "G4");
    private List<Score> scoreList;
    private ScoreSorter<Score> scoreSorter = new MinimumSorter<>();
    private ScoreBoard scoreBoard = new ScoreBoard(scoreSorter, 3);

    @Test
    public void addScore() {
        //uses getSize because every time we add a score the scoreBoard size should increase until
        //we reach the limit thus it verifies if the score is being added or not.
        assertEquals(scoreBoard.getSize(), 0);
        scoreBoard.addScore(score1);
        assertEquals(scoreBoard.getSize(), 1);
        scoreBoard.addScore(score2);
        assertEquals(scoreBoard.getSize(), 2);
        scoreBoard.addScore(score3);
        assertEquals(scoreBoard.getSize(), 3);
        scoreBoard.addScore(score4);
        assertEquals(scoreBoard.getSize(), 3);
    }

    @Test
    public void getSize() {
        //use the same code as the above test because as we continually updates the size of the
        //scoredboard thus updates the size.
        assertEquals(scoreBoard.getSize(), 0);
        scoreBoard.addScore(score1);
        assertEquals(scoreBoard.getSize(), 1);
        scoreBoard.addScore(score2);
        assertEquals(scoreBoard.getSize(), 2);
        scoreBoard.addScore(score3);
        assertEquals(scoreBoard.getSize(), 3);
        scoreBoard.addScore(score4);
        assertEquals(scoreBoard.getSize(), 3);
    }

    @Test
    public void getListScores() {
        scoreList = new ArrayList<>(3);
        scoreList.add(score1);
        scoreList.add(score2);
        scoreList.add(score3);
        scoreBoard.addScore(score1);
        scoreBoard.addScore(score2);
        scoreBoard.addScore(score3);
        assertEquals(scoreBoard.getListScores(), scoreList);
    }

    @Test
    public void sortScores() {
        score1.setScorePoint(0);
        scoreBoard.addScore(score1);
        score2.setScorePoint(1);
        scoreBoard.addScore(score2);
        score3.setScorePoint(2);
        scoreBoard.addScore(score3);
        scoreBoard.sortScores();
        scoreList = new ArrayList<>(3);
        scoreList.add(score1);
        scoreList.add(score2);
        scoreList.add(score3);
        assertEquals(scoreBoard.getListScores(),scoreList);
    }

    @Test
    public void getUserHighscore() {
        scoreBoard.addScore(score3);
        scoreBoard.sortScores();
        assertEquals(scoreBoard.getUserHighscore("C"), score3);
        assertNull(scoreBoard.getUserHighscore("NotInScoreBoard"));
    }

    @Test
    public void getTopScore() {
        scoreBoard.addScore(score3);
        ArrayList<String> list = new ArrayList<String>();
        list.add("  Name: " + score3.getUser() + "  Score: " + score3.getScorePoint() + "  Game: " + score3.getGameType());
        assertEquals(scoreBoard.getTopScore(), list);
    }

    @Test
    public void iterator() {
        assertNotNull(scoreBoard.iterator());
    }
}