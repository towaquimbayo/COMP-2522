package org.bcit.comp2522.labs.lab06.andrew_jason;

import org.bcit.comp2522.labs.lab06.AbstractObservable;
import org.bcit.comp2522.labs.lab06.AbstractObserver;
import org.bcit.comp2522.labs.lab06.Enemy;
import org.bcit.comp2522.labs.lab06.EnemyCollection;
import org.bcit.comp2522.labs.lab06.Window;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class Player extends AbstractObservable implements IComparable {
  private static Player player;

  private org.bcit.comp2522.labs.lab06.EnemyCollection<org.bcit.comp2522.labs.lab06.Enemy> enemyCollection;

  private float diameter = 20f;
  private PVector position;
  private PVector direction;
  private org.bcit.comp2522.labs.lab06.Window window;

  private float speed = 1f;

  private float power;
  Color color;

  private Player(PVector p, PVector d, org.bcit.comp2522.labs.lab06.Window w) {
    this.enemyCollection = new EnemyCollection<>();
    position = p;
    direction = d;
    window = w;
    color = new Color(200, 0, 0);
    power = 1000f;
  }

  public void addEnemy(org.bcit.comp2522.labs.lab06.Enemy e) {
    enemyCollection.add(e);
  }

  public void removeEnemy(org.bcit.comp2522.labs.lab06.Enemy e) {
    enemyCollection.remove(e);
  }

  public static Player getInstance(PVector pos, PVector dir, org.bcit.comp2522.labs.lab06.Window win) {
    if (player == null) {
      player = new Player(pos, dir, win);
    }
    return player;
  }

  public PVector getDirection() {
    return this.direction;
  }

  public PVector getPosition() {
    return this.position;
  }

  public float getPower() {
    return this.power;
  }

  public void setPower(float p) {
    this.power = p;
  }

  public boolean eat(org.bcit.comp2522.labs.lab06.Enemy e) {
    if (this.position.x <= e.position.x + (10f)
        && this.position.x >= e.position.x - (10f)
        && this.position.y <= e.position.y + (10f)
        && this.position.y >= e.position.y - (10f)) {
      return true;
    } else {
      return false;
    }
  }

  public void rotate(float r) {
    this.direction.rotate(r);
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

  public void bounce (float b) {
    this.direction.rotate(b);
  }

  public void move() {
    this.position = this.position.add(this.direction.mult(speed));
    if (outOfBounds(window)) {
      bounce((float) Math.PI / 4f);
    }
  }

  public void draw(Window window) {
    window.fill(color.getRed(), color.getBlue(), color.getBlue());
    window.ellipse(position.x, position.y, diameter, diameter);
  }

  @Override
  public void registerObserver(org.bcit.comp2522.labs.lab06.AbstractObserver observer) {
    org.bcit.comp2522.labs.lab06.Enemy newEnemy = (org.bcit.comp2522.labs.lab06.Enemy) observer;
    enemyCollection.add(newEnemy);
  }

  @Override
  public void unregisterObserver(AbstractObserver observer) {
    org.bcit.comp2522.labs.lab06.Enemy newEnemy = (org.bcit.comp2522.labs.lab06.Enemy) observer;
    enemyCollection.remove((org.bcit.comp2522.labs.lab06.Enemy) observer);
  }

  @Override
  public void notifyObservers() {
    for(org.bcit.comp2522.labs.lab06.Enemy o: enemyCollection) {
      org.bcit.comp2522.labs.lab06.Enemy newEnemy = (org.bcit.comp2522.labs.lab06.Enemy) o;
      newEnemy.update(this);
    }
  }

  public void fight(Object o) {
    org.bcit.comp2522.labs.lab06.Enemy newEnemy = (org.bcit.comp2522.labs.lab06.Enemy) o;
    if (crossedPaths(newEnemy) && this.compareTo(newEnemy) >= 0) {
      this.setPower((this.getPower() + 10000));
    }
  }

  public boolean crossedPaths(Object o) {
    org.bcit.comp2522.labs.lab06.Enemy newEnemy = (org.bcit.comp2522.labs.lab06.Enemy) o;
    if(this.getPosition().dist(newEnemy.getPosition()) <= newEnemy.diameter) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int compareTo(Object o) {
    org.bcit.comp2522.labs.lab06.Enemy newEnemy = (Enemy) o;
    if (this.getPower() > newEnemy.power) {
      return 1;
    } else if (this.getPower() < newEnemy.power) {
      return -1;
    } else {
      return 0;
    }
  }
}
