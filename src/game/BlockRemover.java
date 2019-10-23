package game;

import indicators.HitListener;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @author ori29
 *
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * BlockRemover constructor.
     * @param g the game.
     * @param removedBlocks a counter that holds the number of blocks.
     */
    public BlockRemover(Game g, Counter removedBlocks) {
        this.game = g;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * hitEvent:Blocks that are hit and reach 0 hit-points will be removed from the game.
     * @param beingHit the block that getting the hit.
     * @param hitter the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        beingHit.setHitPoints(beingHit.getHitPoints() - 1);

        if (beingHit.getHitPoints() < 1) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }

    }
}


