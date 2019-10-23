package game;

import java.util.List;

/**
 * LevelInformation interface: holds the entire info needed to setup a level.
 * @author ori29
 *
 */
public interface LevelInformation {

    /**
     * numberOfBalls.
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities.
     * @return a list contains the various ball velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed.
     * @return returns the paddle speed.
     */
    int paddleSpeed();

    /**
     * paddleWidth.
     * @return reutrns the paddleWidth.
     */
    int paddleWidth();

    /**
     * levelName.
     * @return returns the level name.
     */
    String levelName();

    /**
     * getBackground: sets the backround and then return it.
     * @return the background.
     */
    Background getBackground();

    /**
     * blocks.
     * @return returns a list of blocks.
     */
    List<Block> blocks();

    /**
     * numberOfBlocksToRemove.
     * @return returns the number of blocks that should be removed for clearing the level.
     */
    int numberOfBlocksToRemove();

    /**
     * ballColor.
     * @return returns the ball color of the level.
     */
    java.awt.Color ballColor();
}
