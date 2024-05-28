package org.bcit.comp2522.labs.lab05.Lester_Betty_src;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Flock manages a list of Boid objects.
 *
 * @author paul_bucci (based on Daniel Shiffman's Boids)
 * @author Betty Nguyen, Lester Shun
 * @version 1.0
 * @version October 9, 2022
 */
public class Flock extends AbstractObservable {
  ArrayList<Imoveable> boids; // An ArrayList for all the boids
  ArrayList<Imoveable> addBoidQueue = new ArrayList<>();
  ArrayList<Imoveable> removeBoidQueue = new ArrayList<>();
  Color color;
  PVector position;
  Home home;
  float homeRadius;
  BoidRenderer boidRenderer;
  HomeRenderer homeRenderer;

  /**
   * Constructor for a Flock.
   *
   * @param window the processing window
   * @param generator a random number generator
   */
  public Flock(Window window, Random generator) {
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

  /**
   * Render all IMoveables.
   *
   * @param window is processing window
   */
  public void run(Window window) {
    for (Imoveable b : boids) {
      b.run(window, boids);  // Passing the entire list of boids to each boid individually
      boidRenderer.render(window, b);
    }
    homeRenderer.render(window, home);
  }

  public ArrayList<Imoveable> getBoids() {
    return boids;
  }

  public void addBoid() {
    Boid b = new Boid(position.x, position.y, color, home);
    boids.add(b);
  }

  /**
   * Checks every boid with every home to compare the difference of
   * the boid's position and the home's position to the home's radius
   * for home's that are not the boid's home. This will check if the
   * boid is touching another home. If touching another home, that boid
   * will be added to removeBoidQueue with the addToRemoveQueue method.
   * The findFlock method then will add the boid to the addBoidQueue
   * with the addToQueue method.
   *
   * @param homes an array list of all of the homes
   * @param flocks an array list of all of the flocks
   */
  public void checkHome(ArrayList<Home> homes, ArrayList<Flock> flocks) {
    for (Imoveable b : boids) {
      for (Home home : homes) {
        if (b.getPosition().dist(home.getPosition()) <= home.getRadius() && home != this.home) {
          addToRemoveQueue((Boid) b);
          findFlock((Boid) b, home, flocks);
        }
      }
    }
  }

  /**
   * Iterates through the array list of flocks to find if the boid's new home
   * matches a flock's home to add the boid into the new flock.
   *
   * @param observer the boid as an AbstractObserver
   * @param home the home object
   * @param flocks an arraylist of flocks
   */
  public void findFlock(AbstractObserver observer, Home home, ArrayList<Flock> flocks) {
    for (Flock f : flocks) {
      if (f.getHome() == home) {
        f.addToAddQueue(observer);
      }
    }
  }

  /**
   * Adds the boid to the addBoidQueue arraylist.
   *
   * @param observer the boid as an AbstractObserver
   */
  public void addToAddQueue(AbstractObserver observer) {
    addBoidQueue.add((Imoveable) observer);
  }

  /**
   * Sets the boid's colour and behaviour to the new flock's colour
   * and current behaviour and then registers the boid to the new flock.
   *
   * @param observer the boid as an AbstractObserver
   */
  @Override
  public void registerObserver(AbstractObserver observer) {
    ((Boid) observer).setColour(this.color);
    if (((Boid) boids.get(0)).getBehaviour() instanceof FlockingBehaviour) {
      FlockingBehaviour flockBehav = new FlockingBehaviour((Boid) observer);
      ((Boid) observer).setBehaviour(flockBehav);
    } else {
      HomeBehaviour homeBehav = new HomeBehaviour((Boid) observer, this.home);
      ((Boid) observer).setBehaviour(homeBehav);
    }
    this.boids.add((Imoveable) observer);
    System.out.println("Added new observer");
  }

  /**
   *  Checks addBoidQueue for any boids to add to this
   *  Flock. If there are boids in the queue, register the
   *  boid by calling registerObserver.
   */
  public void addToFlock() {
    for (Imoveable b : addBoidQueue) {
      registerObserver((AbstractObserver) b);
    }
  }

  /**
   * Adds the observer (likely of type Boid) to an ArrayList
   * queue of boids to be removed. This is done in order to
   * prevent Concurrent Modification exceptions.
   *
   * @param observer AbstractObserver to be removed from Flock
   *                 (likely of type Boid)
   */
  public void addToRemoveQueue(AbstractObserver observer) {
    removeBoidQueue.add((Imoveable) observer);
  }

  /**
   * Unregisters the boid from the Flock's ArrayList of boids.
   *
   * @param observer AbstractObserver to be removed from Flock
   *                 (likely of type Boid)
   */
  @Override
  public void unregisterObserver(AbstractObserver observer) {
    // TODO: add functionality to remove Boid from this Flock
    this.boids.remove((Imoveable) observer);
  }

  /**
   *  Checks removeBoidQueue for any boids to remove from this
   *  Flock. If there are boids in the queue, unregister the
   *  boid by calling unregisterObserver.
   */
  public void removeFromFlock() {
    for (Imoveable b : removeBoidQueue) {
      unregisterObserver((AbstractObserver) b);
    }
  }

  /**
   * Clear the addBoidQueue and removeBoidQueues in each Flock
   * after the boids have been updated.
   */
  public void clearQueues() {
    addBoidQueue.clear();
    removeBoidQueue.clear();
  }

  /**
   * Notify all boids in the flock of behaviour change.
   */
  @Override
  public void notifyObservers() {
    // TODO: add functionality to update Boid behaviour
    for (Imoveable b : boids) {
      if (b instanceof Boid) {
        ((Boid) b).update(((Boid) b).getBehaviour());
      }
      System.out.println("Flock behaviour changed"); // stub
    }
  }

  /**
   * Return the flock's home.
   *
   * @return the Flock's Home as a Home
   */
  public Home getHome() {
    return home;
  }
}