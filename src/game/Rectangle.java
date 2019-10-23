package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Rectangle: a rectangle, defined by it's top left corner, it's width and
 * height.
 *
 * @author ori29
 *
 */
public class Rectangle {

    // all the vertexes of the Rectangle.
    private Point upperLeft;
    private Point upperRight;
    private Point bottomLeft;
    private Point bottomRight;

    // Width and height.
    private double recWidth;
    private double recheight;

    // all the lines of the rectangle.
    private Line upperLine;
    private Line bottomLine;
    private Line leftLine;
    private Line rightLine;

    /**
     * Rectangle contructor: gets the upper left point, width and height, that
     * computes all other info needed (lines and vertexes).
     *
     * @param upLeft the upper left vertex.
     * @param width  the witdh of the Rectangle.
     * @param height the height of the Rectangle.
     */
    public Rectangle(Point upLeft, double width, double height) {
        this.upperLeft = upLeft;
        this.recWidth = width;
        this.recheight = height;

        // compute other vertexes.
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);

        // compute the lines of the rectangle.
        this.upperLine = new Line(this.getUpperLeft(), this.getUpperRight());
        this.bottomLine = new Line(this.getBottomLeft(), this.getBottomRight());
        this.leftLine = new Line(this.getUpperLeft(), this.getBottomLeft());
        this.rightLine = new Line(this.getUpperRight(), this.getBottomRight());
    }

    /**
     * Rectangle contructor: gets the upper left point (by coordinates), width and
     * height, that computes all other info needed (lines and vertexes).
     *
     * @param x      the x coordinate of the upper left vertex.
     * @param y      the y coordinate of the upper left vertex.
     * @param width  the witdh of the Rectangle.
     * @param height the height of the Rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.recWidth = width;
        this.recheight = height;

        // compute other vertexes
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);

        // compute the lines of the rectangle.
        this.upperLine = new Line(this.getUpperLeft(), this.getUpperRight());
        this.bottomLine = new Line(this.getBottomLeft(), this.getBottomRight());
        this.leftLine = new Line(this.getUpperLeft(), this.getBottomLeft());
        this.rightLine = new Line(this.getUpperRight(), this.getBottomRight());
    }
    /**
     * intersectionPoints method: computes the intersection points
     * of the rectangle with a given line.
     * @param line the line we want to find intersections with.
     * @return a list that contains the points of intersections, might be null list.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        //create the list of lines.
        List<Point> interPoints = new ArrayList<Point>();

        //compute the intersection points.
        Point intersect1 = line.intersectionWith(this.upperLine);
        Point intersect2 = line.intersectionWith(this.bottomLine);
        Point intersect3 = line.intersectionWith(this.leftLine);
        Point intersect4 = line.intersectionWith(this.rightLine);

        //add the not null intersection points to the list.
        if (intersect1 != null) {
            interPoints.add(intersect1);
        }
        if (intersect2 != null) {
            interPoints.add(intersect2);
        }
        if (intersect3 != null) {
            interPoints.add(intersect3);
        }
        if (intersect4 != null) {
            interPoints.add(intersect4);
        }

        //return the list.
        return interPoints;
    }

    /**
     * isThePointInside method: Finds out if a point is inside the rectangle.
     * @param p a Point
     * @return boolean value
     */
    public boolean isThePointInside(Point p) {
        if (p.getX() >= this.bottomLeft.getX() && p.getX() <= this.bottomRight.getX()) {
            if (p.getY() >= this.upperLeft.getY() && p.getY() <= this.bottomLeft.getY()) {
                return true;
            }
        }
        return false;
    }
    /**
     * getWidth: return the rectangle's width.
     * @return the rectangle's width.
     */
    public double getWidth() {
        return this.recWidth;
    }

    /**
     * getHeight: return the rectangle's height.
     * @return the rectangle's height.
     */
    public double getHeight() {
        return this.recheight;
    }
    /**
     * getUpperLeft: return the rectangle's Upper left vertex.
     * @return the rectangle's Upper left vertex.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * getUpperRight: return the rectangle's Upper Right vertex.
     * @return the rectangle's Upper Right vertex.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }
    /**
     * getBottomLeft: return the rectangle's bottom left vertex.
     * @return the rectangle's bottom left vertex.
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }
    /**
     * getBottomRight: return the rectangle's bottom Right vertex.
     * @return the rectangle's bottom Right vertex.
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }
    /**
     * getUpperLine: return the rectangle's upper line.
     * @return  the rectangle's upper line.
     */
    public Line getUpperLine() {
        return this.upperLine;
    }
    /**
     * getBottomLine: return the rectangle's bottom line.
     * @return  the rectangle's bottom line.
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }
    /**
     * getLeftLine: return the rectangle's left line.
     * @return  the rectangle's left line.
     */
    public Line getLeftLine() {
        return this.leftLine;
    }
    /**
     * getRightLine: return the rectangle's right line.
     * @return  the rectangle's right line.
     */
    public Line getRightLine() {
        return this.rightLine;
    }

}
