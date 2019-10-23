package game;
/**
 * Class Velocity: A class that defines an object's change of Y axis and change
 * of X axis.
 *
 * @author ori29
 *
 */
public class Velocity {
    private double dx;
    private double dy;
    private double speed;
    /**
     * Velocity constructor.
     *
     * @param dx - delta X: the change in the XAxis.
     * @param dy - delta Y: the change in the YAxis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        this.speed = 0;
    }
    /**
     * Velocity constructor.
     *
     * @param dx - delta X: the change in the XAxis.
     * @param dy - delta Y: the change in the YAxis.
     * @param speed - the speed of the ball.
     */
    public Velocity(double dx, double dy, double speed) {
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }
    /**
     * Velocity constructor.
     *
     * @param vel - the velocity.
     */
    public Velocity(Velocity vel) {
        this.dx = vel.getDx();
        this.dy = vel.getDy();
        this.speed = vel.getSpeed();
    }
    /**
     * getDx method: a getter for the delta X.
     *
     * @return a double value of the change of dx.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * getDy method: a getter for the delta Y.
     *
     * @return a double value of the change of dy.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * getSpeed method: a getter for ball's speed.
     *
     * @return a double value that holds the speed of the ball.
     */
    public double getSpeed() {
        return this.speed;
    }
    /**
     * applyToPoint method: apply the delta values to a points XY coordinates.
     * @param p - a point.
     * @return a new point, after the delta applied.
     */
    public Point applyToPoint(Point p) {
        double dxPlusX = p.getX() + this.dx;
        double dyPlusY = p.getY() + this.dy;

        Point nextPoint = new Point(dxPlusX, dyPlusY);
        return nextPoint;
    }
    /**
     * fromAngleAndSpeed method: Converts an angle and speed into delta X
     * and delta Y, by using cos and sin functions.
     * @param angle - an angle.
     * @param speed - the speed.
     * @return returns the Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * speed * (-1);
        return new Velocity(dx, dy, speed);
    }
}

