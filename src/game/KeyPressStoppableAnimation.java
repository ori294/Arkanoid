package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation: shows animation that can be stopped by pressing something.
 * @author ori29
 */
public class KeyPressStoppableAnimation implements Animation {

    private String stopKey;
    private KeyboardSensor keyboard;
    private Animation anime;
    private boolean isAlreadyPressed = true;

    /**
     * KeyPressStoppableAnimation constructor.
     * @param sensor a KeyBoardSensor.
     * @param key the key which will stop the animation.
     * @param animation an Animation, a decorator to this class.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.stopKey = key;
        this.keyboard = sensor;
        this.anime = animation;
    }
    /**
     * doOneFrame: runs one frame.
     * @param d a DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.shouldStop()) {
            this.anime.doOneFrame(d);
        }
        if (!this.keyboard.isPressed(stopKey)) {
            this.isAlreadyPressed = false;
        }
    }
    /**
     * Asks the method if the animation should stop (checks if a key is pressed).
     * @return a boolean value.
     */
    @Override
    public boolean shouldStop() {

        if (this.keyboard.isPressed(stopKey) && this.isAlreadyPressed) {
            return false;
        } else if (this.keyboard.isPressed(stopKey)) {
            return true;
        }
        return false;
    }
}
