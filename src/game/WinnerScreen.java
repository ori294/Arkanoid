package game;

import biuoop.DrawSurface;

/**
 * WinnerScreen: shows the winning screen when winning.
 * @author ori29
 *
 */
public class WinnerScreen implements Animation {

    private int score;

    /**
     * WinnerScreen constructor.
     * @param s Score counter.
     */
    public WinnerScreen(Counter s) {
        this.score = s.getValue();
    }

    /**
     * shows the next frame.
     * @param d DrawSurface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(180, d.getHeight() / 2, "You win! Your score is: " + this.score, 32);
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
