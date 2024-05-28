package org.bcit.comp2522.labs.lab06;

import processing.core.PVector;

/**
 * The AbstractObserver class that defines the objects that observer observables.
 */
public abstract class AbstractObserver {
  public abstract void update(PVector position, float size);

  public abstract void update();
}
