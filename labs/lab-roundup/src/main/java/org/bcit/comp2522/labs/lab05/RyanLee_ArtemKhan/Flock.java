package org.bcit.comp2522.labs.lab05.RyanLee_ArtemKhan;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.bcit.comp2522.labs.lab05.*;
import processing.core.PVector;


/**
 * The Flock manages a list of Boid objects.
 *
 * @author Artem Khan
 * @author Ryan Lee
 * @Date: October 2022
 * @version 1.0
 */
public class Flock extends AbstractObservable {
  ArrayList<IMoveable> boids; // An ArrayList for all the boids

  ArrayList<IMoveable> toRemove;

  ArrayList<IMoveable> toAdd;
  Color color;
  PVector position;
  Home home;
  float homeRadius;
  BoidRenderer boidRenderer;
  HomeRenderer homeRenderer;

  private boolean toggled = false;

  private boolean mouseClicked;


  /**
   * Constructor for a Flock.
   *
   * @param window the processing window
   * @param generator a random number generator
   */
  public Flock(Window window, Random generator) {
    this.boids = new ArrayList<>(); // Initialize the ArrayList
    this.toRemove = new ArrayList<>();
    this.toAdd = new ArrayList<>();
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
      mouseClicked = false;
      notifyObservers();
    }
  }


  /**
   * Render all IMoveables.
   *
   * @param window is processing window
   */
  public void run(Window window) {
    for (Flock flock : window.flocks) {
      for (IMoveable boid : flock.getBoids()) {
        if (!flock.equals(this)) {
          if (((boid.getPosition().x >= this.getHome().getBoundingBox().getLeft() - 5)
                  && (boid.getPosition().x <= this.getHome().getBoundingBox().getRight() + 5)
                  && (boid.getPosition().y <= this.getHome().getBoundingBox().getBottom() + 5)
                  && (boid.getPosition().y >= this.getHome().getBoundingBox().getTop() - 5))) {
            toAdd.add(boid);
          }
        }
      }
    }
    for (IMoveable boid : this.getBoids()) {
      for (Flock flock : window.flocks) {
        if (!flock.equals(this)) {
          if (((boid.getPosition().x >= flock.getHome().getBoundingBox().getLeft() - 5)
                  && (boid.getPosition().x <= flock.getHome().getBoundingBox().getRight() + 5)
                  && (boid.getPosition().y <= flock.getHome().getBoundingBox().getBottom() + 5)
                  && (boid.getPosition().y >= flock.getHome().getBoundingBox().getTop() - 5))) {
            toRemove.add(boid);
          }
        }
      }
    }
    for (IMoveable boid : toAdd) {
      registerObserver((AbstractObserver) boid);
    }
    notifyObservers();
    toAdd.clear();
    for (IMoveable boid : toRemove) {
      unregisterObserver((AbstractObserver) boid);
    }
    toRemove.clear();
    for (IMoveable b : boids) {
      b.run(window, boids);  // Passing the entire list of boids to each boid individually
      boidRenderer.render(window, b);
    }
    homeRenderer.render(window, home);
  }

  /**
   * Function to get the flock.
   *
   * @return the boids in the array.
   */
  public ArrayList<IMoveable> getBoids() {
    return boids;
  }

  /**
   * Function to add a boid to the current flock.
   */
  public void addBoid() {
    Boid b = new Boid(position.x, position.y, color);
    boids.add(b);
  }

  /**
   * Getter for the flock's home.
   *
   * @return the flock's home.
   */
  public Home getHome() {
    return home;
  }

  /**
   * Function that registers a new boid to the current flock.
   *
   * @param observer is the new boid.
   */
  @Override
  public void registerObserver(AbstractObserver observer) {
    // TODO: add functionality to add a Boid from a different Flock
    boids.add((IMoveable) observer);
    ((Boid) observer).setColor(home.getColor());
  }

  /**
   * Function that removes a boid from this flock.
   *
   * @param observer is the boid being removed.
   */
  @Override
  public void unregisterObserver(AbstractObserver observer) {
    boids.remove((IMoveable) observer);
  }

  /**
   * Function to toggle the home behaviour or flock behaviour
   * and notify boids which behaviour to use.
   */
  @Override
  public void notifyObservers() {
    // TODO: add functionality to update Boid behaviour
    if (mouseClicked == false) {
      if (toggled == false) {
        for (IMoveable singleBoid : boids) {
          ((Boid) singleBoid).update(home, true);
          toggled = true;
        }
      } else {
        for (IMoveable singleBoid : boids) {
          ((Boid) singleBoid).update(home, false);
          toggled = false;
        }
      }
      mouseClicked = true;
    } else {
      if (toggled == false) {
        for (IMoveable singleBoid : boids) {
          ((Boid) singleBoid).update(home, true);
        }
      } else {
        for (IMoveable singleBoid : boids) {
          ((Boid) singleBoid).update(home, false);
        }
      }
    }
    System.out.format("Currently in %s.\n", this.toString()); // stub
  }
}