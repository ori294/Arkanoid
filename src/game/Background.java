package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Background class: holds a sprite list of the backround sprites.
 * @author ori29
 *
 */
public class Background {

     private List<Sprite> spList = new ArrayList<Sprite>();

     /**
      * Returns the background list.
      * @return the background list.
      */
    public List<Sprite> getBackGround() {
        return this.spList;
    }

    /**
     * addToBackGround: adds sprite to the background.
     * @param s a Sprite.
     */
    public void addToBackGround(Sprite s) {
        spList.add(s);
    }

    /**
     * addToSpriteList: Adds the lists to a given SpriteCollection.
     * @param sp a spriteCollection.
     */
    public void addToSpriteList(SpriteCollection sp) {

        for (int i = 0; i < this.spList.size(); i++) {
            sp.addSprite(this.spList.get(i));
        }
    }

}
