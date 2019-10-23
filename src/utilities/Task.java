package utilities;

/**
 * Task an executeable task.
 * @author ori29
 * @param <T> Task of any kind (PlayGame, Show another Menu, Show highscores and more).
 */
public interface Task<T> {
    /**
     * runs the task.
     * @return return T (could be anything, mostly Void).
     */
    T run();
}
