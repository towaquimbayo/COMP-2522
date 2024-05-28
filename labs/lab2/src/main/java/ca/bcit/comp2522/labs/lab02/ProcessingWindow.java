package ca.bcit.comp2522.labs.lab02;

import processing.core.PApplet;

/**
 * Processing Window class that extends the PApplet class packages.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class ProcessingWindow extends PApplet {

  /**
   * Used to represent the width and height of the window.
   */
  public static final int DIMENSIONS = 500;

  /**
   * Used to represent the initial placement of the mouse
   * in terms of x, y to the origin.
   */
  public static final int INITPOSITION = 50;

  /**
   * Used to represent the background color of the window.
   */
  public static final int RGBBACKGROUND = 64;

  /**
   * Sets the width and size of the window to 500 px.
   */
  public void settings() {
    size(DIMENSIONS, DIMENSIONS);
  }

  /**
   * Places the x, y coordinates of the mouse to 50px by 50px
   * from origin.
   */
  public void draw() {
    ellipse(mouseX, mouseY, INITPOSITION, INITPOSITION);
  }

  /**
   * Turns the background to grey when the mouse is clicked.
   */
  public void mousePressed() {
    background(RGBBACKGROUND);
  }

  /**
   * Main method.
   *
   * @param args for any arguments
   */
  public static void main(String[] args) {
    String[] processingArgs = {"processingWindow"};
    ProcessingWindow processingWindow = new ProcessingWindow();
    PApplet.runSketch(processingArgs, processingWindow);
  }
}