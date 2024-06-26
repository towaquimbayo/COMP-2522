package org.bcit.comp2522.labs.lab05.noufil_willy;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;


/**
 * IMoveable is anything that is movable (and drawable) onscreen.
 *
 * @author paul_bucci
 * @version 1.0
 */
public interface IMoveable {
  PVector getPosition();

  PVector getVelocity();

  float getRadius();

  Color getColor();

  void run(Window window, ArrayList<IMoveable> moveables);

}
