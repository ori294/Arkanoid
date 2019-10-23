package game;

import biuoop.DrawSurface;

/**
 * GameOverScreen: shows the GameOver screen and the player score.
 * @author ori29
 */
public class GameOverScreen implements Animation {

    private int score;

    /**
     * GameOverScreen constructor.
     * @param s the Score counter.
     */
    public GameOverScreen(Counter s) {
        this.score = s.getValue();
    }

    /**
     * doOneFrame: Shows the GameOver text.
     * @param d a DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(180, d.getHeight() / 2, "Game Over. Your score is: " + this.score, 32);
    }

    /**
     * @return false.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}