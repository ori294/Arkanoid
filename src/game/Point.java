package game;
/**
 * Class name: Point. Description: A single point, contains doubles as the x,y
 * coordinates Can be set once, and can't be changed afterwards.
 *
 * @author ori29.
 */
public class Point {

    private double xAxis;
    private double yAxis;

    /**
     * Point constructor.
     *
     * @param xAxis - the x coordinates of the point.
     * @param yAxis - the x coordinates of the point.
     */
    public Point(double xAxis, double yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    /**
     * distance method: measures the distance between two points.
     *
     * @param otherPoint - the point we want to measure the distance to
     * @return returns the distance between the points.
     */
    public double distance(Point otherPoint) {
        double x1 = this.xAxis;
        double y1 = this.yAxis;
        double x2 = otherPoint.xAxis;
        double y2 = otherPoint.yAxis;

        // The square root of (x1-x2)^2 + (y1-y2)^2
        return (double) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * equals method: checks whether two points are equal.
     *
     * @param other - the point we want to compare to.
     * @return returns a boolean value
     */
    public boolean equals(Point other) {

        if (this.getX() == other.getX()) {

            if (this.getY() == other.getY()) {

                return true;
            }
        }

        return false;
    }

    /**
     * getY method: getter for the Y coordinates.
     *
     * @return Y coordinate of the point.
     */
    public double getY() {
        return yAxis;
    }

    /**
     * getX method: getter for the X coordinates.
     *
     * @return X coordinate of the point.
     */
    public double getX() {
        return xAxis;
    }

}
