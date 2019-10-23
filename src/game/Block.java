package game;

import java.util.ArrayList;
import java.util.List;
import indicators.HitListener;
import indicators.HitNotifier;
import biuoop.DrawSurface;

/**
 * Class Block: a block in the game. Implements: Collidable, Sprite.
 *
 * @author ori29
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // gets a Rectangle, colors, hit counter and a middle point (only for showing
    // the hitcounter)
    private Rectangle bl;
    private java.awt.Color innerColor;
    private java.awt.Color boundryColor;
    private int hitCounter;
    private List<HitListener> hitListeners;
    private List<java.awt.Color> fills;
    private List<ImageSprite> images;
    private boolean hadDifferentFills;
    private boolean isImage;

    /**
     * Constructor Block: construct a block by recieving a Rectangle and colors. it
     * will also calculate the middle point.
     *
     * @param rect         a Rectangle.
     * @param innerColor   inside color.
     * @param stroke       outline color.
     * @param hits         the hit counter.
     */
    public Block(Rectangle rect, java.awt.Color innerColor, java.awt.Color stroke, int hits) {
        this.bl = rect;
        this.innerColor = innerColor;
        this.boundryColor = stroke;
        this.hadDifferentFills = false;
        this.isImage = false;

        // calculates the middle point of the block (for showing the hitcounter).
        this.hitCounter = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor Block: construct a block by recieving a Rectangle and colors. it
     * will also calculate the middle point.
     *
     * @param rect         a Rectangle
     * @param fillers      insides colors list.
     * @param stroke       outline color.
     * @param hits         the hit counter.
     */
    public Block(Rectangle rect, List<java.awt.Color> fillers, java.awt.Color stroke, int hits) {
        this.bl = rect;
        this.fills = fillers;
        this.boundryColor = stroke;
        this.hadDifferentFills = true;
        this.isImage = false;

        // calculates the middle point of the block (for showing the hitcounter).
        this.hitCounter = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor Block: construct a block by recieving a Rectangle and colors. it
     * will also calculate the middle point.
     *
     * @param rect         a Rectangle
     * @param ims          insides images list.
     * @param stroke       outline color.
     * @param hits         the hit counter.
     * @param numOfImages  number of images in the list.
     */
    public Block(Rectangle rect, List<String> ims, java.awt.Color stroke, int hits, int numOfImages) {
        this.bl = rect;
        this.boundryColor = stroke;
        this.hadDifferentFills = false;
        this.isImage = true;

        // calculates the middle point of the block (for showing the hitcounter).
        this.hitCounter = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.images = new ArrayList<ImageSprite>();

        for (int i = 0; i < numOfImages; i++) {
            ImageSprite imSprite = new ImageSprite(ims.get(i), (int) this.bl.getUpperLeft().getX(),
                    (int) this.bl.getUpperLeft().getY());
            this.images.add(imSprite);
        }
    }

    /**
     * Method getCollisionRectangle: Returns the Rectangle that's behind the block.
     *
     * @return Rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.bl;
    }

    /**
     * Method hit: this method will recieve the collosion point and the velocity and
     * will notify the ball about the collision (by changing it's velocity).
     *
     * @param collisionPoint  the collision point between the two objects.
     * @param currentVelocity the velocity of the colliding ball.
     * @param hitter the ball that did the hitting.
     * @return returns the updated velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        this.notifyHit(hitter);
        // Reduce hit counter and get the current DY DX of the velocity.

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (bl.getUpperLeft().distance(collisionPoint) < 0.2 || bl.getBottomLeft().distance(collisionPoint) < 0.2
                || bl.getUpperRight().distance(collisionPoint) < 0.2
                || bl.getBottomLeft().distance(collisionPoint) < 0.2) {

            // hits a corner
            Velocity newVel = new Velocity(-dx, -dy, currentVelocity.getSpeed());
            return newVel;
        }

        if (bl.getUpperLine().containsPoint(collisionPoint)) {
            // hit from top
            Velocity newVel = new Velocity(dx, -dy, currentVelocity.getSpeed());
            return newVel;

        } else if (bl.getBottomLine().containsPoint(collisionPoint)) {
            // hit from bottom
            Velocity newVel = new Velocity(dx, -dy, currentVelocity.getSpeed());
            return newVel;

        } else if (bl.getRightLine().containsPoint(collisionPoint)) {
            // hit from right
            Velocity newVel = new Velocity(-dx, dy, currentVelocity.getSpeed());
            return newVel;

        } else if (bl.getLeftLine().containsPoint(collisionPoint)) {
            // hit from left
            Velocity newVel = new Velocity(-dx, dy, currentVelocity.getSpeed());
            return newVel;
        }

        // Shouldn't reach here, but if from any reason the Hit method was called
        // by mistake, Do not chane the velocity.
        return currentVelocity;
    }

    /**
     * drawOn method: Draws the block on a given draw surface, including it's hit
     * counter.
     *
     * @param surface - the draw surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // draws the block.
        if (this.isImage) {
            this.images.get(hitCounter - 1).drawOn(surface);

        } else if (!this.hadDifferentFills) {
             surface.setColor(innerColor);
                if (this.boundryColor != null) {
                    surface.setColor(this.boundryColor);
                }
                surface.drawRectangle((int) bl.getUpperLeft().getX(), (int) bl.getUpperLeft().getY(),
                        (int) bl.getWidth(), (int) bl.getHeight());
                surface.setColor(innerColor);
                surface.fillRectangle((int) bl.getUpperLeft().getX(), (int) bl.getUpperLeft().getY(),
                        (int) bl.getWidth(), (int) bl.getHeight());
        } else if (this.hadDifferentFills && !this.isImage) {
            surface.setColor(this.fills.get(getHitPoints() - 1));
            if (this.boundryColor != null) {
                surface.setColor(this.boundryColor);
            }
            surface.drawRectangle((int) bl.getUpperLeft().getX(), (int) bl.getUpperLeft().getY(),
                    (int) bl.getWidth(), (int) bl.getHeight());
            surface.setColor(this.fills.get(getHitPoints() - 1));
            surface.fillRectangle((int) bl.getUpperLeft().getX(), (int) bl.getUpperLeft().getY(),
                    (int) bl.getWidth(), (int) bl.getHeight());
        }
    }
    /**
     * timePassed method: part of the Sprite interface, doesn't relevant for blocks.
     */
    @Override
    public void timePassed() {
        // Do nothing
    }

    /**
     * Method addToGame: adds the block to the Sprites and collideable lists for
     * class Game.
     *
     * @param g a game.
     */
    public void addToGame(Game g) {
        g.addCollidable((Collidable) this);
        g.addSprite((Sprite) this);
    }
    /**
     * Method addToSprites: adds the block to the Sprites.
     *
     * @param g a game.
     */
    public void addToSprites(Game g) {
        g.addSprite((Sprite) this);
    }

    /**
     * Method removeFromGame: removes the block from the Sprites and collideable
     * lists for class Game.
     *
     * @param g a game.
     */
    public void removeFromGame(Game g) {
        g.removeCollidable((Collidable) this);
        g.removeSprite((Sprite) this);
    }

    /**
     * addHitListener: adds an HL to the list of HL's.
     * @param hl an hit listener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removeHitListener: removes HL from the list.
     * @param hl an hit listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * returns the number of HP left for the block.
     * @return an int, the HP.
     */
    public int getHitPoints() {
        return this.hitCounter;
    }
    /**
     * setHitPoints: sets the HP of the block.
     * @param hits an integer contains the new HP.
     */
    public void setHitPoints(int hits) {
        this.hitCounter = hits;
    }
    /**
     * notifyHit: Notify the HL's whenever the block is getting hit.
     * @param hitter the hitter ball.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
