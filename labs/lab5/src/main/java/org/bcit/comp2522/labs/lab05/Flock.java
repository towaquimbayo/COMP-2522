package org.bcit.comp2522.labs.lab05;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PVector;

/**
 * The Flock manages a list of Boid objects.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class Flock extends AbstractObservable {
  /** ArrayList for all the IMoveable boids. */
  protected ArrayList<IMoveable> boids;
  /** Color instance variable to store color. */
  private final Color color;
  /** PVector instance variable to store position. */
  private final PVector position;
  /** Home instance variable to home object for flock. */
  protected Home home;
  /** Floating value for home radius of the flock. */
  private static final float HOME_RADIUS = 10f;
  /** BoidRender object. */
  private final BoidRenderer boidRenderer;
  /** HomeRenderer object. */
  private final HomeRenderer homeRenderer;
  /** ArrayList for all the Flock instances. */
  protected ArrayList<Flock> otherFlock;
  /** ArrayList for all the Home instances. */
  protected ArrayList<Home> homeList;
  /** Constant for white. */
  public static final int WHITE = 255;
  /** Constant for floating point value of 2. */
  public static final float TWO_F = 2f;
  /** Constant for value of 64. */
  public static final int SIXTY_FOUR = 64;

  /**
   * Constructor for a Flock.
   *
   * @param window the processing window
   * @param generator a random number generator
   */
  public Flock(Window window, Random generator) {
    this.boids = new ArrayList<>();
    this.observers = new ArrayList<>();
    this.position = new PVector(
      generator.nextFloat() * window.width,
      generator.nextFloat() * window.height
    );
    this.color = new Color(
      SIXTY_FOUR + ((int) (generator.nextFloat() * WHITE / TWO_F)),
      SIXTY_FOUR + ((int) (generator.nextFloat() * WHITE / TWO_F)),
      SIXTY_FOUR + ((int) (generator.nextFloat() * WHITE / TWO_F))
    );
    PVector velocity = new PVector(
        generator.nextFloat(),
        generator.nextFloat()
    );
    home = new Home(this.position, velocity, HOME_RADIUS, color);
    boidRenderer = new BoidRenderer();
    homeRenderer = new HomeRenderer();
  }

  /**
   * Check whether the Home is under the mouse when clicked.
   * Notify the observers.
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
   * Render all IMoveables. Runs every frame.
   *
   * @param window is processing window
   */
  public void run(Window window) {
    for (IMoveable b : boids) {
      b.run(window, boids);  // Passing the entire list of boids to each boid individually
      boidRenderer.render(window, b);
    }
    homeRenderer.render(window, home);
  }

  /**
   * Add all the flocks in a ArrayList.
   *
   * @param list for Flock List
   */
  public void addOtherFlocksToList(ArrayList<Flock> list) {
    this.otherFlock = list;
  }

  /**
   * Get a Flock by entering the home object.
   *
   * @param h for Home
   * @return the flock that matches the home
   */
  public Flock getFlockByHome(Home h) {
    for (Flock f : otherFlock) {
      if (f.home == h) {
        return f;
      }
    }
    return this;
  }

  /**
   * Set all the homes in an ArrayList.
   *
   * @param h for Home
   */
  public void setHomesList(ArrayList<Home> h) {
    this.homeList = h;
  }

  /**
   * Get all boid objects from list.
   *
   * @return boids for all boids
   */
  public ArrayList<IMoveable> getBoids() {
    return boids;
  }

  /**
   * Add a boid to the list and register to observer.
   */
  public void addBoid() {
    Boid b = new Boid(position.x, position.y, color, this);
    boids.add(b);
    this.registerObserver(b);
  }

  /**
   * Register the observer.
   *
   * @param observer for a Boid
   */
  @Override
  public void registerObserver(Boid observer) {
    this.observers.add(observer);
  }

  /**
   * Unregister the observer.
   *
   * @param observer for a Boid
   */
  @Override
  public void unregisterObserver(Boid observer) {
    this.observers.remove(observer);
  }

  /**
   * Notify the observers in the list.
   */
  @Override
  public void notifyObservers() {
    observers.forEach(observer -> observer.update("Currently in " + observer));
  }

  /**
   * Equals method for flock.
   *
   * @param f for flock
   * @return true if both flock homes are equal
   */
  public boolean equals(Flock f) {
    return (f.home == this.home);
  }

  /**
   * Hashcode method for flock.
   *
   * @return the product of position x,y and home position x,y values
   */
  public int hashCode() {
    return (int) (this.home.position.x * this.home.position.y
            * this.position.x * this.position.y);
  }

  /**
   * toString method to print this Flock.
   *
   * @return this flock's x position and y position
   */
  public String toString() {
    return String.format("X Position: " + (int) this.position.x
            + " Y Position: " + (int) this.position.y);
  }
}