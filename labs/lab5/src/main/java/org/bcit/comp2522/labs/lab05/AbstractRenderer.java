package org.bcit.comp2522.labs.lab05;

/**
 * Renderers take IMovables and draw them with whatever geometry logic
 * they have implemented.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public abstract class AbstractRenderer {
  /**
   * Abstract method to render.
   *
   * @param window for window
   * @param moveable for IMoveable object
   */
  public abstract void render(Window window, IMoveable moveable);
}
