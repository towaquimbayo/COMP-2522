package org.bcit.comp2522.labs.lab05.Towa_Alex;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Flocking: An implementation of Craig Reynold's Boids program to
 * simulate the flocking behavior of birds. Each boid steers itself
 * based on rules of avoidance, alignment, and coherence.
 *
 * @author Daniel Shiffman, extended and refactored by paul_bucci and Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class Window extends PApplet {
  /** Flock ArrayList. */
  private ArrayList<Flock> flocks;
  /** Home ArrayList. */
  private final ArrayList<Home> homeArrayList = new ArrayList<>();
  /** Number of Flocks. */
  private static final int NUM_FLOCKS = 6;
  /** Number of boids per flock. */
  private static final int NUM_BOIDS_PER_FLOCK = 10;
  /** Draw Background value. */
  private static final int BACKGROUND = 20;
  /** Window settings width. */
  private static final int WIDTH = 640;
  /** Window settings height. */
  private static final int HEIGHT = 360;

  /**
   * Runs before applet starts.
   */
  public void setup() {
    Random generator = new Random();
    flocks = new ArrayList<>();
    for (int i = 0; i < NUM_FLOCKS; i++) {
      Flock flock = new Flock(this, generator);
      // Add an initial set of boids into the system
      for (int j = 0; j < NUM_BOIDS_PER_FLOCK; j++) {
        flock.addBoid();
      }
      homeArrayList.add(flock.home);
      flocks.add(flock);
    }

    for (Flock f : flocks) {
      f.setHomesList(homeArrayList);
      f.addOtherFlocksToList(flocks);
    }
  }

  /**
   * Runs on each frame.
   */
  public void draw() {
    background(BACKGROUND);
    for (Flock flock : flocks) {
      flock.run(this);
    }
  }

  /**
   * Runs only if mouse is clicked.
   */
  public void mousePressed() {
    for (Flock flock : flocks) {
      flock.checkClick(mouseX, mouseY);
    }
  }

  /**
   * Settings method to set screen.
   */
  public void settings() {
    size(WIDTH, HEIGHT);
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