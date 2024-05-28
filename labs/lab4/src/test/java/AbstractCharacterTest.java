import ca.bcit.comp2522.labs.lab04.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

class AbstractCharacterTest {
  FoodCharacter foodCharacter;
  Window window;

  @BeforeEach
  void setUp() {
    window = new Window();
    PVector pos = new PVector(window.width / 2f, window.height / 2f);
    PVector dir = new PVector(0, 1).normalize();
    foodCharacter = new FoodCharacter(pos, dir, window);
  }

  @Test
  void topLeftCornerTest() {
    // character is embedded in the wall
    foodCharacter.setPosition(new PVector(0, 0));
    assertTrue(foodCharacter.touchWall(window));

    // character is exactly touching the wall on both sides
    foodCharacter.setPosition(
      new PVector(
        0 + foodCharacter.getWidth() / 2f,
        0 + foodCharacter.getHeight() / 2f
      )
    );
    assertTrue(foodCharacter.touchWall(window));

    // character is just barely not touching the wall anywhere
    foodCharacter.setPosition(
      new PVector(
        0.1f + foodCharacter.getWidth() / 2f,
        0.1f + foodCharacter.getHeight() / 2f
      )
    );
    assertFalse(foodCharacter.touchWall(window));
  }

  @Test
  void bottomRightCornerTest() {
    // character is embedded in the wall
    foodCharacter.setPosition(new PVector(window.width, window.height));
    assertTrue(foodCharacter.touchWall(window));

    // character is exactly touching the wall on both sides
    foodCharacter.setPosition(
      new PVector(
        window.width - foodCharacter.getWidth() / 2f,
        window.height - foodCharacter.getHeight() / 2f
      )
    );
    assertTrue(foodCharacter.touchWall(window));

    // character is just barely not touching the wall anywhere
    foodCharacter.setPosition(
      new PVector(
        window.width - 0.1f - foodCharacter.getWidth() / 2f,
        window.width - 0.1f - foodCharacter.getHeight() / 2f
      )
    );
    assertFalse(foodCharacter.touchWall(window));
  }

  @Test
  void northSouthCollisionTest() {
    // First FoodCharacter coming from North Direction
    PVector pos = new PVector(window.width / 2f, window.height / 2f - foodCharacter.getWidth() / 2f);
    PVector dir = new PVector(0, 1);
    foodCharacter = new FoodCharacter(pos, dir, window);

    // Second FoodCharacter coming from South Direction
    PVector pos1 = new PVector(window.width / 2f, window.height / 2f + foodCharacter.getWidth() / 2f);
    PVector dir1 = new PVector(0, -1);
    FoodCharacter foodCharacter1 = new FoodCharacter(pos1, dir1, window);

    // Frame 1
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 2
    foodCharacter.collideBehaviour(foodCharacter1);
    foodCharacter1.collideBehaviour(foodCharacter);

    // Frame 3
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 4
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    assertEquals(new PVector(50, 44), foodCharacter.getPosition());
    assertEquals(new PVector(50, 56), foodCharacter1.getPosition());
    assertEquals(new PVector(0, -1), foodCharacter.getDirection());
    assertEquals(new PVector(0, 1), foodCharacter1.getDirection());
  }

  @Test
  void eastWestCollisionTest() {
    // First FoodCharacter coming from West Direction
    PVector pos = new PVector(window.width / 2f - foodCharacter.getWidth() / 2f, window.height / 2f);
    PVector dir = new PVector(1, 0);
    foodCharacter = new FoodCharacter(pos, dir, window);

    // Second FoodCharacter coming from East Direction
    PVector pos1 = new PVector(window.width / 2f + foodCharacter.getWidth() / 2f, window.height / 2f);
    PVector dir1 = new PVector(-1, 0);
    FoodCharacter foodCharacter1 = new FoodCharacter(pos1, dir1, window);

    // Frame 1
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 2
    foodCharacter.collideBehaviour(foodCharacter1);
    foodCharacter1.collideBehaviour(foodCharacter);

    // Frame 3
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 4
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    assertEquals(new PVector(44, 50), foodCharacter.getPosition());
    assertEquals(new PVector(56, 50), foodCharacter1.getPosition());
    assertEquals(new PVector(-1, 0), foodCharacter.getDirection());
    assertEquals(new PVector(1, 0), foodCharacter1.getDirection());
  }

  @Test
  void northEastCollisionTest() {
    // First FoodCharacter coming from South Direction
    PVector pos = new PVector(window.width / 2f, window.height / 2f);
    PVector dir = new PVector(0, -1);
    foodCharacter = new FoodCharacter(pos, dir, window);

    // Second FoodCharacter coming from West Direction
    PVector pos1 = new PVector(window.width / 2f - foodCharacter.getWidth() / 2f, window.height / 2f);
    PVector dir1 = new PVector(1, 0);
    FoodCharacter foodCharacter1 = new FoodCharacter(pos1, dir1, window);

    // Frame 1
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 2
    foodCharacter.collideBehaviour(foodCharacter1);
    foodCharacter1.collideBehaviour(foodCharacter);

    // Frame 3
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 4
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    assertEquals(new PVector(50, 51), foodCharacter.getPosition());
    assertEquals(new PVector(44, 50), foodCharacter1.getPosition());
    assertEquals(new PVector(0, 1), foodCharacter.getDirection());
    assertEquals(new PVector(-1, 0), foodCharacter1.getDirection());
  }

  @Test
  void northWestCollisionTest() {
    // First FoodCharacter coming from South Direction
    PVector pos = new PVector(window.width / 2f, window.height / 2f + foodCharacter.getWidth() / 2f);
    PVector dir = new PVector(0, -1);
    foodCharacter = new FoodCharacter(pos, dir, window);

    // Second FoodCharacter coming from East Direction
    PVector pos1 = new PVector(window.width / 2f + foodCharacter.getWidth() / 2f, window.height / 2f);
    PVector dir1 = new PVector(-1, 0);
    FoodCharacter foodCharacter1 = new FoodCharacter(pos1, dir1, window);

    // Frame 1
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 2
    foodCharacter.collideBehaviour(foodCharacter1);
    foodCharacter1.collideBehaviour(foodCharacter);

    // Frame 3
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 4
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    assertEquals(new PVector(50, 56), foodCharacter.getPosition());
    assertEquals(new PVector(56, 50), foodCharacter1.getPosition());
    assertEquals(new PVector(0, 1), foodCharacter.getDirection());
    assertEquals(new PVector(1, 0), foodCharacter1.getDirection());
  }

  @Test
  void southEastCollisionTest() {
    // First FoodCharacter coming from North Direction
    PVector pos = new PVector(window.width / 2f, window.height / 2f - foodCharacter.getWidth() / 2f);
    PVector dir = new PVector(0, 1);
    foodCharacter = new FoodCharacter(pos, dir, window);

    // Second FoodCharacter coming from West Direction
    PVector pos1 = new PVector(window.width / 2f - foodCharacter.getWidth() / 2f, window.height / 2f);
    PVector dir1 = new PVector(1, 0);
    FoodCharacter foodCharacter1 = new FoodCharacter(pos1, dir1, window);

    // Frame 1
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 2
    foodCharacter.collideBehaviour(foodCharacter1);
    foodCharacter1.collideBehaviour(foodCharacter);

    // Frame 3
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 4
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    assertEquals(new PVector(50, 44), foodCharacter.getPosition());
    assertEquals(new PVector(44, 50), foodCharacter1.getPosition());
    assertEquals(new PVector(0, -1), foodCharacter.getDirection());
    assertEquals(new PVector(-1, 0), foodCharacter1.getDirection());
  }

  @Test
  void southWestCollisionTest() {
    // First FoodCharacter coming from North Direction
    PVector pos = new PVector(window.width / 2f, window.height / 2f - foodCharacter.getWidth() / 2f);
    PVector dir = new PVector(0, 1);
    foodCharacter = new FoodCharacter(pos, dir, window);

    // Second FoodCharacter coming from East Direction
    PVector pos1 = new PVector(window.width / 2f + foodCharacter.getWidth() / 2f, window.height / 2f);
    PVector dir1 = new PVector(-1, 0);
    FoodCharacter foodCharacter1 = new FoodCharacter(pos1, dir1, window);

    // Frame 1
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 2
    foodCharacter.collideBehaviour(foodCharacter1);
    foodCharacter1.collideBehaviour(foodCharacter);

    // Frame 3
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 4
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    assertEquals(new PVector(50, 44), foodCharacter.getPosition());
    assertEquals(new PVector(56, 50), foodCharacter1.getPosition());
    assertEquals(new PVector(0, -1), foodCharacter.getDirection());
    assertEquals(new PVector(1, 0), foodCharacter1.getDirection());
  }

  @Test
  void northEastNorthWest() {
    // First FoodCharacter coming from South West Direction
    PVector pos = new PVector(window.width / 2f - foodCharacter.getWidth() / 2f, window.height / 2f + foodCharacter.getWidth() / 2f);
    PVector dir = new PVector(1, -1);
    foodCharacter = new FoodCharacter(pos, dir, window);

    // Second FoodCharacter coming from South East Direction
    PVector pos1 = new PVector(window.width / 2f + foodCharacter.getWidth() / 2f, window.height / 2f + foodCharacter.getWidth() / 2f);
    PVector dir1 = new PVector(-1, -1);
    FoodCharacter foodCharacter1 = new FoodCharacter(pos1, dir1, window);

    // Frame 1
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 2
    foodCharacter.collideBehaviour(foodCharacter1);
    foodCharacter1.collideBehaviour(foodCharacter);

    // Frame 3
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    // Frame 4
    try {
      foodCharacter.move(window);
      foodCharacter1.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("Error: " + e);
    }

    assertEquals(new PVector(44, 56), foodCharacter.getPosition());
    assertEquals(new PVector(56, 56), foodCharacter1.getPosition());
    assertEquals(new PVector(-1, 1), foodCharacter.getDirection());
    assertEquals(new PVector(1, 1), foodCharacter1.getDirection());
  }
}