package org.bcit.comp2522.labs.lab02.Taylor_Nash;

import processing.core.PApplet;

public class ProcessingWindow extends PApplet {

  public void settings() {
    size(500, 500);
  }

  public void draw() {
    ellipse(mouseX, mouseY, 50, 50);
  }

  public void mousePressed() {
    background(37);
  }

  public static void main(String[] args) {
    String[] processingArgs = {"processingWindow"};
    ProcessingWindow processingWindow = new ProcessingWindow();
    PApplet.runSketch(processingArgs, processingWindow);
  }
}