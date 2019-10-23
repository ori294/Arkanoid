package game;
/**
 * Class slope: a class that wraps the slope of a given line.
 * the main reason for that wrap is for line with null slopes
 * (vertical lines), so we will know its a null slope and deal
 * with it accordingly.
 * @author ori29
 *
 */
public class Slope {

    private double deltaXY;
    private boolean isNull;
    /**
     * Slope constructor by a double.
     * @param deltaXY represents a line's slope.
     */
    public Slope(double deltaXY) {
        this.deltaXY = deltaXY;
    }
    /**
     * Slope constructor by two points.
     * @param start the first point of the line.
     * @param end the last point of the line.
     */
    public Slope(Point start, Point end) {
        double x1 = start.getX();
        double x2 = end.getX();
        double y1 = start.getY();
        double y2 = end.getY();

        if (x1 != x2) {
            this.deltaXY = (y2 - y1) / (x2 - x1);
            isNull = false;
        } else {
            isNull = true;
        }
    }
    /**
     * Method getDeltaXY: a getter for one's slope.
     * @return the slope value.
     */
    public double getDeltaXY() {
        if (this.getNull()) {
            return (-1);
        }
        return this.deltaXY;
    }
    /**
     * Method getNull: a getter that say whether the slope is null.
     * @return a boolean value.
     */
    public boolean getNull() {
        return this.isNull;
    }
}

