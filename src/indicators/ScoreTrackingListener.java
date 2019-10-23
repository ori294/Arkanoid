package indicators;

import game.Ball;
import game.Block;
import game.Counter;

/**
 * ScoreTrackingListener: A listener that will help to keep track of the score.
 *
 * @author ori29
 *
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * ScoreTrackingListener constructor.
     *
     * @param scoreCounter the counter that will count the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent: Whenever a block is being hit, increase the score.
     * @param beingHit the block that got hit.
     * @param hitter the ball that does the hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        if (beingHit.getHitPoints() > 0) {
            this.currentScore.increase(5);
        } else {
            this.currentScore.increase(10);
        }
    }
}
