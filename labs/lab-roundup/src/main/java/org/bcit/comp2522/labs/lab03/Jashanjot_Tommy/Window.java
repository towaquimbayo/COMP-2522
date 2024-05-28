package org.bcit.com2522.labs.lab03;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * Lab-03 starter code.
 * Runs the applet for the Lab-03.
 *
 * @author paul_bucci
 * @author Tommy Nguyen
 * @author Jashanjot Singh
 * @version 1.0 September 14, 2022
 */
public class Window extends PApplet {
  private ArrayList<IDrawable> drawables = new ArrayList<>();
  private ArrayList<AbstractCharacter> characters = new ArrayList<>();
  private ArrayList<ICollidable> collidables = new ArrayList<>();
  private ArrayList<FoodCharacter> addFoodQueue = new ArrayList<>();
  private ArrayList<FoodCharacter> removeFoodQueue = new ArrayList<>();
  private ArrayList<FoodCharacter> currentFoods = new ArrayList<>();
  private PlayerCharacter player;
  private Mouse mouse;
  private FoodCharacter food;
  private int numberOfStartingFood = 25;
  private int currentNumberFood = 0;
  private int maxFood = 250;
  private boolean isClicked = false;

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

    // Initialize mouse
    PVector mousePosition = new PVector(mouseX, mouseY);
    mouse = new Mouse(mousePosition, this);
    addMouse(mouse);

    // Initialize food
    for (int i = 0; i < numberOfStartingFood; i++) {
      PVector foodDirection = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      PVector foodPosition = new PVector(random(width), random(height));
      food = new FoodCharacter(foodPosition, foodDirection, this);
      addFood(food);
    }
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

    // Remove food here
    for (FoodCharacter f : removeFoodQueue) {
      removeThreadSafeFood(f);
    }
    removeFoodQueue.clear();

    for (FoodCharacter f : addFoodQueue) {
      addThreadSafeFood(f);
    }
    addFoodQueue.clear();
    
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

    if (isClicked = true) {
      for (FoodCharacter f : currentFoods) {
        f.clicked(this);
      }
      isClicked = false;
    }

    for (IDrawable d : drawables) {
      d.draw(this);
    }



    // Once mouse is drawn, make it move!
    mouse.move(this);

  }

  /**
   * Check if mouse is pressed and changes the direction of player to mouse.
   */
  public void mousePressed() {
    PVector playerPosition = new PVector(player.position.x, player.position.y);
    PVector mousePosition = new PVector(mouse.position.x, mouse.position.y);
    player.direction = mousePosition.add(playerPosition.mult(-1f)).normalize();

    isClicked = true;
    System.out.println(food.position);
  }

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(640, 360);
  }

  /**
   * Adds a player to all of the relevant arrays.
   *
   * @param player human-controllable dot
   */
  public void addPlayer(PlayerCharacter player) {
    this.player = player;
    this.drawables.add(player);
    this.characters.add(player);
    this.collidables.add(player);
  }

  /**
   * Adds the mouse into the window.
   *
   * @param mouse mouse pointer
   */
  public void addMouse(Mouse mouse) {
    this.mouse = mouse;
    this.drawables.add(mouse);
  }

  /**
   * Adds food to all of the relevant arrays.
   *
   * @param food consumed by player
   */
  private void addThreadSafeFood(FoodCharacter food) {
    if (this.currentNumberFood < maxFood) {
      this.currentNumberFood += 1;
      this.drawables.add(food);
      this.characters.add(food);
      this.collidables.add(food);
      this.currentFoods.add(food);
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
   * Add food to an array to be added into the window.
   *
   * @param food the food to be added
   */
  public void addFood(FoodCharacter food) {
    addFoodQueue.add(food);
  }

  // TODO: add a method for removing Food

  /**
   * Remove the food character from window.
   *
   * @param food the food to remove
   */
  public void removeThreadSafeFood(FoodCharacter food) {
    if (this.currentNumberFood < maxFood && addFoodQueue.size() > 0) {
      this.currentNumberFood -= 1;
      this.drawables.remove(food);
      this.characters.remove(food);
      this.collidables.remove(food);
    }
  }

  /**
   * Add the collided food character to queue.
   *
   * @param c the collided food to remove
   */
  public void removeFoodCollision(ICollidable c) {
    FoodCharacter remove = (FoodCharacter) c;
    removeFoodQueue.add(remove);
  }

  /**
   * Add the food character to queue.
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
