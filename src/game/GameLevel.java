package game;

import java.util.List;

import indicators.HitListener;
import indicators.LevelNameIndicator;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;
import indicators.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * GameLevel class: can load a level information and run the level.
 *
 * @author ori29
 *
 */
@SuppressWarnings("unused")
public class GameLevel extends Game {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Paddle paddle;
    private Sleeper sleeper;
    private Counter blockCount;
    private Counter ballCount;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private int width = 800;
    private int height = 600;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * GameLevel contructor.
     * @param lv a LevelInformation.
     * @param s score Counter.
     * @param l Lives Counter.
     * @param ar An AnimationRunner.
     * @param ks a KeyBoardSensor.
     * @param g a GUI.
     * @param sleep a Sleeper.
     */
    public GameLevel(LevelInformation lv, Counter s, Counter l, AnimationRunner ar,
            KeyboardSensor ks, GUI g, Sleeper sleep) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = ar;
        this.keyboard = ks;
        this.gui = g;
        this.level = lv;
        this.score = s;
        this.lives = l;
        this.sleeper = sleep;
    }

    /**
     * addCollidable method: add a collideable to the collideables list.
     *
     * @param c collideable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite method: add a sprite to the sprite list.
     *
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * initialize method: Initialize a new game: create the Blocks and Ball (and
     * Paddle) and add them to the game.
     */
    public void initialize() {

        this.blockCount = new Counter();
        this.ballCount = new Counter();

        HitListener blockRemover = new BlockRemover(this, this.blockCount);
        HitListener ballRemover = new BallRemover(this, this.ballCount);
        HitListener scoreTracker = new ScoreTrackingListener(this.score);

        //set background.
        this.level.getBackground().addToSpriteList(this.sprites);
        Rectangle rectangle1 = new Rectangle(0, 0, 800, 40);
        Block block0 = new Block(rectangle1, java.awt.Color.LIGHT_GRAY, java.awt.Color.LIGHT_GRAY, 0);
        block0.addToSprites(this);

        // set score indicator.
        Rectangle indicatorRect = new Rectangle(100, 0, 250, 40);
        ScoreIndicator indicator = new ScoreIndicator(indicatorRect, this.score, java.awt.Color.LIGHT_GRAY,
                java.awt.Color.LIGHT_GRAY);
        indicator.addToGame(this);

        // set lives indicator.
        Rectangle livesRec = new Rectangle(0, 0, 100, 40);
        LivesIndicator livesIndicator = new LivesIndicator(livesRec, this.lives, java.awt.Color.LIGHT_GRAY,
                java.awt.Color.LIGHT_GRAY);
        livesIndicator.addToGame(this);

        // set level name indicator.
        Rectangle name = new Rectangle(300, 0, 300, 40);
        LevelNameIndicator nameIndicator = new LevelNameIndicator(name, this.level.levelName(),
                java.awt.Color.LIGHT_GRAY, java.awt.Color.LIGHT_GRAY);
        nameIndicator.addToGame(this);

        // set top limit rectangle.
        Rectangle rect1 = new Rectangle(0, 40, 800, 15);
        Block block1 = new Block(rect1, java.awt.Color.GRAY, java.awt.Color.GRAY, 0);
        block1.addToGame(this);

        // set left limit rectangle.
        Rectangle rect2 = new Rectangle(0, 55, 15, 600);
        Block block2 = new Block(rect2, java.awt.Color.GRAY, java.awt.Color.GRAY, 0);
        block2.addToGame(this);

        // set right limit rectangle.
        Rectangle rect4 = new Rectangle(785, 55, 25, 600);
        Block block4 = new Block(rect4, java.awt.Color.GRAY, java.awt.Color.GRAY, 0);
        block4.addToGame(this);

        // set bottom limit rectangle, it will delete balls that reach it.
        Rectangle rect3 = new Rectangle(0, 640, 800, 20);
        Block block3 = new Block(rect3, java.awt.Color.BLACK, java.awt.Color.BLACK, 0);
        block3.addToGame(this);
        block3.addHitListener(ballRemover);

        // set the blocks.
        List<Block> bList = this.level.blocks();

        for (int i = 0; i < bList.size(); i++) {
            bList.get(i).addToGame(this);
            bList.get(i).addHitListener(blockRemover);
            bList.get(i).addHitListener(scoreTracker);
            this.blockCount.increase(1);
        }
    }
    /**
     * runs the game, each turn at a time. closes the game when the game is over.
     */
    public void run() {
        while (this.lives.getValue() > 0) {
            this.playOneTurn();
        }
        return;
    }
    /**
     * playOneTurn method: runs the game and it's animation for one turn.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }
    /**
     * removeCollidable: removes a collideable from the list.
     *
     * @param c collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * removeSprite: removes a sprite from the list.
     *
     * @param s sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * createBallsOnTopOfPaddle: creates ball after they have fallen.
     */
    public void createBallsOnTopOfPaddle() {
        // set the paddle.
        Rectangle pad = new Rectangle(370, 580, this.level.paddleWidth(), 20);
        Paddle paddle1 = new Paddle(this.gui, pad, java.awt.Color.ORANGE, java.awt.Color.BLACK,
                this.width - 20, 20, this.level.paddleSpeed());
        this.paddle = paddle1;
        this.paddle.addToGame(this);

        // set balls.
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(420, 575, 5, this.level.ballColor());
            Velocity v = this.level.initialBallVelocities().get(i);
            ball.setVelocity(v);
            ball.setGame(this.environment);
            ball.addToGame(this);
            this.ballCount.increase(1);
        }
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                    new PauseScreen()));
        }

        if (this.blockCount.getValue() <= 0) {
            this.paddle.removeFromGame(this);
            this.score.increase(100);
            this.running = false;
            return;
        }

        if (this.ballCount.getValue() <= 0) {
            this.paddle.removeFromGame(this);
            this.lives.decrease(1);
            this.running = false;
        }
    }
    /**
     * shouldStop returns the opposite boolean value that indicate if
     * the level running should stop.
     * @return a boolean value.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * returns the level block count.
     * @return the block count.
     */
    public int blockCount() {
        return this.blockCount.getValue();
    }
}