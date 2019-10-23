package game;

/**
 * Game class: manage the game for this assiment.
 *
 * @author ori29
 *
 */
public abstract class Game implements Animation {

    /**
     * Game constructor.
     */
    public Game() {
        //nothing.
    }
    /**
     * addCollidable method: add a collideable to the collideables list.
     * @param c collideable.
     */
    public abstract void addCollidable(Collidable c);

    /**
     * addSprite method: add a sprite to the sprite list.
     * @param s a sprite.
     */
    public abstract void addSprite(Sprite s);
    /**
     * initialize method: Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public abstract void initialize();

    /**
     * run method: runs one turn of the game and it's animation.
     */
    public abstract void run();

    /**
     * playOneTurn method: runs one turn of the game and it's animation.
     */
    public abstract void playOneTurn();

    /**
     * removeCollidable: removes a collideable from the list.
     * @param c collideable.
     */
    public abstract void removeCollidable(Collidable c);

    /**
     * removeSprite: removes a sprite from the list.
     * @param s sprite.
     */
    public abstract void removeSprite(Sprite s);
}
