package game;

/**
 * Collidable interface: implemented in all objects that can collide with other objects.
 * @author ori29
 */
public interface Collidable {

       /**
       * Return the "collision shape" of the object.
       * @return a rectangle.
       */
       Rectangle getCollisionRectangle();

       /**
       *Notify the object that we collided with it at collisionPoint with a given velocity.
       * The return is the new velocity expected after the hit (based on
       * the force the object inflicted on us).
       *
       * @param collisionPoint the collision point.
       * @param currentVelocity balls current velocity.
       * @param hitter the ball that did the hitting.
       * @return a new velocity.
       */
       Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
