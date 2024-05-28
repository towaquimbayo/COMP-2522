package org.bcit.comp2522.labs.lab05.Towa_Alex;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/**
 * IMoveable is anything that is movable (and drawable) onscreen.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public interface IMoveable {
  /** Get PVector position. */
  PVector getPosition();

  /** Get PVector velocity. */
  PVector getVelocity();

  /** Get PVector radius. */
  float getRadius();

  /** Get color value. */
  Color getColor();

  /**
   * Run the object on window.
   *
   * @param window for window
   * @param moveables for IMoveable objects
   */
  void run(Window window, ArrayList<IMoveable> moveables);
}
