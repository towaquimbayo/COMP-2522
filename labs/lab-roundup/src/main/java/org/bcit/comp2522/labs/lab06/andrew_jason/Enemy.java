package org.bcit.comp2522.labs.lab06.andrew_jason;

import org.bcit.comp2522.labs.lab06.AbstractObserver;
import org.bcit.comp2522.labs.lab06.Player;
import org.bcit.comp2522.labs.lab06.Window;
import processing.core.PVector;

import java.awt.*;
import java.util.Observable;

public class  Enemy extends AbstractObserver implements IComparable {

  protected PVector position;

  protected PVector direction;

  protected org.bcit.comp2522.labs.lab06.Window window;

  protected float diameter = 10f;

  protected float speed = 1f;

  protected float distance;

  protected float power;

  protected float maxSpeed;

  int fColour = 255;

  Color color;

  public Enemy(PVector position, PVector direction, org.bcit.comp2522.labs.lab06.Window window) {
    this.position = position;
    this.direction = direction;
    this.window = window;
    this.color = new Color(200, 100, 0);
    this.power = 100f;
    this.maxSpeed = 0.5f;
  }

public PVector getPosition() {
    return this.position;
}

  public float getDistance() {
    return this.distance;
  }

  public PVector getDirection() {
    return this.direction;
  }

  public void setDistance(float f) {
    this.distance = f;
  }

  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  public float getMaxSpeed() {
    return this.maxSpeed;
  }

  public float getPower() {
    return this.power;
  }

  public void setPower(float f) {
    this.power = f;
  }

  public void rotate(float f) {
    this.direction.rotate(f);
  }

  public void bounce(float b) {
    this.direction.rotate(b);
  }

  public boolean outOfBounds(org.bcit.comp2522.labs.lab06.Window window) {
    float radius = (float) (this.diameter / 2);
    if ((this.position.x + radius > window.width - 1
      || this.position.x - radius < 1
      || this.position.y + radius > window.height - 1
      || this.position.y - radius < 1)) {
      return true;
    } else {
      return false;
    }
  }

  public PVector seekTheWeak(PVector targetPosition, org.bcit.comp2522.labs.lab06.Player player) {
    float distance = PVector.dist(this.getPosition(), targetPosition);
    PVector currentDirection = this.getDirection();
    PVector newDirection = PVector.sub(targetPosition, this.getPosition());
    newDirection.normalize();
    newDirection.mult(this.getMaxSpeed());

    if (distance < 30.0f && this.compareTo(player) > 0 && !this.outOfBounds(window)) {
      return this.direction = newDirection;

    } else if (distance < 30.0f && this.compareTo(player) < 0 && !this.outOfBounds(window)) {
      return this.direction = newDirection.rotate((float) Math.PI);
    } else
      return currentDirection;
  }

  public void move(org.bcit.comp2522.labs.lab06.Window window) {
    this.position = this.position.add(this.direction.mult(speed));
    if (outOfBounds(window)) {
      bounce((float) Math.PI / 4f);
    }
  }

  public void draw(Window window) {
    int currentfColour = 255;
    if (this.compareTo(window.player) > 0) {
      this.fColour = 100;
    } else {
      this.fColour = currentfColour;
    }

    window.fill(this.fColour);
    window.ellipse(this.position.x,
      this.position.y,
      this.diameter,
      this.diameter);
  }



  @Override
  public void update(Object o) {
      org.bcit.comp2522.labs.lab06.Player newPlayer = (org.bcit.comp2522.labs.lab06.Player) o;
      this.direction = this.seekTheWeak(newPlayer.getPosition(), newPlayer);
  }

  @Override
  public int compareTo(Object o) {
    org.bcit.comp2522.labs.lab06.Player newPlayer = (Player) o;
    if (this.power > newPlayer.getPower()) {
      return 1;
    } else if (this.power < newPlayer.getPower()) {
      return -1;
    } else {
      return 0;
    }
  }
}
