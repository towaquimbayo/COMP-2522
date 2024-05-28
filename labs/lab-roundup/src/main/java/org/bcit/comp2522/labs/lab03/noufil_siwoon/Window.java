package org.bcit.comp2522.labs.lab03.noufil_siwoon;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;

/**
 * Lab-03 starter code.
 * Runs the applet for the Lab-03.
 *
 * @author paul_bucci & Siwoon Lim & Noufil Saqib
 * @version 1.0 September 23, 2022
 */
public class Window extends PApplet {
  private final ArrayList<IDrawable> drawables = new ArrayList<>();
  private final ArrayList<AbstractCharacter> characters = new ArrayList<>();
  private final ArrayList<ICollidable> collidables = new ArrayList<>();
  private final ArrayList<FoodCharacter> addFoodQueue = new ArrayList<>();
  private final ArrayList<FoodCharacter> removeFoodQueue = new ArrayList<>();
  private PlayerCharacter player;
  private int currentNumberFood = 0;
  private static final int START_FOOD = 25;
  private static final int MAX_FOOD = 250;
  private static final float DIM_HALF = 2f;
  private static final float ROT_FLOAT = 8f;
  private static final int MOUSE_DIA = 25;
  private static final int COLOR_HEX = 10;
  private static final int SCREEN_WIDTH = 640;
  private static final int SCREEN_HEIGHT = 360;
  protected Mouse mouse;

  public void drawCircle(float x, float y, float diameter) {
    ellipse(x, y, diameter, diameter);
  }

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   *
   */
  public void setup() {
    // Initialize player
    PVector playerDirection = new PVector(1, 1).normalize();
    PVector playerPosition = new PVector(width / DIM_HALF, height / DIM_HALF);
    // Singleton player
    PlayerCharacter player = PlayerCharacter.getInstance(playerPosition, playerDirection, this);
    addPlayer(player);

    for (int i = 0; i < START_FOOD; i++) {
      PVector foodDirection = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      PVector foodPosition = new PVector(random(width), random(height));
      FoodCharacter food = new FoodCharacter(foodPosition, foodDirection, this);
      addFood(food);
    }

    this.mouse = new Mouse(mouseX, mouseY, MOUSE_DIA, 0, this);
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
      player.rotate((float) Math.PI / ROT_FLOAT);
    } else if (event.getKeyCode() == LEFT) {
      player.rotate((float) -Math.PI / ROT_FLOAT);
    }
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(COLOR_HEX);
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

    mouse.draw(this);
    drawCircle(mouse.xpos, mouse.ypos, mouse.diameter);
  }

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(SCREEN_WIDTH, SCREEN_HEIGHT);
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
   * Adds food to all the relevant arrays.
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

  public void addFood(FoodCharacter food) {
    addFoodQueue.add(food);
  }

  public void removeFood(FoodCharacter food) {
    removeFoodQueue.add(food);
  }

  /**
   * Method to make mouse can remove food and the PlayerCharacter is reoriented towards the mouse.
   */
  public void mousePressed() {
    PVector pmouse = new PVector(this.mouseX, this.mouseY);
    PVector pplayer = new PVector(player.position.x, player.position.y);

    player.bringPlayerToMouse(pplayer, pmouse);
    player.removeFoodOnClick(mouse, characters);
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
