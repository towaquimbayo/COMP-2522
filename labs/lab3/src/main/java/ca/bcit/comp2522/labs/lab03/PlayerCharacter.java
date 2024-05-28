package ca.bcit.comp2522.labs.lab03;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PVector;

/**
 * The Player's main character on screen. It also serves as an example of
 * a Singleton design pattern. Strictly speaking, as designed here, it
 * violates the Open-Closed principle, but I'm including it regardless
 * so that we can talk about Singletons with a real example.
 *
 * @author Towa, Dinuja
 * @version 1.0
 */
public class PlayerCharacter extends AbstractCharacter {
  // PlayerCharacter object, static because its only 1
  private static PlayerCharacter playerCharacter;
  // Tail Factor constant
  private static final float TAIL_FACTOR = -5f;
  // Tail Count constant
  private static final int TAIL_COUNT = 3;
  // HEX Code for Red
  protected static final int TWO_HUNDRED = 200;

  /**
   * PlayerCharacter constructor that passes its values into
   * the AbstractCharacter constructor as well.
   *
   * @param position for position of the PlayerCharacter
   * @param direction for direction of the PlayerCharacter
   * @param window for Window scene
   */
  private PlayerCharacter(PVector position, PVector direction, Window window) {
    super(position, direction, window);
    this.color = new Color(TWO_HUNDRED, 0, 0);
  }

  /**
   * Getting the instance of a PlayerCharacter.
   *
   * @param position current centrepoint position
   * @param direction unit vector (length of 1) pointing in current direction
   * @param window scene window
   * @return returns new PlayerCharacter
   */
  public static PlayerCharacter getInstance(PVector position, PVector direction, Window window) {
    if (playerCharacter == null) {
      playerCharacter = new PlayerCharacter(position, direction, window);
    }
    return playerCharacter;
  }

  /**
   * Remove food when player collided with food.
   *
   * @param c for Collidable Object
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    if (c instanceof FoodCharacter) {
      this.window.removeFood((FoodCharacter) c);
      this.width += 1f;
      this.height += 1f;
    }
  }

  /**
   * Draws the player's character.
   *
   * @param window current scene window
   */
  @Override
  public void draw(Window window) {
    Tail tail = new Tail(
        this.position.copy(),
        this.direction.copy().mult(TAIL_FACTOR),
        this.width,
        this.height,
        TAIL_COUNT
    );
    tail.draw(window);
    window.fill(color.getRed(), color.getGreen(), color.getGreen());
    window.ellipse(this.position.x,
        this.position.y,
        this.width,
        this.height);
  }

  /**
   * Removed food that was clicked by the mouse.
   *
   * @param characters for Character ArrayList in Window
   * @param mouse for Mouse object
   */
  public void removedClickedFood(ArrayList<AbstractCharacter> characters, Mouse mouse) {
    // Checks every FoodCharacter in the list and removes the food if it's within the range
    boolean isRemoved = false;
    for (AbstractCharacter a : characters) {
      if (a instanceof FoodCharacter && !isRemoved) {
        float foodX = a.position.x;
        float foodY = a.position.y;

        if ((foodX > mouse.xpos - mouse.diameter) && (foodX < mouse.xpos + mouse.diameter)
                && (foodY > mouse.ypos - mouse.diameter) && (foodY < mouse.ypos + mouse.diameter)) {
          super.window.removeFood((FoodCharacter) a);
          isRemoved = true;
        }
      }
    }
  }

  /**
   * Change direction of the mouse when clicked.
   *
   * @param mousePointer for PVector Mouse
   * @param playerPointer for PVector Player
   */
  public void changeDirection(PVector mousePointer, PVector playerPointer) {
    // targetDirection = clicked.add(playerPost)
    this.direction = mousePointer.add(playerPointer.mult(-1f)).normalize();
  }
}
