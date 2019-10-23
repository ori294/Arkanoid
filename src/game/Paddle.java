package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Class Paddle: Controlled by the keyboard, the paddle is actually quite simillar to blocks.
 * it can move and collide with balls.
 * Implements Sprite, Collidable.
 * @author ori29
 */
public class Paddle implements Sprite, Collidable {

    //Holds colors, speed, keyboard, a rectangle and movement limits.
    private biuoop.KeyboardSensor keyboard;
    private java.awt.Color innerColor;
    private java.awt.Color boundryColor;
    private Rectangle pad;
    private double rightLimit;
    private double leftLimit;
    private int speed;

    /**
    * Paddle constructor.
    * @param gui the relevant gui of the paddle.
    * @param paddle the rectangle behind the paddle.
    * @param inColor the inner color.
    * @param bColor the boundry color.
    * @param rLim the right limit of the paddle.
    * @param lLim the left limit of the paddle.
    */
    public Paddle(GUI gui, Rectangle paddle, java.awt.Color inColor, java.awt.Color bColor, double rLim
        , double lLim) {
        this.keyboard = gui.getKeyboardSensor();
        this.boundryColor = bColor;
        this.innerColor = inColor;
        this.pad = paddle;
        this.rightLimit = rLim;
        this.leftLimit = lLim;
        this.speed = 10; //Default speed of the paddle.
    }
    /**
    * Paddle constructor.
    * @param gui the relevant gui of the paddle.
    * @param paddle the rectangle behind the paddle.
    * @param inColor the inner color.
    * @param bColor the boundry color.
    * @param rLim the right limit of the paddle.
    * @param lLim the left limit of the paddle.
    * @param sp the speed of the paddle.
    */
    public Paddle(GUI gui, Rectangle paddle, java.awt.Color inColor, java.awt.Color bColor, double rLim
        , double lLim, int sp) {
        this.keyboard = gui.getKeyboardSensor();
        this.boundryColor = bColor;
        this.innerColor = inColor;
        this.pad = paddle;
        this.rightLimit = rLim;
        this.leftLimit = lLim;
        this.speed = sp; //Default speed of the paddle.
    }

    /**
     *Method moveLeft: called when the keyboard listener detects a press of the left key.
     */
    public void moveLeft() {

        //do not move if reached the edge of the screen.
        if (this.leftLimit >= pad.getUpperLeft().getX()) {
            return;
        }
        //change the Rectangles position according to the Paddle's speed.
        Point leftPoint = new Point(this.pad.getUpperLeft().getX() - speed, this.pad.getUpperLeft().getY());
        Rectangle moveLeft = new Rectangle(leftPoint, this.pad.getWidth(), this.pad.getHeight());
        pad = moveLeft;

    }

    /**
     *Method moveRight: called when the keyboard listener detects a press of the right key.
     */
    public void moveRight() {

        //do not move if reached the edge of the screen.
        if (this.rightLimit <= pad.getUpperRight().getX()) {
            return;
        }
        //change the Rectangles position according to the Paddle's speed.
        Point leftPoint = new Point(this.pad.getUpperLeft().getX() + speed,
                this.pad.getUpperLeft().getY());
        Rectangle moveRight = new Rectangle(leftPoint, this.pad.getWidth(), this.pad.getHeight());
        pad = moveRight;
    }
    /**
     * Method timePassed: checks whether the left or right keys are being
     * pressed, than call the relevant method.
     */
    @Override
    public void timePassed() {

        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }

    }

    /**
     * Method getCollisionRectangle: return the Rectangle behind the paddle.
     * @return the rectangle behind the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.pad;

    }

    /**
     * Method hit: this method will recieve the collosion point and the velocity and
     * will notify the ball about the collision (by changing it's velocity).
     *
     * @param collisionPoint  the collision point between the two objects.
     * @param currentVelocity the velocity of the colliding ball.
     * @param hitter the ball that does the hitting.
     * @return returns the updated velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //get the DY and DX of the ball.
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        /*
         * Hit from the top: the paddle is divided to 5 regions.
         * each regions will change the velocity differently.
         */
        if (pad.getUpperLine().containsPoint(collisionPoint)) {

            this.notifyHit(hitter);
            double divPad = pad.getUpperLine().length() / 5;
            double distance = pad.getUpperLeft().distance(collisionPoint);

            if (distance <= divPad) {
                // hit region 1
                Velocity newVel = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                //System.out.println("region1");
                return newVel;
            } else if (distance <= divPad * 2) {
                // hit region 2
                Velocity newVel = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                //System.out.println("region2");
                return newVel;
            } else if (distance <= divPad * 3) {
                // hit region 3
                Velocity newVel = new Velocity(dx, -dy, currentVelocity.getSpeed());
                //System.out.println("region3");
                return newVel;
            } else if (distance <= divPad * 4) {
                // hit region 4
                Velocity newVel = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                //System.out.println("region4");
                return newVel;
            } else {
                // hit region 5
                Velocity newVel = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
                //System.out.println("region5");
                return newVel;
            }

        } else if (pad.getBottomLine().containsPoint(collisionPoint)) {
            // hit from bottom
            Velocity newVel = new Velocity(dx, -dy, currentVelocity.getSpeed());
            return newVel;

        } else if (pad.getRightLine().containsPoint(collisionPoint)) {
            // hit from right
            Velocity newVel = new Velocity(-dx, dy, currentVelocity.getSpeed());
            return newVel;

        } else if (pad.getLeftLine().containsPoint(collisionPoint)) {
            // hit from left
            Velocity newVel = new Velocity(-dx, dy, currentVelocity.getSpeed());
            return newVel;
        }

        // Shouldn't reach here, but if from any reason the Hit method was called
        // by mistake, Do not chane the velocity.
        return currentVelocity;

    }
    /**
     * notifyHit method: notify to the object whenever it got hit.
     *
     * @param hitter - ball the does the hitting.
     */
    private void notifyHit(Ball hitter) {
        // Do nothing.
    }
    /**
     * drawOn method: Draws the paddle on a given draw surface.
     *
     * @param surface - the draw surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(boundryColor);
        surface.drawRectangle((int) pad.getUpperLeft().getX(), (int) pad.getUpperLeft().getY(), (int) pad.getWidth(),
                (int) pad.getHeight());
        surface.setColor(innerColor);
        surface.fillRectangle((int) pad.getUpperLeft().getX(), (int) pad.getUpperLeft().getY(), (int) pad.getWidth(),
                (int) pad.getHeight());
    }
    /**
     * Method addToGame: adds the block to the Sprites and collideable lists for class Ass3Game.
     * @param game a game.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
    /**
     * Method removeFromGame: removes the block from a given game.
     * @param game the game we want to remove from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
