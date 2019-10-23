package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Class GameEnvironment: The GameEnviorment holds the info about the game's
 * collidable objects.
 *
 * @author ori29
 *
 */
public class GameEnvironment {

    // the list of the collidables.
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * addCollidable Method: adds a collidable to the collidable list.
     *
     * @param c a collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * RemoveCollidable Method: removes a collidable from the collidable list.
     *
     * @param c a collidable
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
    /**
     * getClosestCollision method: given a trajectory of a ball, find it's closest
     * collision with any of the collidables.
     *
     * @param trajectory a line representing a ball's trajectory
     * @return CollisionInfo, including the point of collision and the collision shape.
     * returns null if not collision was found.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        //create a temporary list of all found collision points and shapes
        List<Point> collisionList = new ArrayList<Point>();
        List<Collidable> shapesList = new ArrayList<Collidable>();
        boolean isColliding = false;

        //for each collidable in the list, compute if it intersects with the trajectory.
        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            Point collision = trajectory.closestIntersectionToStartOfLine(rect);

            //whenever a collision is found, add the point and the shape to the lists.
            if (collision != null) {
                shapesList.add(c);
                collisionList.add(collision);
                isColliding = true;
            }
        }

        //If we found at least 1 collision, find the closest one.
        if (isColliding) {

            Point closestCollision = collisionList.get(0);
            Collidable closestShape = shapesList.get(0);

            //for each point of collision, measure the distance to the ball.
            for (int i = 0; i < collisionList.size(); i++) {
                Point temp = collisionList.get(i);

                if (trajectory.start().distance(temp) <= closestCollision.distance(temp)) {
                    closestShape = shapesList.get(i);
                    closestCollision = temp;
                }
            }

            //call CollisionInfo constructor.
            CollisionInfo coll = new CollisionInfo(closestShape, closestCollision);
            return coll;
        }

        //return null if not a single collision was found.
        return null;
    }
}
