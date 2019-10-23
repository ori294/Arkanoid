package game;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;

/**
 * ImageSprite: a sprite that holds an image.
 * @author ori29
 *
 */
public class ImageSprite implements Sprite {

    private Image image;
    private int imageX;
    private int imageY;

    /**
     * ImageSprite constructor.
     * @param fn filename string.
     * @param x the X position of the image.
     * @param y the Y position of the image.
     */
    public ImageSprite(String fn, int x, int y) {

        this.imageX = x;
        this.imageY = y;
        Image img = null;

        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fn);
            img = ImageIO.read(is);
            this.image = img;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * drawOn draws the image on the drawsurface.
     * @param d a drawsurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(this.imageX, this.imageY, this.image);
    }

    /**
     * timePassed: do nothing.
     */
    @Override
    public void timePassed() {
        // do nothing.
    }

    /**
     * addToGame: adds the image to the game.
     * @param g a Game.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);

    }

}
