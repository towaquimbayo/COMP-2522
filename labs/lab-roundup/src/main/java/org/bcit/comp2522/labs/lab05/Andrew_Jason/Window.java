package org.bcit.comp2522.labs.lab05.Andrew_Jason;

import java.util.ArrayList;
import java.util.Random;

import org.bcit.comp2522.labs.lab05.Flock;
import processing.core.PApplet;


/**
 * Flocking: An implementation of Craig Reynold's Boids program to
 * simulate the flocking behavior of birds. Each boid steers itself
 * based on rules of avoidance, alignment, and coherence.
 *
 * @author Daniel Shiffman, extended and refactored by paul_bucci
 * @version 1.0
 */
public class  Window extends PApplet {
  ArrayList<org.bcit.comp2522.labs.lab05.Flock> flocks;
  int numFlocks = 6;
  int numBoidsPerFlock = 10;

  /**
   * Runs before applet starts.
   */
  public void setup() {
    Random generator = new Random();
    flocks = new ArrayList<org.bcit.comp2522.labs.lab05.Flock>();
    for (int i = 0; i < numFlocks; i++) {
      org.bcit.comp2522.labs.lab05.Flock flock = new org.bcit.comp2522.labs.lab05.Flock(this, generator);
      // Add an initial set of boids into the system
      for (int j = 0; j < numBoidsPerFlock; j++) {
        flock.addBoid();
      }
      flocks.add(flock);
    }
  }

  /**
   * Runs on each frame.
   */
  public void draw() {
    background(20);

    for (org.bcit.comp2522.labs.lab05.Flock flock : flocks) {
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

  public void checkHome() {
//    for (Flock flock : flocks) {
//      for (IMoveable boid : flock.boids) {
//        for (Flock f : flocks) {
//          if (f.touchedHome(boid)) {
//            AbstractObserver newBoid = (AbstractObserver) boid;
//            f.registerObserver(newBoid);
//            flock.unregisterObserver(newBoid);
//          }
//        }
//      }
//    }
  }

  public void settings() {
    size(640, 360);
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