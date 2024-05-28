package org.bcit.comp2522.labs.lab03.kent_linh;

import processing.core.PVector;

import java.util.Date;

/**
 * The Food's character on screen.
 *
 * @author paul_bucci
 * @author kent concengco, linh nguyen
 * @version 1.0 September 14, 2022
 * @version 2.0 September 28, 2022
 */
public class FoodCharacter extends AbstractCharacter {
  private final float clickTolerance = 10f;
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
    this.spawnDelay = 5000;
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
   * addFood() works because it modifies the ArrayList called "addFoodQueue" before it's iterated.
   * On the other hand, addNotThreadSafeFood() is not working because
   * it modifies the arrays drawables, collidables, and characters
   * while the program is iterating over them. This can produce an infinite loop
   * under the right conditions.
   *
   * @param c as ICollidable
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    final float half_pi = (float) Math.PI / 2;
    Date now = new Date();
    if (now.getTime() - this.lastSpawn.getTime() > spawnDelay) {
      this.lastSpawn = now;
      if (c instanceof FoodCharacter) {
        this.bounce(half_pi);
        FoodCharacter newFood = new FoodCharacter(this.position.copy(),
            this.direction.copy(),
            this.window);
        newFood.bounce(-1 * half_pi);
        window.addFood(newFood);
      }
    }
  }

  /**
   * Adds the instance of food that is clicked by the mouse
   * to the removeFoodQueue, which will delete it in the
   * next draw() function call.
   *
   * @param mouse as mouse position during click
   */
  public void clicked(Mouse mouse) {
    PVector mousePos = new PVector(mouse.getPosition().x, mouse.getPosition().y);
    boolean isInside = mousePos.x <= this.position.x + this.width / 2 + clickTolerance
        && mousePos.x >= this.position.x - this.width / 2 - clickTolerance
        && mousePos.y <= this.position.y + this.height / 2 + clickTolerance
        && mousePos.y >= this.position.y - this.width / 2 - clickTolerance;
    if (isInside) {
      window.removeFood(this);
    }
  }

}
