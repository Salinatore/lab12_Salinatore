package it.unibo.es2;

/**
 * Models a grid
 */
public interface Logics {
    /**
     * @return the true if now element is active
     */
    public Boolean hit(int row, int column);

    /**
     * @return true if the game is ended (a row o a column if full)
     */
    Boolean isFinished();
}
