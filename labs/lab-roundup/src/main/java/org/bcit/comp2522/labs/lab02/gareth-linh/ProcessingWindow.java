package org.comp2522;

import processing.core.PApplet;

/**
 * Class for the Processing Window to be displayed on screen.
 */
public class ProcessingWindow extends PApplet {

  public void settings() {
    size(500, 500);
  }

  public void draw() {
    ellipse(mouseX, mouseY, 50, 50);
  }

  public void mousePressed() {
    background(64);
  }

  /**
   * Main function.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    String[] processingArgs = {"processingWindow"};
    ProcessingWindow processingWindow = new ProcessingWindow();
    PApplet.runSketch(processingArgs, processingWindow);
  }
}