package org.bcit.comp2522.labs.lab05.Lester_Betty_src;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/**
 * IMoveable is anything that is movable (and drawable) onscreen.
 *
 * @author paul_bucci
 * @version 1.0
 */
public interface Imoveable {

  /**
   * Get position of the IMoveable object.
   *
   * @return Position of IMoveable as PVector
   */
  PVector getPosition();

  /**
   * Get velocity of the IMoveable object.
   *
   * @return Velocity of IMoveable as PVector
   */
  PVector getVelocity();

  /**
   * Get radius of the IMoveable object.
   *
   * @return Radius of IMoveable as float
   */
  float getRadius();

  /**
   * Get color of the IMoveable object.
   *
   * @return Color of IMoveable as Color
   */
  Color getColor();

  /**
   * Advances the IMoveable's position based on behaviour.
   *
   * @param window the Window which contains IMoveable object
   * @param moveables All IMoveables in window
   */
  void run(Window window, ArrayList<Imoveable> moveables);
}
