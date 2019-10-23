package game;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * SpriteCollection class: holds the list of the game Sprites.
 *
 * @author ori29
 *
 */
public class SpriteCollection {

    //create sprites list.
    private List<Sprite> gameSprites = new ArrayList<Sprite>();
    /**
     * removeSprite: removes a given sprite from the list.
     * @param s a Sprite
     */
    public void removeSprite(Sprite s) {
        this.gameSprites.remove(s);
    }
    /**
     * addSprite: adds a given sprite to the list.
     * @param s a Sprite
     */
    public void addSprite(Sprite s) {
        this.gameSprites.add(s);
    }
    /**
     * notifyAllTimePassed: Notify to the game Sprites that the time to
     * do their animation has come.
     */
    public void notifyAllTimePassed() {

        for (int i = 0; i < gameSprites.size(); i++) {
            gameSprites.get(i).timePassed();
        }
    }
    /**
     * drawAllOn: calls the Draw method for each sprite.
     * @param d a give draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < gameSprites.size(); i++) {
            gameSprites.get(i).drawOn(d);
        }
    }
    /**
     * getSize: return the list size.
     * @return size.
     */
    public int getSize() {
        return this.gameSprites.size();
    }
}