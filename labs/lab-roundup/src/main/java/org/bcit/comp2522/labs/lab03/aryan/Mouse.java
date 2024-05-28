package org.bcit.comp2522.labs.lab03.aryan;

/**
 * Mouse class create to help track the movements of the cursor on the processing window.
 *
 * @author Aryan Jand & Amarra Hong
 * @version 2.0
 */
public class Mouse implements IDrawable {
  private float xpos;
  private float ypos;
  private final float diameter;
  Window window;

  /**
   * Mouse constructor, that helps create mouse objects.
   * With the following parameters.
   *
   * @param xpos     X-Coordinate of the mouse
   * @param ypos     Y-Coordinate of the mouse
   * @param diameter 2 times the radius of the mouse
   * @param window   the sprocessing window
   */
  public Mouse(float xpos, float ypos, float diameter, Window window) {
    this.xpos = xpos;
    this.ypos = ypos;
    this.diameter = diameter;
    this.window = window;
  }

  /**
   * Getter for the X-Coordinate of the mouse.
   *
   * @return xpos
   */
  public float getXpos() {
    return xpos;
  }

  /**
   * Setter for the X-Coordinate of the mouse Object.
   *
   * @param xpos X-Coordinate
   */
  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  /**
   * Getter for the y-Coordinate of the mouse.
   *
   * @return ypos
   */
  public float getYpos() {
    return ypos;
  }

  /**
   * Setter for the Y-Coordinate of the mouse Object.
   *
   * @param ypos Y-Coordinate
   */
  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  /**
   * Getter for the diameter of the mouse.
   *
   * @return diameter
   */
  public float getDiameter() {
    return diameter;
  }

  /**
   * Draws the properties of the mouse object.
   *
   * @param window processing window
   */
  @Override
  public void draw(Window window) {
    window.noStroke();
    window.fill(0xffff0000, 204);
    window.ellipse(getXpos(), getYpos(), getDiameter(), getDiameter());
  }

  /**
   * Moves the mouse object on the processing window.
   * Using x & y coordinates of the mouse.
   *
   * @param window processing window
   */
  public void move(Window window) {
    setXpos(window.mouseX);
    setYpos(window.mouseY);
  }
}