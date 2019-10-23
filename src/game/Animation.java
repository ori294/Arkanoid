package game;

import biuoop.DrawSurface;

/**
 * Animation interface.
 *
 * @author ori29
 *
 */
public interface Animation {

    /**
     * doOneFrame: run one frame of the animation.
     * @param d a DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop: asks if the animation should stop.
     * @return a boolean value.
     */
    boolean shouldStop();

}
