package org.bcit.comp2522.labs.lab05;

import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;


/**
 * Flocking: An implementation of Craig Reynold's Boids program to
 * simulate the flocking behavior of birds. Each boid steers itself
 * based on rules of avoidance, alignment, and coherence.
 *
 * @author Daniel Shiffman, extended and refactored by paul_bucci
 * @version 1.0
 */
public class Window extends PApplet {
  ArrayList<Flock> flocks;
  ArrayList<Flock> removeFromFlockQueue;
  ArrayList<Flock> addToFlockQueue;
  ArrayList<Boid> replaceBoidQueue;

  int numFlocks = 6;
  int numBoidsPerFlock = 10;

  /**
   * Runs before applet starts.
   */
  public void setup() {
    Random generator = new Random();
    removeFromFlockQueue = new ArrayList<Flock>();
    addToFlockQueue = new ArrayList<Flock>();
    replaceBoidQueue = new ArrayList<Boid>();
    flocks = new ArrayList<Flock>();
    for (int i = 0; i < numFlocks; i++) {
      Flock flock = new Flock(this, generator);
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
    for (Flock flock : flocks) {
      flock.run(this, flocks);
    }
    if (replaceBoidQueue.size() > 0
        && removeFromFlockQueue.size() > 0
        && addToFlockQueue.size() > 0) {
      rearrangeBoids();
      replaceBoidQueue.clear();
      removeFromFlockQueue.clear();
      addToFlockQueue.clear();
    }
  }

  public void unregisterFlockQueue(Flock oldFlock) {
    removeFromFlockQueue.add(oldFlock);
  }

  public void registerFlockQueue(Flock newFlock, Boid boid) {
    addToFlockQueue.add(newFlock);
    replaceBoidQueue.add(boid);
  }


  /**
   * RearrangeBoids method will add and remove each of the respective
   * boids from the boids arraylist.
  */
  public void rearrangeBoids() {
    int size = replaceBoidQueue.size();
    for (int i = 0; i < size; i++) {
      Boid boid = replaceBoidQueue.get(i);
      removeFromFlockQueue.get(i).getBoids().remove(boid);
      addToFlockQueue.get(i).getBoids().add(boid);
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