package game;

import biuoop.DrawSurface;

/**
 * PasueScreen: shows a pause sceen.
 * @author ori29
 *
 */
public class PauseScreen implements Animation {

    /**
     * doOneFrame: shows the next frame.
     * @param d DrawSurface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(180, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * shouldStop: returns false.
     * @return boolean value - false.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

}
