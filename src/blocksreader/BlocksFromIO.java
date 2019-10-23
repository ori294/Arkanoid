package blocksreader;

import java.util.ArrayList;
import java.util.List;

import game.Block;
import game.Rectangle;

/**
 * BlocksFromIO: Generate a block creator from IO read.
 * implements: BlockCreator.
 * @author ori29
 *
 */
public class BlocksFromIO implements BlockCreator {

    private String symbol;
    private java.awt.Color stroke;
    private int hitPoints;
    private int width;
    private int height;
    private List<java.awt.Color> fills;
    private List<String> images;
    private boolean hasImage = false;

    /**
     * Constructor.
     * @param s a string - the block symbol.
     */
    public BlocksFromIO(String s) {
        this.symbol = s;
        this.fills = new ArrayList<java.awt.Color>();
        this.images = new ArrayList<String>();
    }

    /**
     * setHeight: sets the block height.
     * @param s a string.
     */
    public void setHeight(String s) {
        if (s != null) {
            this.height = Integer.parseInt(s);
        }
    }

    /**
     * setWidth: sets the block width.
     * @param s a string.
     */
    public void setWidth(String s) {
        if (s != null) {
            this.width = Integer.parseInt(s);
        }
    }
    /**
     * setHP: sets the block Hit Points.
     * @param s a string.
     */
    public void setHP(String s) {
        if (s != null) {
            this.hitPoints = Integer.parseInt(s);
        }
    }
    /**
     * setStroke: Sets the block boundry color.
     * @param s a string.
     */
    public void setStroke(java.awt.Color s) {
        if (s != null) {
            this.stroke = s;
        }
    }
    /**
     * addFill: adds a fill (color) to the block.
     * @param s a Color.
     */
    public void addFill(java.awt.Color s) {
        if (s != null) {
            this.fills.add(s);
        }
    }
    /**
     * addImage: adds a fill (image) to the block.
     * @param s a string.
     */
    public void addImage(String s) {
        if (s != null) {
            this.hasImage = true;
            this.images.add(s);
        }
    }

    /**
     * getWidth: returns the block width.
     * @return an integer - width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * create: creates a block in the given position.
     * @param xpos the X position on the screen.
     * @param ypos the Y position on the screen.
     * @return a Block object.
     */
    @Override
    public Block create(int xpos, int ypos) {
        Rectangle rec = new Rectangle(xpos, ypos, this.width, this.height);
        Block bl = null;

        if (this.hasImage) { //create with an image fill.
            bl = new Block(rec, this.images, this.stroke, this.hitPoints, this.images.size());
            return bl;

        } else if (this.fills.size() == 1) { //create with a color fill.
            bl = new Block(rec, this.fills.get(0), this.stroke, this.hitPoints);
        } else { //create with several color fills.
            bl = new Block(rec, this.fills, this.stroke, this.hitPoints);
        }
        return bl;
    }
}
