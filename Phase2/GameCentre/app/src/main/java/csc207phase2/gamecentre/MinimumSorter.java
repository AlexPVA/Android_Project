package csc207phase2.gamecentre;

import java.util.List;
import java.util.Collections;
import java.lang.Comparable;

/**
 * Sorts Score where lowest is higher on the array list.
 */
public class MinimumSorter<T extends Comparable<Score>> implements ScoreSorter<Score> {

    @Override
    public void sort(List<Score> scores) {
        Collections.sort(scores, new ScorePointComparator());
    }

}
