package csc207phase2.gamecentre;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.List;

/**
 * A score board for a game.
 */
public class ScoreBoard<T> implements Iterable<Score> {

    /**
     * This is the list of Scores within the score board.
     */
    private List<Score> scores;

    /**
     * The sorter for this scoreboard.
     */
    private ScoreSorter<Score> sorter;

    /**
     * Constructs a new ScoreBoard
     */
    public ScoreBoard(ScoreSorter<Score> sorter) {
        this.sorter = sorter;
        this.scores = new ArrayList<>();
    }

    /**
     * Adds score to scoreboard.
     * @param score the score being added to the scoreboard.
     */
    public void addScore(Score score) {
        Iterator<Score> iter = scores.iterator();
        while (iter.hasNext()) {
            if (score == iter.next()) {
                break;
            }
        }
        scores.add(score);
        this.sortScores();
    }

    /**
     * Get the size of the scoreboard.
     * @return the size of the scoreboard.
     */
    public int getSize() {return scores.size();}

    /**
     * Get the array list of the score board.
     * @return the array list of the score board.
     */
    public List getListScores() { return scores; }

    /**
     * Sort the scores in the specified sorter algorithm.
     */
    public void sortScores() {sorter.sort(scores);}

    /**
     * Get the high score of the user.
     * @param username the user that the score is for
     * @return Score of the user's high score, if there are none return null.
     */
    public Score getUserHighscore(String username) {
        this.sortScores();
        Iterator<Score> iter = scores.iterator();
        while (iter.hasNext()) {
            Score score = iter.next();
            if (username.equals(score.getUser())) {
                return iter.next();
                }
            }
        return null;
    }

    /**
     * Get the top 8 scores of the given game type.
     * @return scoreList an array list of the top 8 scores for the game.
     */
    public ArrayList<String> getTopScore() {
        ArrayList<String> list = new ArrayList<String>();
        this.sortScores();
        Iterator<Score> iter = scores.iterator();
        while (iter.hasNext()) {
                Score curr = iter.next();
                list.add(curr.toString());
        }
        return list;
    }

    @NonNull
    @Override
    public Iterator<Score> iterator() {
        return new ScoreBoardIterator();
    }

    /**
     * Iterates through the Tiles in the Board in row-major order.
     */
    private class ScoreBoardIterator implements Iterator<Score> {

        /**
         * The index of the next Score to return.
         */
        private int current = 0;

        /**
         * Returns whether there is another Score to return.
         * @return whether there is another Score to return.
         */
        @Override
        public boolean hasNext() {
            return current < scores.size();
        }

        /**
         * Returns the next Score.
         * @return the next Score.
         */
        @Override
        public Score next() {
            Score result;
            try {
                result = scores.get(current);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            current += 1;
            return result;
        }
    }
}
