package org.bcit.comp2522.labs.lab05.Andrew_Jason;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.bcit.comp2522.labs.lab05.AbstractObservable;
import org.bcit.comp2522.labs.lab05.AbstractObserver;
import org.bcit.comp2522.labs.lab05.Boid;
import org.bcit.comp2522.labs.lab05.BoidRenderer;
import org.bcit.comp2522.labs.lab05.Home;
import org.bcit.comp2522.labs.lab05.HomeRenderer;
import org.bcit.comp2522.labs.lab05.IMoveable;
import org.bcit.comp2522.labs.lab05.Window;
import processing.core.PVector;


/**
 * The Flock manages a list of Boid objects.
 *
 * @author paul_bucci (based on Daniel Shiffman's Boids)
 * @version 1.0
 */
public class Flock extends AbstractObservable {
  ArrayList<org.bcit.comp2522.labs.lab05.IMoveable> boids; // An ArrayList for all the boids
  Color color;
  PVector position;
  org.bcit.comp2522.labs.lab05.Home home;
  float homeRadius;
  org.bcit.comp2522.labs.lab05.BoidRenderer boidRenderer;
  org.bcit.comp2522.labs.lab05.HomeRenderer homeRenderer;

  /**
   * Constructor for a Flock.
   *
   * @param window the processing window
   * @param generator a random number generator
   */
  public Flock(org.bcit.comp2522.labs.lab05.Window window, Random generator) {
    this.boids = new ArrayList<>(); // Initialize the ArrayList
    this.position = new PVector(
      generator.nextFloat() * window.width,
      generator.nextFloat() * window.height
    );
    this.color = new Color(
      64 + ((int) (generator.nextFloat() * 255 / 2f)),
      64 + ((int) (generator.nextFloat() * 255 / 2f)),
      64 + ((int) (generator.nextFloat() * 255 / 2f))
    );
    PVector velocity = new PVector(
        generator.nextFloat(),
        generator.nextFloat()
    );
    homeRadius = 10f;
    home = new Home(this.position, velocity, homeRadius, color);
    boidRenderer = new BoidRenderer();
    homeRenderer = new HomeRenderer();
  }

  /**
   * Check whether the Home is under the mouse when clicked.
   *
   * @param mouseX from window
   * @param mouseY from window
   */
  public void checkClick(float mouseX, float mouseY) {
    PVector mousePos = new PVector(mouseX, mouseY);
    PVector homePos = home.getPosition();
    if (mousePos.dist(homePos) <= home.getRadius()) {
      notifyObservers();
    }
  }

  public boolean touchedHome(org.bcit.comp2522.labs.lab05.IMoveable boid){
    if (boid.getPosition().dist(home.getPosition()) <= home.getRadius()) {
      return true;
    } else {
      return false;
    }

//    for (IMoveable b: boids) {
//      Boid newBoid = (Boid) b;
//      if (newBoid.getPosition().dist(home.getPosition()) <= home.getRadius()) {
//        unregisterObserver(newBoid);
//        registerObserver(newBoid);
//      }
//    }
  }


  /**
   * Render all IMoveables.
   *
   * @param window is processing window
   */
//  public void run(Window window) {
//    for (IMoveable b : boids) {
//      b.run(window, boids);  // Passing the entire list of boids to each boid individually
//      boidRenderer.render(window, b);
//    }
//    homeRenderer.render(window, home);
//  }

  public void run(Window window) {
    org.bcit.comp2522.labs.lab05.Boid badBoid = null;
    Flock badFlock = null;
    for (org.bcit.comp2522.labs.lab05.IMoveable b : boids) {
      for (Flock flock : window.flocks) {
        if (flock.touchedHome(b)) {
            badFlock = flock;
            badBoid = (org.bcit.comp2522.labs.lab05.Boid) b;
        }
      }
      b.run(window, boids);  // Passing the entire list of boids to each boid individually
      boidRenderer.render(window, b);
    }
    homeRenderer.render(window, home);

    this.unregisterObserver(badBoid);
    if (badFlock != null) {
      badFlock.registerObserver(badBoid);
    }
  }

  public ArrayList<org.bcit.comp2522.labs.lab05.IMoveable> getBoids() {
    return boids;
  }

  public void addBoid() {
    org.bcit.comp2522.labs.lab05.Boid b = new org.bcit.comp2522.labs.lab05.Boid(position.x, position.y, color, this);
    boids.add(b);
  }

  @Override
  public void registerObserver(org.bcit.comp2522.labs.lab05.AbstractObserver observer) {
      org.bcit.comp2522.labs.lab05.Boid newBoid = (org.bcit.comp2522.labs.lab05.Boid) observer;
      boids.add(newBoid);
      newBoid.setColor(this.color);

    // TODO: add functionality to add a Boid from a different Flock
  }

  @Override
  public void unregisterObserver(AbstractObserver observer) {
      boids.remove((org.bcit.comp2522.labs.lab05.Boid) observer);
    // TODO: add functionality to remove Boid from this Flock
  }

  @Override
  public void notifyObservers() {
    for(IMoveable b: boids) {
      org.bcit.comp2522.labs.lab05.Boid newBoid = (Boid) b;
      newBoid.update(home);
    }
    // TODO: add functionality to update Boid behaviour
    System.out.format("Currently in %s.\n", this.toString()); // stub
  }
}