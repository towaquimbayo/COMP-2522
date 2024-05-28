package org.bcit.comp2522.labs.lab03.Towa_Dinuja;

/**
 * The Mouse on screen.
 *
 * @author Towa, Dinuja
 * @version 1.0
 */
public class Mouse implements IDrawable {
  // X position
  protected float xpos;
  // Y position
  protected float ypos;
  // Diameter value
  protected final float diameter;
  // ID value
  protected final int id;
  // Window scene object
  protected Window scene;

  /**
   * Mouse Constructor.
   *
   * @param xin for x position
   * @param yin for y position
   * @param diam for diameter
   * @param idIn for id
   * @param scene for window scene
   */
  public Mouse(float xin, float yin, float diam, int idIn, Window scene) {
    this.xpos = xin;
    this.ypos = yin;
    this.diameter = diam;
    this.id = idIn;
    this.scene = scene;
  }

  /**
   * Draws the mouse.
   *
   * @param window for window
   */
  @Override
  public void draw(Window window) {
    xpos = window.mouseX;
    ypos = window.mouseY;
  }
}
