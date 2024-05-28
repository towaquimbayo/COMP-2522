package org.comp2522;

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
 * The goal for this file is to set up the graph in black color
 * and add circle balls into the graph with a move function and
 * collide function which allows the ball to move around
 * the graph and collide with each other.
 * @author Gareth & Linh
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

  Mouse mouse = new Mouse(mouseX, mouseY, 50, numBalls + 1, balls, this);

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

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(0);
    for (Ball ball : balls) {
      ball.collide();
      ball.move();
      drawCircle(ball.getX(), ball.getY(), ball.getDiameter());
    }
    mouse.collide();
    mouse.setX(mouseX);
    mouse.setY(mouseY);
    drawCircle(mouse.getX(), mouse.getY(), mouse.getDiameter());

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
