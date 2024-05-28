package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

/**
 * Interface that sets up an object for collision.
 */
public interface ICollidable {

  boolean collided(ICollidable c);

  void collideBehaviour(ICollidable c);

}
