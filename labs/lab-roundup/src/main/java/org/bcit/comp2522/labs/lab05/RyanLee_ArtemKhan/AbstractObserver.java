package org.bcit.comp2522.labs.lab05.RyanLee_ArtemKhan;

import processing.core.PVector;

import java.awt.*;

/**
 * Observers are doing the looking, i.e., they are the subscribers.
 *
 * @author paul_bucci
 * @version 1.0
 */
public abstract class AbstractObserver  {

  private PVector position;
  private PVector velocity;
  private PVector acceleration;
  private Color color;
  private float radius;
  private float maxforce;    // Maximum steering force
  private float maxspeed;    // Maximum speed

  public AbstractObserver(float x, float y, Color color) {
    this.acceleration = new PVector(0, 0);
    float angle = (float) (Math.random() * Math.PI * 2);
    this.velocity = new PVector((float) Math.cos(angle), (float) Math.sin(angle));
    this.position = new PVector(x, y);
    this.color = color;
    this.radius = 2.0f;
    this.maxspeed = 2;
    this.maxforce = 0.03f;
  }

  public float getRadius() {
    return radius;
  }

  public float getMaxforce() {
    return maxforce;
  }

  public float getMaxspeed() {
    return maxspeed;
  }

  public PVector getPosition() {
    return position;
  }

  public PVector getVelocity() {
    return velocity;
  }

  public PVector getAcceleration() {
    return acceleration;
  }

  public abstract void update(Object msg, boolean toggled);
}
