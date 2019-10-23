package blocksreader;

import java.util.HashMap;
import java.util.Map;

import game.Block;

/**
 * BlocksFromSymbolsFactory: a factory that hold a map. will create blocks according
 * to the block symbols (and also spacers).
 * @author ori29
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * BlocksFromSymbolsFactory constructor: inits the maps.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<String, Integer>();
        this.blockCreators = new HashMap<String, BlockCreator>();
    }

    /**
     * addSpacer: adds a spacer to the spacers map.
     * @param s a string (the symbol).
     * @param i and Inetger, the space width.
     */
    public void addSpacer(String s, Integer i) {
        this.spacerWidths.put(s, i);
    }
    /**
     * addBlockCreator: adds a BlockCreator to the BlockCreators map.
     * @param s a string (the symbol).
     * @param bc a BlockCreator.
     */
    public void addBlockCreator(String s, BlockCreator bc) {
        this.blockCreators.put(s, bc);
    }

    /**
     * getSpaceWidth: given a spacer, return it's width.
     * @param s a string (the symbol).
     * @return the spacer width.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * getBlock given a symbol, create a block and return it.
     * @param s a string (the symbol).
     * @param x the X position of the creation.
     * @param y the Y position of the creation.
     * @return a Block.
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }
    /**
     * getBlockWidth: returns the block width.
     * @param s a string (the symbol).
     * @return the desired block width.
     */
    public int getBlockWidth(String s) {
        return this.blockCreators.get(s).getWidth();
    }

    /**
     * isSpaceSymbol: checks if a symbol is in the map.
     * @param s a string.
     * @return boolean value.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * isBlockSymbol: checks if a symbol is in the map.
     * @param s a string.
     * @return boolean value.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }
}
