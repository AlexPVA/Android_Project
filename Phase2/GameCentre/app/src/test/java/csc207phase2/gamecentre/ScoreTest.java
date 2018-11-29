package csc207phase2.gamecentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {
    private String user = "test";
    private String game = "game";
    private Score score = new Score(user, game);
    private Score score2 = new Score(user, game);
    private Score score3 = new Score(user, game);
    private Object score4 = new Object();

    @Test
    public void getScorePoint() {
        assertEquals(score.getScorePoint(), 0);
        score.setScorePoint(100);
        assertEquals(score.getScorePoint(), 100);
    }

    @Test
    public void getUser() {
        assertEquals(score.getUser(), user);
    }

    @Test
    public void getGameType() {
        assertEquals(score.getGameType(), game);
    }

    @Test
    public void setScorePoint() {
        score.setScorePoint(1212);
        assertEquals(score.getScorePoint(), 1212);
    }

    @Test
    public void equals() {
        score3.setScorePoint(100);
        assertTrue(score.equals(score2));
        assertFalse(score.equals(score3));
    }

    @Test
    public void compareTo() {
        assertEquals(score.compareTo(score2), 0);
        score2.setScorePoint(100);
        assertEquals(score.compareTo(score2), -1);
        score.setScorePoint(200);
        assertEquals(score.compareTo(score2), 1);
    }
}