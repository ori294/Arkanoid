package game;

import biuoop.DrawSurface;

/**
 * DecorativeLine: a Dectration Line.
 * @author ori29
 *
 */
public class DecorativeLine implements Sprite {

    private java.awt.Color color;
    private int strX;
    private int strY;
    private int endX;
    private int endY;

    /**
     * DecorativeLine contructor.
     * @param x1 the X of the start point.
     * @param y1 the Y of the start point.
     * @param x2 the X of the end point.
     * @param y2 the Y of the end point.
     * @param col the color of the line.
     */
    public DecorativeLine(int x1, int y1, int x2, int y2, java.awt.Color col) {
        this.strX = x1;
        this.strY = y1;
        this.endX = x2;
        this.endY = y2;
        this.color = col;

    }

    /**
     * drawOn: draws the line as a Sprite.
     * @param d a DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine(this.strX, this.strY, this.endX, this.endY);
    }

    /**
     * Line shouldn't move, so this one does nothing.
     */
    @Override
    public void timePassed() {
        //do nothing.
    }

    /**
     * addToGame: adds the line to the game.
     * @param g a game.
     */
    @Override
    public void addToGame(Game g) {
        //do nothing.
    }
}