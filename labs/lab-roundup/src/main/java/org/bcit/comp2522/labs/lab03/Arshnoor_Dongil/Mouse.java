package org.bcit.comp2522.labs.lab03.Arshnoor_Dongil;

import java.awt.*;
import java.awt.Color;
import processing.core.PVector;

/**
 * Lab-03 mouse class.
 * Create a mouse for the lab-03.
 *
 * @author Dongil ashnoor ,ragav
 * @version 2022-10-05
 */
public class Mouse implements IDrawable {
  private final float width;
  private final float height;
  private PVector position;
  private final Color color;
  protected Window window;

  /**
   * Constructor for mouse.
   *
   * @param positionIn mouse's position
   * @param widthIn mouse's width
   * @param heightIn mouse's height
   * @param window current scene window
   */
  public Mouse(PVector positionIn, float widthIn, float heightIn, Window window) {
    this.width = widthIn;
    this.height = heightIn;
    this.position = positionIn;
    this.color = new Color(241, 204, 19);
    this.window = window;
  }

  public BoundingBox getBoundingBox() {
    return new BoundingBox(this.getPosition(), this.getWidth(), this.getHeight());
  }

  public PVector getPosition() {
    return this.position;
  }

  public float getHeight() {
    return height;
  }

  public float getWidth() {
    return width;
  }

  public void setMouse(PVector newPos) {
    this.position = newPos;
  }

  /**
   * Make player character reorient towards to
   * where the player clicks the mouse.
   *
   * @param pc player character
   */
  public void MouseEvent(PlayerCharacter pc) {
    //mouse position
    PVector mousePos = this.getPosition().copy();
    // player position
    PVector playerPos = pc.getPosition().copy();
    PVector Pos = mousePos.add(playerPos.mult(-1f));

    pc.direction.add(Pos).normalize();
  }


  /**
   * Check if the player clicked the bounding boxes.
   *
   * @param food the bounding boxes
   * @return if the player clicked boxes
   */
  public boolean foodclick(FoodCharacter food) {
    BoundingBox bounce = food.getBoundingBox();

    boolean clicked = getBoundingBox().collides(bounce);

    return clicked;

  }

  public void remover(FoodCharacter food) {
    window.removeFood(food);
    //System.out.println("remove");
  }

  @Override
  public void draw(Window window) {
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    window.ellipse(this.position.x, this.position.y, this.width, this.height);
  }
}
