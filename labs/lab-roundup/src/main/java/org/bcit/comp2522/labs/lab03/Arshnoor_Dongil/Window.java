package org.bcit.comp2522.labs.lab03.Arshnoor_Dongil;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * Lab-03 starter code.
 * Runs the applet for the Lab-03.
 *
 * @author paul_bucci
 * @version 1.0 September 14, 2022
 */
public class Window extends PApplet {
  private ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();
  private ArrayList<AbstractCharacter> characters = new ArrayList<AbstractCharacter>();
  private ArrayList<ICollidable> collidables = new ArrayList<ICollidable>();
  private ArrayList<FoodCharacter> addFoodQueue = new ArrayList<FoodCharacter>();

  private ArrayList<FoodCharacter> removeFoodQueue = new ArrayList<FoodCharacter>();
  private PlayerCharacter player;
  private int numberOfStartingFood = 25;
  private int currentNumberFood = 0;
  private int maxFood = 250;

  private Mouse mouse;

  /**
   * Called once at the beginning of the program.
   * Initializes all the objects.
   *
   *
   */
  public void setup() {
    // Initialize player
    PVector playerDirection = new PVector(1, 1).normalize();
    PVector playerPosition = new PVector(width / 2f, height / 2f);
    // Singleton player
    PlayerCharacter player = PlayerCharacter.getInstance(playerPosition, playerDirection, this);
    addPlayer(player);

    // Initialize food
    for (int i = 0; i < numberOfStartingFood; i++) {
      PVector foodDirection = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      PVector foodPosition = new PVector(random(width), random(height));
      FoodCharacter food = new FoodCharacter(foodPosition, foodDirection, this);
      addFood(food);
    }

    // Initialize Mouse
    PVector startPosition = new PVector(mouseX, mouseY);
    Mouse mouse = new Mouse(startPosition, 15, 15, this);
    addMouse(mouse);
  }

  /**
   * Called on a key press.
   *
   * @param event is the keyboard event
   */
  @Override
  public void keyPressed(KeyEvent event) {
    super.keyPressed(event);
    if (player == null) {
      return;
    }
    switch (event.getKeyCode()) {
      case RIGHT:
        player.rotate((float) Math.PI / 8f);
        break;
      case LEFT:
        player.rotate((float) -Math.PI / 8f);
        break;
      default:
        break;
    }
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(10);
    PVector mousePosition = new PVector(mouseX, mouseY);
    mouse.setMouse(mousePosition);

    for (FoodCharacter f : addFoodQueue) {
      addThreadSafeFood(f);
    }
    addFoodQueue.clear();
    // Remove food here

    for (FoodCharacter f : removeFoodQueue) {
      removeThreadSafeFood(f);
    }
    removeFoodQueue.clear();

    for (ICollidable a : collidables) {
      for (ICollidable b : collidables) {
        if (a.collided(b)) {
          a.collideBehaviour(b);
        }
      }
    }

    for (AbstractCharacter c : characters) {
      c.move(this);
    }
    for (IDrawable d : drawables) {
      d.draw(this);
    }
  }

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(640, 360);
  }

  //processing mouse pressed event
  public void mousePressed() {
    mouse.MouseEvent(player);
    for (ICollidable a : collidables) {
      if (a instanceof FoodCharacter) {
        if (mouse.foodclick((FoodCharacter) a)) {

          mouse.remover((FoodCharacter) a);
        }
      }
    }
  }

  /**
   * Adds a player to  the relevant arrays.
   *
   * @param player human-controllable dot
   */
  public void addPlayer(PlayerCharacter player) {
    this.player = player;
    this.drawables.add(player);
    this.characters.add(player);
    this.collidables.add(player);
  }

  public void addMouse(Mouse mouse) {
    this.mouse = mouse;
    this.drawables.add(mouse);
  }
  /**
   * Adds food to the relevant arrays.
   *
   * @param food consumed by player
   */
  private void addThreadSafeFood(FoodCharacter food) {
    if (this.currentNumberFood < maxFood) {
      this.currentNumberFood += 1;
      this.drawables.add(food);
      this.characters.add(food);
      this.collidables.add(food);
    }
  }


  /**
   * BAD METHOD added for illustrative purposes only.
   *
   * @param food the food to add (and watch the JVM blow up on)
   */
  public void addNotThreadSafeFood(FoodCharacter food) {
    if (this.currentNumberFood < maxFood) {
      this.currentNumberFood += 1;
      this.drawables.add(food);
      this.characters.add(food);
      this.collidables.add(food);
    }
  }

  /**
   * function for adding food.
   *
   *
   * @param food passing food parameter through the addFood
   */

  public void addFood(FoodCharacter food) {

    addFoodQueue.add(food);

  }

  /**
   * Removes food from all of the relevant arrays.
   *
   * @param food consumed by player
   */
  private void removeThreadSafeFood(FoodCharacter food) {
    if (this.currentNumberFood > 0) {
      this.currentNumberFood -= 1;
      this.drawables.remove(food);
      this.characters.remove(food);
      this.collidables.remove(food);
    }
  }

  public void removeFood(FoodCharacter food) {
    removeFoodQueue.add(food);
  }

  // TODO: add a method for removing Food

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"window"};
    Window window = new Window();
    PApplet.runSketch(appletArgs, window);
  }
}
