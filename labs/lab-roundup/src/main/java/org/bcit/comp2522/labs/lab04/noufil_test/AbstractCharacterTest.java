package org.bcit.comp2522.labs.lab04.noufil_test;

import org.bcit.comp2522.labs.lab04.FoodCharacter;
import org.bcit.comp2522.labs.lab04.OutOfBoundsException;
import org.bcit.comp2522.labs.lab04.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;


class AbstractCharacterTest {
  FoodCharacter foodCharacter;
  FoodCharacter foodCharacter2;
  Window window;

  @BeforeEach
  void setUp() {
    window = new Window();
    PVector pos = new PVector(window.width / 2f, window.height / 2f);
    PVector dir = new PVector(0, 1).normalize();
    foodCharacter = new FoodCharacter(pos, dir, window);
    foodCharacter2 = new FoodCharacter(pos, dir, window);
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
  void northSouthFoodTest() {
    // one character is moving north, the other is moving south
    // FC1 going south
    foodCharacter.setPosition(new PVector(10, 10));
    foodCharacter.setDirection(new PVector(0, 1));
    // FC2 going north
    foodCharacter2.setPosition(new PVector(10, 10 + foodCharacter.getHeight()));
    foodCharacter2.setDirection(new PVector(0, -1));

    System.out.println("F1Dir" + foodCharacter.getDirection());
    System.out.println("F1Pos" + foodCharacter.getPosition());
    System.out.println("F2Dir" + foodCharacter.getDirection());
    System.out.println("F12Pos" + foodCharacter.getPosition());

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    foodCharacter.collideBehaviour(foodCharacter2);
    foodCharacter2.collideBehaviour(foodCharacter);

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    assertEquals(new PVector(10, 9), foodCharacter.getPosition());
    assertEquals(new PVector(10, 21), foodCharacter2.getPosition());
    assertEquals(new PVector(0, -1), foodCharacter.getDirection());
    assertEquals(new PVector(0, 1), foodCharacter2.getDirection());
  }

  @Test
  void eastWestFoodTest() {
    // one character is moving east, the other is moving west
    foodCharacter.setPosition(new PVector(10, 10));
    foodCharacter.setDirection(new PVector(1, 0));
    foodCharacter2.setPosition(new PVector(10 + foodCharacter.getWidth(), 10));
    foodCharacter2.setDirection(new PVector(-1, 0));

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    foodCharacter.collideBehaviour(foodCharacter2);
    foodCharacter2.collideBehaviour(foodCharacter);

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    assertEquals(new PVector(9, 10), foodCharacter.getPosition());
    assertEquals(new PVector(21, 10), foodCharacter2.getPosition());
    assertEquals(new PVector(-1, 0), foodCharacter.getDirection());
    assertEquals(new PVector(1, 0), foodCharacter2.getDirection());
  }

  @Test
  void northEastFoodTest() {
    // one character is moving north, the other is moving east
    // FC1 moving north
    foodCharacter.setPosition(new PVector(10 + foodCharacter.getWidth(), 10 + foodCharacter.getHeight()));
    foodCharacter.setDirection(new PVector(0, -1));
    // FC2 moving east
    foodCharacter2.setPosition(new PVector(10, 10));
    foodCharacter2.setDirection(new PVector(1, 0));

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    foodCharacter.collideBehaviour(foodCharacter2);
    foodCharacter2.collideBehaviour(foodCharacter);

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    assertEquals(new PVector(20, 21), foodCharacter.getPosition());
    assertEquals(new PVector(9, 10), foodCharacter2.getPosition());
    assertEquals(new PVector(0, 1), foodCharacter.getDirection());
    assertEquals(new PVector(-1, 0), foodCharacter2.getDirection());
  }

  @Test
  void northWestFoodTest() {
    // one character is moving north, the other is moving west
    // FC1 moving north
    foodCharacter.setPosition(new PVector(10, 10 + foodCharacter.getHeight()));
    foodCharacter.setDirection(new PVector(0, -1));
    // FC2 moving west
    foodCharacter2.setPosition(new PVector(10 + foodCharacter.getWidth(), 10));
    foodCharacter2.setDirection(new PVector(-1, 0));

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    foodCharacter.collideBehaviour(foodCharacter2);
    foodCharacter2.collideBehaviour(foodCharacter);

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    assertEquals(new PVector(10, 21), foodCharacter.getPosition());
    assertEquals(new PVector(21, 10), foodCharacter2.getPosition());
    assertEquals(new PVector(0, 1), foodCharacter.getDirection());
    assertEquals(new PVector(1, 0), foodCharacter2.getDirection());
  }

  @Test
  void southEastFoodTest() {
    // one character is moving south, the other is moving east
    // FC1 moving south
    foodCharacter.setPosition(new PVector(10 + foodCharacter.getWidth(), 10));
    foodCharacter.setDirection(new PVector(0, 1));
    // FC2 moving east
    foodCharacter2.setPosition(new PVector(10, 10 + foodCharacter.getHeight()));
    foodCharacter2.setDirection(new PVector(1, 0));

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    foodCharacter.collideBehaviour(foodCharacter2);
    foodCharacter2.collideBehaviour(foodCharacter);

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    assertEquals(new PVector(20, 9), foodCharacter.getPosition());
    assertEquals(new PVector(9, 20), foodCharacter2.getPosition());
    assertEquals(new PVector(0, -1), foodCharacter.getDirection());
    assertEquals(new PVector(-1, 0), foodCharacter2.getDirection());
  }

  @Test
  void southWestFoodTest() {
    // one character is moving south, the other is moving west
    // FC1 moving south
    foodCharacter.setPosition(new PVector(10, 10));
    foodCharacter.setDirection(new PVector(0, 1));
    // FC2 moving west
    foodCharacter2.setPosition(new PVector(10 + foodCharacter.getWidth(), 10 + foodCharacter.getHeight()));
    foodCharacter2.setDirection(new PVector(-1, 0));

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    foodCharacter.collideBehaviour(foodCharacter2);
    foodCharacter2.collideBehaviour(foodCharacter);

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    assertEquals(new PVector(10, 9), foodCharacter.getPosition());
    assertEquals(new PVector(21, 20), foodCharacter2.getPosition());
    assertEquals(new PVector(0, -1), foodCharacter.getDirection());
    assertEquals(new PVector(1, 0), foodCharacter2.getDirection());
  }

  @Test
  void northeastNorthwestFoodTest() {
    // one character is moving north-east, the other is moving north-west
    // FC1 moving north-east
    foodCharacter.setPosition(new PVector(10, 10 + (foodCharacter.getHeight() * 2)));
    foodCharacter.setDirection(new PVector(1, -1));
    // FC2 moving north-west
    foodCharacter2.setPosition(new PVector(10 + (foodCharacter.getWidth() * 2),
            10 + (foodCharacter.getHeight() * 2)));
    foodCharacter2.setDirection(new PVector(-1, -1));

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    foodCharacter.collideBehaviour(foodCharacter2);
    foodCharacter2.collideBehaviour(foodCharacter);

    try {
      foodCharacter.move(window);
      foodCharacter2.move(window);
      foodCharacter.move(window);
      foodCharacter2.move(window);
    } catch (OutOfBoundsException e) {
      System.out.println("The food character went out of bounds.");
    }

    assertEquals(new PVector(9, 31), foodCharacter.getPosition());
    assertEquals(new PVector(31, 31), foodCharacter2.getPosition());
    assertEquals(new PVector(-1, 1), foodCharacter.getDirection());
    assertEquals(new PVector(1, 1), foodCharacter2.getDirection());
  }
}