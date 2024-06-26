package org.bcit.comp2522.labs.lab05.Andrew_Jason;

import java.awt.Color;
import java.util.ArrayList;

import org.bcit.comp2522.labs.lab05.Window;
import processing.core.PVector;


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
