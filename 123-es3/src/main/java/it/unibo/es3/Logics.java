package it.unibo.es3;

import java.util.Set;

public interface Logics {

    /**
     * @return set of active points
     */
    Set<Pair<Integer, Integer>> activePointsSet();

    /**
     * Iterates.
     */
    void doIteration();

    /**
     * @return true if full
     */
    Boolean isFinished();
}
