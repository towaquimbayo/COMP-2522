package org.bcit.comp2522.labs.lab03.Towa_Dinuja;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/**
 * Lab-03 starter code.
 * Runs the applet for the Lab-03.
 *
 * @author Towa, Dinuja
 * @version 1.0
 */
public class Window extends PApplet {
  // ArrayList for Drawables
  private final ArrayList<IDrawable> drawables = new ArrayList<>();
  // ArrayList for AbstractCharacters
  private final ArrayList<AbstractCharacter> characters = new ArrayList<>();
  // ArrayList for ICollidables
  private final ArrayList<ICollidable> collidables = new ArrayList<>();
  // ArrayList for FoodCharacters when adding individual object
  private final ArrayList<FoodCharacter> addFoodQueue = new ArrayList<>();
  // ArrayList for FoodCharacters when removing each food
  private final ArrayList<FoodCharacter> removeFoodQueue = new ArrayList<>();
  // PlayerCharacter
  private PlayerCharacter player;
  // Static constant variable for the number of starting food
  private static final int NUMBER_OF_STARTING_FOOD = 25;
  // Value for counting number of food
  private int currentNumberFood = 0;
  // Value for MAX_FOOD
  private static final int MAX_FOOD = 250;
  // Mouse diameter constant
  protected static final int MOUSE_DIAM = 20;
  // 640 settings value
  protected static final int SIX_FORTY = 640;
  // 360 settings value
  protected static final int THREE_SIXTY = 360;
  // 2 Floating value
  private static final float TWO = 2f;
  // 8 Floating value
  private static final float EIGHT = 8f;
  // Mouse object
  protected Mouse mouse;

  /**
   * Draw Mouse cursor using the ellipse method.
   */
  public void drawMouse(float x, float y, float diam) {
    ellipse(x, y, diam, diam);
  }

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    // Initialize player
    PVector playerDirection = new PVector(1, 1).normalize();
    PVector playerPosition = new PVector(width / TWO, height / TWO);
    // Singleton player
    PlayerCharacter player = PlayerCharacter.getInstance(playerPosition, playerDirection, this);
    addPlayer(player);

    // Initialize food
    for (int i = 0; i < NUMBER_OF_STARTING_FOOD; i++) {
      PVector foodDirection = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      PVector foodPosition = new PVector(random(width), random(height));
      FoodCharacter food = new FoodCharacter(foodPosition, foodDirection, this);
      addFood(food);
    }

    // Creates the mouse object
    mouse = new Mouse((float) mouseX, (float) mouseY, MOUSE_DIAM, 0, this);
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

    if (event.getKeyCode() == RIGHT) {
      player.rotate((float) Math.PI / EIGHT);
    } else if (event.getKeyCode() == LEFT) {
      player.rotate((float) -Math.PI / EIGHT);
    }
  }

  /**
   * Mouse clicked event function.
   * If there is a FoodCharacter within the range where the mouse
   * clicked x and y coordinates are clicked at then remove the FoodCharacter.
   *
   * @param event for Mouse Event
   */
  @Override
  public void mouseClicked(MouseEvent event) {
    super.mouseClicked(event);
    PVector mousePointer = new PVector(mouseX, mouseY);
    PVector playerPointer = new PVector(player.position.x, player.position.y);

    // Call changeDirection method
    player.changeDirection(mousePointer, playerPointer);

    // Call removedClickedFood method
    player.removedClickedFood(characters, mouse);
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

    for (FoodCharacter f : removeFoodQueue) {
      removeThreadSafeFood(f);
    }
    removeFoodQueue.clear();

    for (ICollidable a : collidables) {
      a.getBoundingBox().draw(this);
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

    // Draw the mouse object on the window
    mouse.draw(this);
    drawMouse(mouse.xpos, mouse.ypos, mouse.diameter);
  }

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(SIX_FORTY, THREE_SIXTY);
  }

  /**
   * Adds a player to all the relevant arrays.
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
   * Adds food to all of relevant arrays.
   *
   * @param food consumed by player
   */
  private void addThreadSafeFood(FoodCharacter food) {
    if (this.currentNumberFood < MAX_FOOD) {
      this.currentNumberFood += 1;
      this.drawables.add(food);
      this.characters.add(food);
      this.collidables.add(food);
    }
  }

  /**
   * Removes food to all of relevant arrays.
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

  /**
   * BAD METHOD added for illustrative purposes only.
   *
   * @param food the food to add (and watch the JVM blow up on)
   */
  public void addNotThreadSafeFood(FoodCharacter food) {
    if (this.currentNumberFood < MAX_FOOD) {
      this.currentNumberFood += 1;
      this.drawables.add(food);
      this.characters.add(food);
      this.collidables.add(food);
    }
  }

  /**
   * adds food.
   *
   * @param food for FoodCharacter
   */
  public void addFood(FoodCharacter food) {
    addFoodQueue.add(food);
  }

  /**
   * Removes food.
   *
   * @param food for FoodCharacter
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
