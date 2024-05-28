package org.bcit.comp2522.labs.lab02.Towa_Alex;

import processing.core.PApplet;

/**
 * Runs the applet for the Lab-02 bouncing
 * balls starter code.
 * Based on code from Keith Peters demonstrating
 * multiple-object collision.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class BouncyBubbles extends PApplet {

  /** Number of balls to display on screen */
  public static final int NUMBALLS = 12;

  /**
   * Used to represent the number one.
   */
  public static final int ONE = 1;

  /**
   * Used to define the random, low.
   */
  public static final int LOW = 30;

  /**
   * Used to define the rgd fill.
   */
  public static final int RGB = 255;

  /**
   * Used to define the alpha fill.
   */
  public static final int ALPHA = 204;

  /**
   * Used to define the width of the window.
   */
  public static final int WIDTH = 640;

  /**
   * Used to define the height of the window.
   */
  public static final int HEIGHT = 360;

  /**
   * Used to define the random, high.
   */
  public static final int HIGH = 70;

  /** Error around edge of ball */
  public final float spring = 0.05f;

  /** Acceleration downwards */
  public final float gravity = 0.03f;

  /** Energy lost to other balls */
  public final float friction = -0.9f;

  /** Set value for Diameter of the cursor */
  public final float diamMouse = 75f;

  /** Array of Collidable objects that contain the balls */
  Collidable[] collideObj = new Collidable[NUMBALLS];

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   * For loop for creating 11 bouncy balls
   * and leave last index open for mouse cursor.
   * After for loop, creates a Mouse object
   * on the last index of the Collidable array.
   * Pass the MouseX and MouseY values for the cursor action,
   * and have the last index value as an ID.
   */
  public final void setup() {
    /* Create a new set of balls with random sizes. */
    for (int i = 0; i < NUMBALLS - ONE; i++) {
      collideObj[i] = new Ball(
        random(width),
        random(height),
        random(LOW, HIGH),
        i,
        collideObj,
        this
      );
    }
    collideObj[NUMBALLS - ONE] = new Mouse(mouseX, mouseY, diamMouse, NUMBALLS - ONE, collideObj, this);
    noStroke();
    fill(RGB, ALPHA);
  }

  /**
   * Draws a circle onscreen at x, y, circle.
   *
   * @param x position of centre of circle
   * @param y position of centre of circle
   * @param diameter width of circle
   */
  public final void drawCircle(float x, float y, float diameter) {
    ellipse(x, y, diameter, diameter);
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public final void draw() {
    background(0);
    for (Collidable collideA : collideObj) {
      collideA.collide();
      collideA.move();
      drawCircle(collideA.getX(), collideA.getY(), collideA.getDiameter());
    }
  }

  /**
   * Called once at the beginning of the program. Sets the
   * width and height of the display of BouncyBubbles.
   */
  public final void settings() {
    size(WIDTH, HEIGHT);
  }

  /**
   * Main method.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"bouncyBubbles"};
    BouncyBubbles bouncyBubbles = new BouncyBubbles();
    PApplet.runSketch(appletArgs, bouncyBubbles);
  }
}
