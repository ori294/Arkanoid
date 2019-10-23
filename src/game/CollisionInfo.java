package game;

/**
 * CollisionInfo class: Holds the information about a collision.
 * Holds the shape of the collision and the point of the collision.
 *
 * @author ori29
 *
 */
public class CollisionInfo {

    private Collidable shape;
    private Point collisionPoint;
    /**
     * CollisionInfo contructor.
     * @param shape the shape of the collision.
     * @param collisionPoint the point of the collision.
     */
    public CollisionInfo(Collidable shape, Point collisionPoint) {
        this.collisionPoint = collisionPoint;
        this.shape = shape;
    }
    /**
    * collisionPoint method: returns the collision point.
    * @return collisionPoint.
    */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
    * collisionObject method: returns the collision shape (mostly rectangle).
    * @return collisionObject.
    */
    public Collidable collisionObject() {
        return shape;
    }
}
