package org.bcit.comp2522.labs.lab02.navdeep_bryant;

import processing.core.PApplet;

/**
 * Lab-02 starter code.
 * Runs the applet for the Lab-02 bouncing
 * balls starter code.
 * Based on code from Keith Peters demonstrating
 * multiple-object collision.
 *
 * @author paul_bucci
 *
 */
public class BouncyBubbles extends PApplet {

  /* number of balls to display on screen */
  int numBalls = 12;
  /* error around edge of ball */
  float spring = 0.05f;
  /* acceleration downwards */
  float gravity = 0.03f;
  /* energy lost to other balls */
  float friction = -0.9f;
  /* contains all the balls on screen */
  Ball[] balls = new Ball[numBalls];

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    /* Create a new set of balls with random sizes. */
    for (int i = 0; i < numBalls; i++) {
      balls[i] = new Ball(
        random(width),
        random(height),
        random(30, 70),
        i,
        balls,
        this
      );
    }
    noStroke();
    fill(255, 204);
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

  public void showCursorBall(float diameter) {
    ellipse(mouseX, mouseY, diameter, diameter);
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(0);
    for (int n = 0; n < numBalls; n++) {
      Ball ball = balls[n];
      ball.collide();
      ball.move();
      if (n < numBalls - 1) {
        drawCircle(ball.getX(), ball.getY(), ball.getDiameter());
      } else {
        ball.setX(mouseX);
        ball.setY(mouseY);
        showCursorBall(ball.getDiameter());
      }
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

/**
 * Once the program is running, a processing window opens and generates 12 instances of the ball object.
 * 12 balls appear on screen of random height and width, the balls lose energy
 * on each bounce due to friction. After some time, all the balls lose energy and stop bouncing.
 */
