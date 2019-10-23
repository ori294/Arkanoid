package utilities;

import game.Animation;
import game.AnimationRunner;
import game.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

/**
 * A menu task, shows the highscores animation.
 * @author ori29
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private KeyPressStoppableAnimation highScoresAnimation;

    /**
     * ShowHiScoresTask constructor.
     * @param r Animation runner.
     * @param hs Animation.
     * @param key Keyboard Sensor.
     */
    public ShowHiScoresTask(AnimationRunner r, Animation hs, KeyboardSensor key) {
        this.runner = r;
        this.highScoresAnimation = new KeyPressStoppableAnimation(key, "space", hs);
    }

    /**
     * runs the highscores animation.
     * @return Void.
     */
    @Override
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }

}
