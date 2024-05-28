package org.bcit.comp2522.labs.lab03.kent_linh;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;

/**
 * Lab-03 starter code.
 * Runs the applet for the Lab-03.
 *
 * @author paul_bucci
 * @author kent_concengco, linh_nguyen
 * @version 1.0 September 14, 2022
 * @version 2.0 September 28, 2022
 */
public class Window extends PApplet {
  private final ArrayList<IDrawable> drawables = new ArrayList<>();
  private final ArrayList<IMovable> movables = new ArrayList<>();
  private final ArrayList<ICollidable> collidables = new ArrayList<>();
  private final ArrayList<IClickable> clickables = new ArrayList<>();
  private final ArrayList<FoodCharacter> addFoodQueue = new ArrayList<>();
  private final ArrayList<FoodCharacter> removeFoodQueue = new ArrayList<>();
  private final int maxFood = 250;
  private PlayerCharacter player;
  private boolean mouseClicked;
  private Mouse mouse;
  private int currentNumberFood = 0;



  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    // Initialize player
    PVector playerDirection = new PVector(1, 1).normalize();
    PVector playerPosition = new PVector(width / 2f, height / 2f);
    // Singleton player
    PlayerCharacter player = PlayerCharacter.getInstance(playerPosition, playerDirection, this);
    addPlayer(player);

    // Initialize food
    int numberOfStartingFood = 20;
    for (int i = 0; i < numberOfStartingFood; i++) {
      PVector foodDirection = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      PVector foodPosition = new PVector(random(width), random(height));
      FoodCharacter food = new FoodCharacter(foodPosition, foodDirection, this);
      addFood(food);
    }

    PVector mousePosition = new PVector(mouseX, mouseY);
    mouse = new Mouse(mousePosition, 10);
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
      case RIGHT -> player.rotate((float) Math.PI / 8f);
      case LEFT -> player.rotate((float) -Math.PI / 8f);
      default -> {
      }
    }
  }

  /**
   * Changes "mouseClicked" variable to true
   * and saves the mouse position after pressing mouse.
   */
  public void mousePressed() {
    mouseClicked = true;
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(10);
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

    for (IMovable m : movables) {
      m.move(this);
    }
    for (IDrawable d : drawables) {
      d.draw(this);
    }

    if (mouseClicked) {
      for (IClickable c : clickables) {
        c.clicked(mouse);
      }
      mouseClicked = false;
    }

  }

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(640, 360);
  }

  /**
   * Adds a player to all the relevant arrays.
   *
   * @param player human-controllable dot
   */
  public void addPlayer(PlayerCharacter player) {
    this.player = player;
    this.drawables.add(player);
    this.movables.add(player);
    this.collidables.add(player);
    this.clickables.add(player);
  }

  /**
   * Adds a mouse to all the relevant arrays.
   *
   * @param mouse human-controllable dot
   */
  public void addMouse(Mouse mouse) {
    this.drawables.add(mouse);
    this.movables.add(mouse);
  }


  /**
   * Adds food to all the relevant arrays.
   *
   * @param food consumed by player
   */
  private void addThreadSafeFood(FoodCharacter food) {
    if (this.currentNumberFood < maxFood) {
      this.currentNumberFood += 1;
      this.drawables.add(food);
      this.movables.add(food);
      this.collidables.add(food);
      this.clickables.add(food);
    }
  }

  /**
   * Removes food to all the relevant arrays.
   *
   * @param food consumed by player
   */
  private void removeThreadSafeFood(FoodCharacter food) {
    if (this.currentNumberFood > 0) {
      this.currentNumberFood -= 1;
      this.drawables.remove(food);
      this.movables.remove(food);
      this.collidables.remove(food);
      this.clickables.remove(food);
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
      this.movables.add(food);
      this.collidables.add(food);
    }
  }

  /**
   * Adds food to the "addFoodQueue" array.
   *
   * @param food the food to add
   */
  public void addFood(FoodCharacter food) {
    addFoodQueue.add(food);
  }

  /**
   * Removes food from the "removeFoodQueue" array.
   *
   * @param food the food to remove
   */
  public void removeFood(FoodCharacter food) {
    removeFoodQueue.add(food);
  }

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
