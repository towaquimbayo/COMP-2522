package org.bcit.comp2522.labs.lab03.noufil_siwoon;

/**
 * Mouse class created for cursor tracking.
 *
 * @author Siwoon Lim & Noufil Saqib
 * @version 1.0
 */
public class Mouse implements IDrawable {
  protected float xpos;
  protected float ypos;
  protected final float diameter;
  protected final int id;
  protected Window scene;

  /**
   * Constructor for Mouse class.
   *
   * @param xin for x-position
   * @param yin for y-position
   * @param din for mouse diameter
   * @param idin for mouse id
   * @param scene for Window scene
   */
  public Mouse(float xin, float yin, float din, int idin, Window scene) {
    this.xpos = xin;
    this.ypos = yin;
    this.diameter = din;
    this.id = idin;
    this.scene = scene;
  }

  @Override
  public void draw(Window window) {
    xpos = window.mouseX;
    ypos = window.mouseY;
  }
}
