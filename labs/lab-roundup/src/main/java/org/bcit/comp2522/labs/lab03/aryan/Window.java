package org.bcit.comp2522.labs.lab03.aryan;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;


/**
 * Lab-03 starter code.
 * Runs the applet for the Lab-03.
 *
 * @author paul_bucci, Aryan Jand, Amarra Hong
 * @version 4.0 September 25, 2022
 */
public class Window extends PApplet implements IHoverable {
  private final ArrayList<IDrawable> drawables = new ArrayList<>();
  private final ArrayList<AbstractCharacter> characters = new ArrayList<>();
  private final ArrayList<ICollidable> collidables = new ArrayList<>();
  private final ArrayList<FoodCharacter> addFoodQueue = new ArrayList<>();
  private final ArrayList<FoodCharacter> removeFoodQueue = new ArrayList<>();

  private PlayerCharacter player;
  private Mouse mouse;
  private final int numberOfStartingFood = 25;
  private int currentNumberFood = 0;
  private final int maxFood = 200;

  private final int mouseDiameter = 40;

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
    // Initializing the mouse
    Mouse mouse = new Mouse(mouseX, mouseY, mouseDiameter, this);
    addMouse(mouse);
    // Initialize food
    for (int i = 0; i < numberOfStartingFood; i++) {
      PVector foodDirection = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      PVector foodPosition = new PVector(random(width), random(height));
      FoodCharacter food = new FoodCharacter(foodPosition, foodDirection, this);
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
   * Called to remove food characters when the mouse position is similar to food character .
   */
  public void removeCharacter() {
    boolean bk = false;
    for (ICollidable food : characters) {
      boolean boo = mouseHover(food);
      if (food instanceof FoodCharacter && boo && !bk) {
        removeFood((FoodCharacter) food);
        bk = true;
      }
    }
  }

  /**
   * Called to reorient the player character towards an action/event.
   */
  public void reorientCharacter() {
    PVector mouseA = new PVector(mouseX, mouseY);
    PVector playerB = new PVector(player.position.x, player.position.y);
    player.direction = mouseA.add(playerB.mult(-1f)).normalize();
  }

  @Override
  public boolean mouseHover(ICollidable food) {
    PVector fc = new PVector(food.getPosition().x, food.getPosition().y);
    boolean boo = (fc.x - food.getWidth() > mouseX - mouseDiameter
            && fc.x + food.getWidth() < mouseX + mouseDiameter)
            && (fc.y - food.getHeight() > mouseY - mouseDiameter
            && fc.y + food.getHeight() < mouseY + mouseDiameter);
    return boo;
  }

  /**
   * Is called when the mouse is pressed / clicked.
   */
  @Override
  public void mousePressed() {
    reorientCharacter();
    removeCharacter();
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

    for (AbstractCharacter c : characters) {
      c.move(this);
    }
    for (IDrawable d : drawables) {
      d.draw(this);
      mouse.move(this);
    }
  }

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(600, 750);
  }

  public void addMouse(Mouse mouse) {
    this.mouse = mouse;
    this.drawables.add(mouse);
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

  /**
   * Used to add food to the ArrayLists.
   *
   * @param food FoodCharacter
   */
  public void addFood(FoodCharacter food) {
    addFoodQueue.add(food);
  }

  /**
   * Used to remove food to the ArrayLists.
   *
   * @param food FoodCharacter
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
