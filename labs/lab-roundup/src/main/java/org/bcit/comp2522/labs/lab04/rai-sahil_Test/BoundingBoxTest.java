import org.bcit.comp2522.labs.lab04.BoundingBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;
import static org.junit.jupiter.api.Assertions.*;

class BoundingBoxTest {
  BoundingBox a, b;
  float width, height;

  @BeforeEach
  void setup() {
    width = 10f;
    height = 10f;
  }

  @Test
  void getTopTest() {
    PVector posA = new PVector(100, 100);
    b = new BoundingBox(posA, width, height);
    // due to Processing's coordinates, top should be less than pos.y
    assertEquals(posA.y - (height / 2f), b.getTop());
  }

  @Test
  void getBottomTest() {
    PVector posA = new PVector(100, 100);
    b = new BoundingBox(posA, width, height);
    // due to Processing's coordinates, top should be more than pos.x
    assertEquals(posA.y + (height / 2f), b.getBottom());
  }

  @Test
  void getLeftTest() {
    PVector posA = new PVector(100, 100);
    b = new BoundingBox(posA, width, height);
    assertEquals(posA.x - (width / 2f), b.getLeft());
  }

  @Test
  void getRightTest() {
    PVector posA = new PVector(100, 100);
    b = new BoundingBox(posA, width, height);
    assertEquals(posA.x + (width / 2f), b.getRight());
  }

  @Test
  void noCollisionTop() {
    PVector posA = new PVector(100, 100);
    a = new BoundingBox(posA, width, height);

    for (int i = 0; i < width; i++) {
      PVector posB = new PVector(89 + ((float) i),89);
      b = new BoundingBox(posB, width, height);
      assertFalse(a.collides(b));
      assertFalse(b.collides(a));
    }
  }

  @Test
  void noCollisionBottom() {
    PVector posA = new PVector(100, 100);
    a = new BoundingBox(posA, width, height);

    for (int i = 0; i < width; i++) {
      PVector posB = new PVector(89 + ((float) i),111);
      b = new BoundingBox(posB, width, height);
      assertFalse(a.collides(b));
      assertFalse(b.collides(a));
    }
  }

  @Test
  void noCollisionLeft() {
    PVector posA = new PVector(100, 100);
    a = new BoundingBox(posA, width, height);

    for (int i = 0; i < width; i++) {
      PVector posB = new PVector(89,89 + ((float) i));
      b = new BoundingBox(posB, width, height);
      assertFalse(a.collides(b));
      assertFalse(b.collides(a));
    }
  }

  @Test
  void noCollisionRight() {
    PVector posA = new PVector(100, 100);
    a = new BoundingBox(posA, width, height);

    for (int i = 0; i < width; i++) {
      PVector posB = new PVector(111,89 + ((float) i));
      b = new BoundingBox(posB, width, height);
      assertFalse(a.collides(b));
      assertFalse(b.collides(a));
    }
  }

  @Test
  void collideTest() {
    PVector posA = new PVector(100, 100);
    a = new BoundingBox(posA, width, height);

    for (int i = 0; i < width; i++) {
      PVector posB = new PVector(100 + ((float) i),100 + ((float) i));
      b = new BoundingBox(posB, width, height);
      assertTrue(a.collides(b));
      assertTrue(b.collides(a));
    }
  }



}