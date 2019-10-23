package game;

import biuoop.DrawSurface;

/**
 * Class Ball: defined by a center point, a radius, color and velocity of
 * movement.
 *
 * @author ori29
 *
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity vel;
    private GameEnvironment game;

    /**
     * Ball constructor.
     * @param x      - balls x coordinate of the center point.
     * @param y      - balls y coordinate of the center point.
     * @param radius - balls radius.
     * @param color  - balls color.
     */
    public Ball(int x, int y, int radius, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.vel = new Velocity(2, 2); // Default Velocity.
    }
    /**
     * getX method: a getter of the X coordinate of the ball's center.
     *
     * @return the X coordinate of the ball's center.
     */
    public double getX() {
        return this.center.getX();
    }
    /**
     * getY method: a getter of the Y coordinate of the ball's center.
     *
     * @return the Y coordinate of the ball's center.
     */
    public double getY() {
        return this.center.getY();
    }
    /**
     * getCenter method: a getter of the ball's center point.
     *
     * @return the ball's center.
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * getSize method: a getter of the ball's radius.
     *
     * @return the ball's radius (integer).
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * setSize method: a setter of the ball's radius.
     *
     * @param rad gets an integer and sets it as the ball's radius.
     */
    public void setSize(int rad) {
        this.radius = rad;
    }
    /**
     * setCenter method: a setter of the ball's center.
     *
     * @param cen gets a point and sets it as the ball's center.
     */
    public void setCenter(Point cen) {
        this.center = cen;
    }
    /**
     * setGame method: a setter of the ball's GameEnvironment.
     *
     * @param g a game enviorment.
     */
    public void setGame(GameEnvironment g) {
        this.game = g;
    }
    /**
     * getColor Method: A getter for the ball's color.
     *
     * @return returns the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * setVelocity method: Sets the velocity by getting a velocity object.
     *
     * @param v - Velocity object.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }
    /**
     * setVelocity method: Sets the ball's velocity by delta X and Y.
     *
     * @param dx - delta X: the change in the XAxis.
     * @param dy - delta Y: the change in the YAxis.
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }
    /**
     * getVelocity method: a getter for the ball's velocity.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.vel;
    }
    /**
     * nextCollision method: Determing the trajectory of the ball and than checks
     * whether there's a collision within this trajectory. If so, returns the
     * closest collision.
     *
     * @return null if no collision, CollisionInfo if there is.
     */
    public CollisionInfo nextCollision() {

        // get the line of the trajectory
        Point target = new Point(this.getX() + this.vel.getDx() * 10, this.getY() + this.vel.getDy() * 10);
        Line trajectory = new Line(this.center, target);

        // find closest collision
        CollisionInfo coll = this.game.getClosestCollision(trajectory);

        if (coll == null) {
            return null;
        }
        return coll;
    }

    /**
     * timePassed method: part of the Sprite interface. this is the method with
     * moves the ball in the right direction, taking collisions into calculations.
     */
    @Override
    public void timePassed() {

        // get collision info
        CollisionInfo coll = this.nextCollision();

        // no collision - continue in the current velocity.
        if (coll == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;

        // if the ball is close to collision by two time radius plus it's spped, call "Hit" Method,
        // which changes the velocity according to the point of the collision.
        } else if (this.getCenter().distance(coll.collisionPoint()) <= this.getVelocity().getSpeed()
                + this.getSize() * 1.4) {

            Velocity newVel = coll.collisionObject().hit(this, coll.collisionPoint(), this.getVelocity());
            this.setVelocity(newVel);
        }

        //Calculates the next collision point, than forcast the ball's next step.
        //if the ball will enter the rectangle, change velocity accordingly.
        CollisionInfo coll2 = this.nextCollision();

        if (coll2 == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        } else if (coll2.collisionObject().getCollisionRectangle().isThePointInside(this.moveForcast())) {
            Velocity newVel = new Velocity(-this.getVelocity().getDx(), -this.getVelocity().getDy()
                    , this.getVelocity().getSpeed());
            this.setVelocity(newVel);
        }
        //Move to the next step.
        this.center = this.getVelocity().applyToPoint(this.center);
        return;
    }

    /**
    * method moveForcast: forcasts the ball next center point.
    * @return the point of the next move of the ball.
    */
    public Point moveForcast() {
        Point temp = this.getCenter();
        temp = this.getVelocity().applyToPoint(temp);
        return temp;
    }
    /**
     * drawOn method: Draws the ball on a given draw surface.
     *
     * @param surface - the draw surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.black);
        surface.drawCircle((int) this.getX(), (int) this.getY(), this.radius);
        surface.setColor(color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.radius);
    }
    /**
     * Method addToGame: adds the ball to the Sprites list for class Ass3Game.
     * @param g a game.
     */
    public void addToGame(Game g) {
        g.addSprite((Sprite) this);
    }
    /**
     * Method removeFromGame: removes the ball from the Sprites list.
     * @param g a game.
     */
    public void removeFromGame(Game g) {
        g.removeSprite((Sprite) this);
    }

}
