package game;

/**
 * Class name: Line. Description: Two points that compose a line. the class has
 * also the middle point field and the slope.
 *
 * @author ori29
 *
 */
public class Line {

    private Point start;
    private Point end;
    private Point middle;
    @SuppressWarnings("unused")
    private Slope slope;

    /**
     * Line constructor (number 1) by two points.
     *
     * @param start - the starting point (X,Y).
     * @param end   - the ending point (X,Y).
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.slope = new Slope(start, end);
    }

    /**
     * Line constructor (number 2) by (x1,y1,x2,y2) coordinates.
     *
     * @param x1 - first point X coordinate.
     * @param y1 - first point Y coordinate.
     * @param x2 - second point X coordinate.
     * @param y2 - second point Y coordinate.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.slope = new Slope(this.start, this.end);

    }

    /**
     * Method length: measure the length of the line, by calling Point.distance()
     * method.
     *
     * @return - the length of the line (double type).
     */
    public double length() {
        double distance = this.start.distance(end);
        return distance;
    }

    /**
     * Method start: a getter for the start point of the line.
     *
     * @return start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Method end: a getter for the end point of the line.
     *
     * @return end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Method middle: Calculates the X,Y coordinates of the line's middle point.
     *
     * @return the middle of the line point.
     */
    public Point middle() {
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();

        double midX = (x1 + x2) / 2;
        double midY = (y1 + y2) / 2;

        this.middle = new Point(midX, midY);

        return this.middle;
    }

    /**
     * Method isIntersecting: Find's out whether a line has intersection point with
     * another given line.
     *
     * @param other - the other line we want to check for intersection point.
     * @return a boolean value.
     */
    public boolean isIntersecting(Line other) {
        Point interXY = this.intersectionWith(other);

        if (interXY != null) {
            return true;
        }
        return false;
    }

    /**
     * Method intersectionWith: find's the intersection point of two lines.
     *
     * @param other - the other line we want to check for intersection point.
     * @return returns the point of intersection, if there's such (else: returns
     *         null).
     */
    public Point intersectionWith(Line other) {
        // get the data we need for the first line equation.
        double y1 = this.start.getY();
        double x1 = this.start.getX();
        Slope m1 = new Slope(this.start, this.end);

        // get the data we need for the second line equation.
        double x2 = other.start.getX();
        double y2 = other.start.getY();
        Slope m2 = new Slope(other.start, other.end);

        if (!m1.getNull() && !m2.getNull() && m1.getDeltaXY() == m2.getDeltaXY()) {
            return null;
        }
        if (m1.getNull() && m2.getNull()) {
            return null;
        }

        if (m1.getNull()) {
            double interX = x1;
            double b2 = y2 - (m2.getDeltaXY() * x2); // find the y-intercept.
            double interY = b2 + (m2.getDeltaXY() * interX);
            Point interXY = new Point(interX, interY);

            // Checks whether the point we found is on the line segment.
            if (this.containsPoint(interXY) && other.containsPoint(interXY)) {
                return interXY;
            } else {
                return null;
            }
        }

        if (m2.getNull()) {
            double interX = x2;
            double b1 = y1 - (m1.getDeltaXY() * x1); // find the y-intercept.
            double interY = b1 + (m1.getDeltaXY() * interX);
            Point interXY = new Point(interX, interY);

            // Checks whether the point we found is on the line segment.
            if (this.containsPoint(interXY) && other.containsPoint(interXY)) {
                return interXY;
            } else {
                return null;
            }
        }

        double b1 = y1 - (m1.getDeltaXY() * x1); // find the y-intercept.
        double b2 = y2 - (m2.getDeltaXY() * x2); // find the y-intercept.

        // finds the X,Y of the intersection point.
        double interX = (b2 - b1) / (m1.getDeltaXY() - m2.getDeltaXY());
        double interY = (m1.getDeltaXY() * interX) + b1;
        Point interXY = new Point(interX, interY);

        // Checks whether the point we found is on the line segment.
        if (this.containsPoint(interXY) && other.containsPoint(interXY)) {
            return interXY;
        } else {
            return null;
        }
    }


    /**
     * Method containsPoint: Finds out whether a point is on the line.
     *
     * @param xy - The point we want to check.
     * @return a boolean value.
     */
    public boolean containsPoint(Point xy) {
        /*
         * / Getting the data we need for calculation. epsilon: because the program
         * calculates the distance between points with double types, in some rare cases,
         * the numbers will be inaccurate and thus the calculation will fail. The
         * epsilon is a small number that will cover any possible discrepancies.
         */
        double epsilon = 0.0000000001;
        double ab = this.start.distance(xy);
        double bc = this.end.distance(xy);
        double ac = this.length();

        /*
         * By using the triangle in-equality, whenever AB + BC is bigger than than AC,
         * it means that ABC is a triangle and thus the point B isn't on the line AC.
         */
        if (ab + bc > ac + epsilon) {
            return false;

        }
        return true;
    }

    /**
     * Method equals: Finds out whether two line are equal.
     *
     * @param other - the other line we want to compare to.
     * @return - a boolean value.
     */
    public boolean equals(Line other) {

        double x1 = this.start.getX();
        double x2 = other.end.getX();
        double y1 = this.start.getY();
        double y2 = other.start.getY();

        if ((x1 == x2) && (y1 == y2)) {
            return true;
        }

        return false;
    }
    /**
     * Method closestIntersectionToStartOfLine: compute a line's closest intersection.
     * point with a given Rectangle.
     * @param rect a Rectangle.
     * @return The point of the closest intersection or null if there isn't.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //Get the intersection point each of the Rectangle lines.
        Point intersect1 = this.intersectionWith(rect.getUpperLine());
        Point intersect2 = this.intersectionWith(rect.getBottomLine());
        Point intersect3 = this.intersectionWith(rect.getLeftLine());
        Point intersect4 = this.intersectionWith(rect.getRightLine());

        //Create variables for distance with default values.
        double distance1 = (-1);
        double distance2 = (-1);
        double distance3 = (-1);
        double distance4 = (-1);
        double smallest = (-1);
        boolean isIntersecting = false;

        //if the line intersects with the upper line.
        if (intersect1 != null) {
            distance1 = this.start.distance(intersect1);
            if (distance1 < smallest) {
                smallest = distance1;
            }
            if (!isIntersecting) {
                isIntersecting = true;
                smallest = distance1;
            }
        }
        //if the line intersects with the bottom line.
        if (intersect2 != null) {
            distance2 = this.start.distance(intersect2);
            if (distance2 < smallest) {
                smallest = distance2;
            }
            if (!isIntersecting) {
                isIntersecting = true;
                smallest = distance2;
            }
        }
        //if the line intersects with the left line.
        if (intersect3 != null) {
            distance3 = this.start.distance(intersect3);
            if (distance3 < smallest) {
                smallest = distance3;
            }
            if (!isIntersecting) {
                isIntersecting = true;
                smallest = distance3;
            }
        }
        //if the line intersects with the right line.
        if (intersect4 != null) {
            distance4 = this.start.distance(intersect4);
            if (distance4 < smallest) {
                smallest = distance4;
            }
            if (!isIntersecting) {
                isIntersecting = true;
                smallest = distance4;
            }
        }
        //determine which intersection point is the closest.
        if (smallest == distance1 && distance1 >= 0) {
            return intersect1;
        }
        if (smallest == distance2 && distance2 >= 0) {
            return intersect2;
        }
        if (smallest == distance3 && distance3 >= 0) {
            return intersect3;
        }
        if (smallest == distance4 && distance4 >= 0) {
            return intersect4;
        }
        //return null if no intersections found.
        return null;
    }
}


