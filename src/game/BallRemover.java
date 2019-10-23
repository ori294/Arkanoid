package game;

import indicators.HitListener;

/**
 * BallRemover class: an HitListener which can remove a block from the game,
 * once it was hit.
 * @author ori29
 *
 */
public class BallRemover implements HitListener {

    private Game game;
    private Counter ballCount;
    /**
     * BallRemover constuctor.
     * @param g a game.
     * @param gameBalls counter that holds the number of active balls in the game.
     */
    public BallRemover(Game g, Counter gameBalls) {
        this.game = g;
        this.ballCount = gameBalls;
    }
    /**
     * hitEvent: removes the ball from the game.
     * @param beingHit the block that got hit.
     * @param hitter the ball that did the hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        hitter.removeFromGame(this.game);
        this.ballCount.decrease(1);
    }
}
