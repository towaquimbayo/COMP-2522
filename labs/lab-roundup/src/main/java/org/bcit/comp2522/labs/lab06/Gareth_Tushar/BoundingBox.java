package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

import processing.core.PVector;

import java.awt.*;

public class BoundingBox implements IDrawable {

  private final PVector topRight;

  private final PVector topLeft;

  private final PVector bottomRight;

  private final PVector bottomLeft;

  private final float top;

  private final float right;

  private final float bottom;

  private final float left;

  private final Color color;

  public BoundingBox(PVector pos, float width, float height) {
   this.top = pos.y - (height /2f);
   this.bottom = pos.y + (height/2f);
   this.right = pos.x + (width / 2f);
   this.left = pos.x - (width /2f);

   this.topRight = new PVector(pos.x + width / 2f, pos.y + height / 2f);
   this.topLeft = new PVector(pos.x - width / 2f, pos.y + height / 2f);
   this.bottomRight = new PVector(pos.x + width / 2f, pos.y - height / 2f);
   this.bottomLeft = new PVector(pos.x - width / 2f, pos.y - height / 2f);

   this.color = new Color(255, 0, 0);
  }

 @Override
 public void draw(Window window) {
  window.stroke(color.getRed(), color.getGreen(), color.getBlue());
  window.line(this.topLeft.x, this.topLeft.y, this.topRight.x, this.topRight.y);
  window.line(this.topRight.x, this.topRight.y, this.bottomRight.x, this.bottomRight.y);
  window.line(this.bottomRight.x, this.bottomRight.y, this.bottomRight.x, this.bottomRight.y);
  window.line(this.topLeft.x, this.topLeft.y, this.bottomLeft.x, this.bottomLeft.y);
 }

 public float getTop(){return this.top;}
 public float getBottom() {return this.bottom;}

 public float getLeft() {return this.left;}

 public float getRight() {return this.right;}

 public boolean collides(BoundingBox b) {
   boolean collided = !(
    this.getBottom() < b.getTop()
     || this.getTop() > b.getBottom()
     || this.getRight() < b.getLeft()
     || this.getLeft() > b.getRight()
    );
   return collided;
 }
}
