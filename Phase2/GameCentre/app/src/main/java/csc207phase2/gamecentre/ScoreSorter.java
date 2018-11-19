package csc207phase2.gamecentre;

import java.util.List;

public interface ScoreSorter<Score> {

    /**
     * Sorts the items in list in non-decreasing order.
     */
    void sort(List<Score> array);
}
