package game;

import biuoop.DrawSurface;

/**
 * DecorationBall: Kind of a ball, but it won't move.
 * @author ori29
 *
 */
public class DecorationBall implements Sprite {

    private Point center;
    private int radius;
    private java.awt.Color boundryColor;
    private java.awt.Color innerColor;

    /**
     * DecorationBall constructor.
     * @param x      - balls x coordinate of the center point.
     * @param y      - balls y coordinate of the center point.
     * @param radius - balls radius.
     * @param bColor  - balls color.
     * @param inColor - ball color.
     */
    public DecorationBall(int x, int y, int radius, java.awt.Color bColor, java.awt.Color inColor) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.boundryColor = bColor;
        this.innerColor = inColor;
    }

    /**
     * the ball should move, so this one does nothing.
     */
    @Override
    public void timePassed() {
        //Do Nothing.
    }

    /**
     * drawOn method: Draws the ball on a given draw surface.
     *
     * @param surface - the draw surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(boundryColor);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(innerColor);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);

    }

    /**
     * addToGame: adds the ball to the game sprites.
     * @param g a game.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
