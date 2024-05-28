package org.bcit.comp2522.labs.lab05.Lester_Betty_src;

/**
 * Renderers take IMovables and draw them with whatever geometry logic
 * they have implemented.
 *
 * @author paul_bucci
 * @version 1.0
 */
public abstract class AbstractRenderer {
  public abstract void render(Window window, Imoveable moveable);
}
