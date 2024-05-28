package org.bcit.com2522.labs.lab03;

import java.util.Date;

import processing.core.PVector;

/**
 * The Food's character on screen.
 *
 * @author paul_bucci
 * @version 1.0
 */
public class FoodCharacter extends AbstractCharacter {
  Date lastSpawn;
  int spawnDelay;
  int fillColour = 255;

  /**
   * Constructor for FoodCharacter.
   *
   * @param position centrepoint position
   * @param direction unit vector pointing in direction of movement
   * @param window current scene window
   */
  public FoodCharacter(PVector position, PVector direction, Window window) {
    super(position, direction, window);
    this.lastSpawn = new Date();
    this.spawnDelay = 2000;
  }

  /**
   * Draws the Food character.
   *
   * @param window current scene window.
   */
  @Override
  public void draw(Window window) {
    window.fill(this.fillColour);
    window.rect(this.position.x,
        this.position.y,
        this.width,
        this.height);
  }

  /**
   * Method for colliding behaviour.
   *
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    this.bounce((float) Math.PI / 2);
    Date now = new Date();
    if (now.getTime() - this.lastSpawn.getTime() > spawnDelay) {
      this.lastSpawn = now;
      PVector position = this.position.copy();
      PVector direction = this.direction.copy().rotate((float) Math.PI);
      FoodCharacter fd = new FoodCharacter(position, direction, window);
      window.addFood(fd);
    }
  }

}
