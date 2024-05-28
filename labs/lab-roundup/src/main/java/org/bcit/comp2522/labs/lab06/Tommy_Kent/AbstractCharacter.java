package org.bcit.comp2522.labs.lab06.Tommy_Kent;

import processing.core.PVector;

import java.awt.*;

/**
 * Abstract class that defines character interaction.
 *
 * @author Tommy_Nguyen
 * @author Kent_Concengco
 * @version 1.0
 */
public abstract class AbstractCharacter implements Comparable<AbstractCharacter>, ICollidable {
  private final float initDiameter;
  private final float maxDiameter = 100f;
  protected PVector position;
  protected PVector direction;
  protected float diameter;
  protected Color color;
  protected Window window;
  protected float power;
  protected float speed;

  /**
   * Abstract class for collidable characters.
   *
   * @param speed the speed of the character
   * @param power the strength of the character
   * @param pin the position of the character
   * @param dir the direction of the character
   * @param din the diameter of the character
   * @param cin the color of the character
   * @param win the window that the character resides in
   */
  public AbstractCharacter(float speed, float power, PVector pin, PVector dir, float din, Color cin,
                           Window win) {
    this.speed = speed;
    this.position = pin;
    this.direction = dir;
    this.power = power;
    this.initDiameter = din;
    this.diameter = din + power;
    this.color = cin;
    this.window = win;
  }

  @Override
  public int compareTo(AbstractCharacter c) {
    return (int) (this.power - c.power);
  }

  public void move() {
    PVector multDir = this.direction.copy();
    this.position = this.position.add(multDir.mult(speed));
  }

  /**
   * Draws the character.
   */
  public void draw() {
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    borders();
    window.ellipse(this.position.x, this.position.y, diameter, diameter);
  }

  private void borders() {
    if (position.x < -diameter) {
      position.x = window.width + diameter;
    }
    if (position.y < -diameter) {
      position.y = window.height + diameter;
    }
    if (position.x > window.width + diameter) {
      position.x = -diameter;
    }
    if (position.y > window.height + diameter) {
      position.y = -diameter;
    }
  }

  @Override
  public boolean isCollided(ICollidable c) {
    if (c == this) {
      return false;
    }
    return c.getPosition().dist(this.position) <= (c.getDiameter() / 2f + this.diameter / 2f);
  }

  @Override
  public PVector getPosition() {
    return position;
  }

  /**
   * Power that the character gains.
   *
   * @param powerGainRate the rate of power gained
   */
  public void powerGain(float powerGainRate) {
    this.power += powerGainRate;
    if (this.diameter <= maxDiameter) {
      this.diameter = this.initDiameter + power;
    }
  }

  public void setPosition(PVector position) {
    this.position = position;
  }

  public PVector getDirection() {
    return direction;
  }

  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  @Override
  public float getDiameter() {
    return diameter;
  }

  public void setDiameter(float diameter) {
    this.diameter = diameter;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public float getPower() {
    return power;
  }

  public void setPower(float power) {
    this.power = power;
  }
}
