package csc207phase2.gamecentre;

import java.util.List;

public interface ScoreSorter<Score> {

    /**
     * Sorts the items in the list depending on the sorter.
     */
    void sort(List<Score> array);
}
