package org.bcit.comp2522.labs.lab03.noufil_siwoon;

import processing.core.PVector;

import java.awt.Window;
import java.awt.*;
import java.util.Date;

/**
 * The Food's character on screen.
 *
 * @author paul_bucci & Siwoon Lim & Noufil Saqib
 * @version 1.0
 */
public class FoodCharacter extends AbstractCharacter {
  Date lastSpawn;
  protected static final int SPAWN_DELAY = 2000;
  protected static final int ROTATE_DEG = 100;
  protected int fillColour = 255;

  /**
   * Constructor for FoodCharacter.
   *
   * @param position center-point position
   * @param direction unit vector pointing in direction of movement
   * @param window current scene window
   */
  public FoodCharacter(PVector position, PVector direction, java.awt.Window window) {
    super(position, direction, window);
    this.lastSpawn = new Date();
    this.color = new Color(fillColour, fillColour, fillColour);
  }

  /**
   * Draws the Food character.
   *
   * @param window current scene window.
   */
  @Override
  public void draw(Window window) {
    window.fill(color.hashCode());
    window.rect(this.position.x,
            this.position.y,
            this.width,
            this.height);
  }

  /**
   * CollideBehaviour method detects collision between two food characters.
   * TODO2:
   * The addFood method does not blow up because it
   * modifies the ArrayList after it is done being
   * iterated through.
   * The addNotThreadSafeFood method blows up because
   * it is modifying the ArrayLists while it is being
   * iterated through.
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    Date now = new Date();
    if (now.getTime() - this.lastSpawn.getTime() > SPAWN_DELAY) {
      this.lastSpawn = now;
      this.bounce((float) Math.PI / 2);

      FoodCharacter f = new FoodCharacter(this.position.copy(),
              this.direction.copy().rotate(ROTATE_DEG), window);
      this.window.addFood(f);
    }
  }

}
