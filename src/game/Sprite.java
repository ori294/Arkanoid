package game;

import biuoop.DrawSurface;

/**
 * Interface Sprite: Sprite is an object in the game which can move.
 * It can be anything from paddle to block and monsters.
 * @author ori29
 */
public interface Sprite {
       /**
       * drawOn: draw the object on the board.
       * @param d the draw surface.
       */
       void drawOn(DrawSurface d);
       /**
       * timePasswd: notify the object that it's time to move.
       */
       void timePassed();
       /**
       * addToGame: ads a give sprite to the game.
       * @param g the game.
       */
       void addToGame(Game g);
    }
