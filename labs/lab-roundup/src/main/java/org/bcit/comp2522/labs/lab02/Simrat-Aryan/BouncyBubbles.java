import processing.core.PApplet;

/**
 * Lab-02 starter code.
 * Runs the applet for the Lab-02 bouncing
 * balls starter code.
 * Based on code from Keith Peters demonstrating
 * multiple-object collision.
 *
 * <p></p>
 * BouncyBubbles extends PApplet.
 * From the PApplet class it uses many methods.
 * Settings method sets the size of the window.
 * The setup method initialize balls with random height & width.
 * The draw method calculates the collision between the balls and moves them.
 * After which it draws all the balls to the Processing window.
 * This drawing is done in the drawCircle method.
 *
 * @author paul_bucci, Simrat Grewal & Aryan Jand
 * @version 1.0.0
 */
public class BouncyBubbles extends PApplet {

  /**
   * Number of balls to display on screen.
   */
  int numBalls = 15;
  /**
   * Error around edge of ball.
   */
  float spring = 0.05f;
  /**
   * Acceleration downwards.
   */
  float gravity = 0.03f;
  /**
   * Energy lost to other balls.
   */
  float friction = -0.9f;
  /**
   * Contains all the balls on screen.
   */
  Collidable[] collidables = new Collidable[numBalls];

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {

    /* Create a new set of balls with random sizes. */
    for (int i = 0; i < numBalls - 1; i++) {
      collidables[i] = new Ball(random(width), random(height), i, random(60,
              100), collidables, this);
    }

    collidables[numBalls - 1] = new Mouse(random(width), random(height),
            numBalls, random(30, 70), collidables, this);
    noStroke();
    fill(255, 204);
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(0);
    for (Collidable c : collidables) {
      c.collide();
      c.move();
      c.draw();
    }
  }

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(640, 360);
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"bouncyBubbles"};
    BouncyBubbles bouncyBubbles = new BouncyBubbles();
    PApplet.runSketch(appletArgs, bouncyBubbles);
  }
}
