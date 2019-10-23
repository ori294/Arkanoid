package blocksreader;

import game.Block;
/**
 * Block creator interface.
 *
 * @author ori29
 */
public interface BlockCreator {
    /**
     * Create a block given X and Y position.
     * @param xpos x position.
     * @param ypos y position.
     * @return Block.
     */
    Block create(int xpos, int ypos);
    /**
     * return the Width of the block.
     * @return int - width.
     */
    int getWidth();
}