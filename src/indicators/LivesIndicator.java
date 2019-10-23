package indicators;

import java.awt.Color;
import game.Counter;
import game.Game;
import game.Line;
import game.Point;
import game.Rectangle;
import game.Sprite;
import biuoop.DrawSurface;

/**
 * LivesIndicator indicates how many lives the player has.
 * @author ori29
 *
 */
public class LivesIndicator implements Sprite {

    private Rectangle ind;
    private java.awt.Color innerColor;
    private java.awt.Color boundryColor;
    private Point middle;
    private Counter lives;

    /**
     * LivesIndicator constructor.
     * @param indicator a rectangle.
     * @param l lives counter.
     * @param inColor the inside color.
     * @param bColor outside color.
     */
    public LivesIndicator(Rectangle indicator, Counter l, Color inColor, Color bColor) {
        this.ind = indicator;
        this.lives = l;
        this.innerColor = inColor;
        this.boundryColor = bColor;
        Line midLine = new Line(ind.getBottomRight(), ind.getUpperLeft());
        this.middle = midLine.middle();
    }

    /**
     * drawOn: draws the lives indicator on the screen.
     * @param d a drawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(boundryColor);
        d.drawRectangle((int) ind.getUpperLeft().getX(), (int) ind.getUpperLeft().getY(), (int) ind.getWidth(),
                (int) ind.getHeight());
        d.setColor(innerColor);
        d.fillRectangle((int) ind.getUpperLeft().getX(), (int) ind.getUpperLeft().getY(), (int) ind.getWidth(),
                (int) ind.getHeight());
        d.setColor(java.awt.Color.BLACK);
        d.drawText((int) middle.getX() - 45, (int) middle.getY() + 5,
                "Lives: " + String.valueOf(this.lives.getValue()) , 20);
    }

    /**
     * Part of the sprite interface, does nothing here.
     */
    @Override
    public void timePassed() {
        // do noting.
    }

    /**
     * addToGame: adds the indicator to the game.
     * @param game the game we want to add to.
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
