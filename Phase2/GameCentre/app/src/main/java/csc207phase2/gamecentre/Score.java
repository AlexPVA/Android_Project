package csc207phase2.gamecentre;

import android.support.annotation.NonNull;
import java.util.Comparator;
import java.lang.Comparable;

/**
 * The score of the game.
 */
public class Score implements Comparable<Score> {

    /**
     * The total points of the score.
     */
    private int scorePoint;

    /**
     * The User that has this score.
     */
    private String user;

    /**
     * The game type that this score is for.
     */
    private String gameType;

    /**
     * Returns this Score's score points.
     * @return this Score's score points.
     */
    public int getScorePoint() {return scorePoint; }

    /**
     * Return this Score's associated user.
     * @return this Score's associated user.
     */
    public String getUser() {return user;}

    /**
     * Return this Score's game type.
     *
     * @return this Score's game type.
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * A Score that has a user associated with it and scorePoint of 0 initially.
     * @param user The user that earned this score.
     * @param game The game that this score is for.
     */
    public Score(String user, String game) {
        this.user = user;
        this.gameType = game;
    }

    /**
     * Sets this Score's score points to newScore.
     * @param newScore a new score to be updated onto the Score
     */
    public void setScorePoint(int newScore){
        this.scorePoint = newScore;
    }

    @Override
    public boolean equals(Object otherScore) {
        if (this.getScorePoint() == ((Score) otherScore).getScorePoint()) {
            if (this.gameType.equals(((Score) otherScore).getGameType())) {
                return this.user.equals(((Score) otherScore).user);
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Return the name of the user, the point and the game for the score in string.
     * @return the name of the user, the point, and the game for the score in string.
     */
    @NonNull
    @Override
    public String toString(){
        return "  Name: " + this.user + "  Score: " + this.scorePoint + "  Game: " + this.gameType;
    }

    /**
     * Compares this Score with another Score.
     *
     * @param other The Score that is compared to this Score
     * @return A negative integer, zero, or positive integer
     *         if this grade is less than, equal to, or greater
     *         than other grade respectively, according to their
     *         scorePoint values.
     */
    @Override
    public int compareTo(Score other) {return Double.compare(this.scorePoint, other.scorePoint);}
}

/**
 * A comparator based on the score points, in descending order.
 */
class ScorePointComparator implements Comparator<Score> {

    /**
     * Compares the firstScore with second Score. Returns negative integer, zero, or positive
     * integer if this score is less than, equal to, or greater than other score respectively.
     *
     * @param firstScore The Score that is compared to secondScore
     * @param secondScore The score that is compared to the firstScore
     * @return A negative integer, zero, or positive integer
     *         if this score is less than, equal to, or greater
     *         than other score respectively, according to their
     *         scorePoint values.
     */
    @Override
    public int compare(Score firstScore, Score secondScore) {
        return firstScore.getScorePoint() - secondScore.getScorePoint();
    }


}