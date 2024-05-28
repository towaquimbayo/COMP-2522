package org.bcit.comp2522.labs.lab05.noufil_willy;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * The Flock manages a list of Boid objects.
 *
 * @author paul_bucci (based on Daniel Shiffman's Boids), refactored by Willy Liao, Noufil Saqib
 * @version 1.0
 */
public class Flock extends AbstractObservable {
  ArrayList<IMoveable> boids; // An ArrayList for all the boids
  Color color;
  PVector position;
  Home home;
  float homeRadius;
  BoidRenderer boidRenderer;
  HomeRenderer homeRenderer;
  String behaviour;
  ArrayList<IMoveable> addBoidQueue;
  ArrayList<IMoveable> removeBoidQueue;

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
    observers = new ArrayList<>();
    behaviour = "flocking";
    addBoidQueue = new ArrayList<>();
    removeBoidQueue = new ArrayList<>();
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
      if (behaviour.equals("flocking")) {
        behaviour = "home";
      } else {
        behaviour = "flocking";
      }
      notifyObservers(behaviour);
    }
  }

  /**
   * Render all IMoveables. It also adds any new Boid
   * to the current Flock, and removes and Boid that have
   * left.
   *
   * @param window is processing window
   */
  public void run(Window window) {
    // Check if boids touch
    for (Flock f : window.flocks) {
      if (f != this) {
        for (IMoveable boid : f.getBoids()) {

          PVector boidPos = boid.getPosition();
          if (this.position.dist(boidPos) <= home.getRadius()) {

            f.unregisterObserver((AbstractObserver) boid);
            this.registerObserver((AbstractObserver) boid);
          }
        }
      }
    }

    // add new boids to flock
    boids.addAll(addBoidQueue);

    // remove boids from flock
    boids.removeAll(removeBoidQueue);

    addBoidQueue.clear();
    removeBoidQueue.clear();

    notifyObservers(this.color);
    notifyObservers(behaviour);

    for (IMoveable b : boids) {
      b.run(window, boids);  // Passing the entire list of boids to each boid individually
      boidRenderer.render(window, b);
    }

    homeRenderer.render(window, home);
  }

  /**
   * Returns list of Boids.
   *
   * @return boids an ArrayList
   */
  public ArrayList<IMoveable> getBoids() {
    return boids;
  }

  /**
   * Creates a new Boid and adds it to the Flock.
   * It also registers the Boid as an Observer.
   */
  public void addBoid() {
    Boid b = new Boid(position.x, position.y, color);
    boids.add(b);
    registerObserver(b);
  }

  /**
   * Adds the observer to the list of observers and also adds
   * it to the ArrayList of IMoveable.
   *
   * @param observer an AbstractObserver
   */
  @Override
  public void registerObserver(AbstractObserver observer) {
    observers.add(observer);
    addBoidQueue.add((IMoveable) observer);
  }

  /**
   * Removes the observer from the list of observers and also
   * removes it from the ArrayList of IMoveable.
   *
   * @param observer an AbstractObserver
   */
  @Override
  public void unregisterObserver(AbstractObserver observer) {
    observers.remove(observer);
    removeBoidQueue.add((IMoveable) observer);
  }

  /**
   * Notifies all observers with the specified message.
   *
   * @param msg an Object
   */
  @Override
  public void notifyObservers(Object msg) {
    if (msg.equals("flocking")) {
      for (AbstractObserver observer : observers) {
        observer.update(new FlockingBehaviour((Boid) observer));
      }
    }

    if (msg.equals("home")) {
      for (AbstractObserver observer : observers) {
        observer.update(new HomeBehaviour((Boid) observer, this.home));
      }
    }

    if (msg instanceof Color) {
      for (AbstractObserver observer : observers) {
        observer.update(msg);
      }
    }
  }
}