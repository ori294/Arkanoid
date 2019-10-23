package indicators;

import game.Ball;
import game.Block;

/**
 * HitListener interface: registered to other objects as a listener.
 * helps to detect ball hits, ball deaths and more.
 * @author ori29
 *
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block that got the hit.
     * @param hitter the Ball that's doing the hitting
     */
       void hitEvent(Block beingHit, Ball hitter);
}
