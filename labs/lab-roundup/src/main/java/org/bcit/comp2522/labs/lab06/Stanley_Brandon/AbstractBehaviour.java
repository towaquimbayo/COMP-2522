package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

/**
 * Abstract superclass representing methods for the behaviours.
 */
public abstract class AbstractBehaviour {

  /**
   * Move method changes an enemy's position based on their curent direction.
   *
   * @param window the frame where characters are drawn.
   */
  public abstract void move(Window window);

  /**
   * Changes an enemy's direction vector by the f parameter in radians.
   *
   * @param f angle to rotate in radians.
   *
   */
  public abstract void bounce(float f);
}
