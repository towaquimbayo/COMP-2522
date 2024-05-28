package org.bcit.comp2522.labs.lab02.Taylor_Nash;

import processing.core.PApplet;

/**
 * Lab-02 starter code.
 * Runs the applet for the Lab-02 bouncing
 * balls starter code.
 * Based on code from Keith Peters demonstrating
 * multiple-object collision.
 * High-level description:
 * Randomly create balls with different sizes and locations.
 * A maximum of 12 balls will be created.
 *
 * @author Nash and Taylor
 * @version 2022 September
 *
 */
public class BouncyBubbles extends PApplet {
  /* number of balls to display on screen */
  int numBalls = 13;
  /* error around edge of ball */
  float spring = 0.05f;
  /* acceleration downwards */
  float gravity = 0.03f;
  /* energy lost to other balls */
  float friction = -0.9f;
  /* contains all the balls on screen */
  Ball[] balls = new Ball[numBalls];
  Mouse mouse = new Mouse();

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    /* Create a new set of balls with random sizes. */
    for (int i = 0; i < numBalls - 1; i++) {
      balls[i] = new Ball(
        random(width),
        random(height),
        random(50, 70),
        i,
        balls,
        this
      );
    }
    balls[numBalls - 1] = new Ball(mouseX, mouseY, 50, numBalls - 1, balls, this);
    noStroke();
    fill(205, 204);
  }

  /**
   * Draws a circle onscreen at x, y, circle.
   *
   * @param x position of centre of circle
   * @param y position of centre of circle
   * @param diameter width of circle
   */
  public void drawCircle(float x, float y, float diameter) {
    ellipse(x, y, diameter, diameter);
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(0);
    cursor(CROSS);
    for (int i = 0; i < balls.length; i++) {
      if (i == balls.length - 1) {
        balls[i].collide();
        balls[i].setX(mouseX);
        balls[i].setY(mouseY);
        drawCircle(balls[i].getX(), balls[i].getY(), 50);
        mouse.printMousePosition(balls[i].getX(), balls[i].getY());
      } else {
        balls[i].collide();
        balls[i].move();
        drawCircle(balls[i].getX(), balls[i].getY(), balls[i].getDiameter());
      }
    }

    System.out.println("mouse X position is " + mouseX
        + " mouse Y position is " + mouseY);
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
