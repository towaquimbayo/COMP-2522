package org.bcit.com2522.labs.lab03;

import processing.core.PVector;

/**
 * The Drawable Mouse on window scene.
 *
 * @author paul_bucci
 * @author Tommy Nguyen
 * @author Jashanjot Singh
 * @version 1.0
 */
public class Mouse implements IDrawable {
  protected PVector position;
  protected Window window;
  protected float width = 25f;
  protected float height = 25f;

  /**
   * Mouse constructor.
   *
   * @param position centrepoint position
   * @param window   scene window
   */
  public Mouse(PVector position, Window window) {
    this.position = position;
    this.window = window;
  }

  /**
   * Draws the mouse pointer.
   *
   * @param window scene window
   */
  @Override
  public void draw(Window window) {
    window.fill(255, 204);
    window.ellipse(this.position.x, this.position.y, this.width, this.height);
  }

  /**
   * Move the mouse pointer to the position of the cursor.
   *
   * @param window scene window
   */
  public void move(Window window) {
    this.position.set(window.mouseX, window.mouseY);
  }
}
