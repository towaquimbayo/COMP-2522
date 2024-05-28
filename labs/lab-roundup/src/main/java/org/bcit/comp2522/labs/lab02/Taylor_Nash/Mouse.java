package org.bcit.comp2522.labs.lab02.Taylor_Nash;

/**
 * Class for Mouse to present current mouse's x and y position.
 * Implements collide method from AbstractCollidable interface.
 *
 * @author Nash and Taylor
 * @version 2022 September
 *
 */
public class Mouse implements AbstractCollidable {
  public void collide() {}

  public void printMousePosition(float x, float y) {
    System.out.println("Current mouse position X: " + x + ", Y: " + y);
  }
}
