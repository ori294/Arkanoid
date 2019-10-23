package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * CountdownAnimation: Shows a countdown from 3.
 * @author ori29
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private int millisecondsPerFrame;
    /**
     * CountdownAnimation constructor.
     * @param seconds the length of the countdown.
     * @param from count from x.
     * @param screen in order to show thw sceen while counting.
     */
    public CountdownAnimation(double seconds, int from, SpriteCollection screen) {
        this.numOfSeconds = seconds;
        this.countFrom = from;
        this.gameScreen = screen;
        this.millisecondsPerFrame = (int) (1000 * this.numOfSeconds / this.countFrom);
    }
    /**
     * doOneFrame: play one frame of the countdown.
     * @param d the Drawsurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sleeper = new Sleeper();

        if (this.countFrom <= 0) {
            this.gameScreen.drawAllOn(d);

            d.drawText(395, d.getHeight() / 2, "Go!", 64);

        } else if (this.countFrom > 0) {
            this.gameScreen.drawAllOn(d);

            d.drawText(395, d.getHeight() / 2, String.valueOf(this.countFrom), 64);

        }

        this.sleeper.sleepFor(this.millisecondsPerFrame);
        this.countFrom--;
    }

    /**
     * shouldStop: asks the method whether the countdown should stop.
     * @return a boolean value.
     */
    @Override
    public boolean shouldStop() {
        if (this.countFrom >= -1) {
            return false;
        }
        return true;
    }
}
