package org.bcit.comp2522.labs.lab04.Aryan_Test;

import org.bcit.comp2522.labs.lab04.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;


class AbstractCharacterTest {
  FoodCharacter foodCharacter;
  FoodCharacter FC;
  Window window;
  PVector pos;

  @BeforeEach
  void setUp() {
    window = new Window();
    pos = new PVector(window.width / 2f, window.height / 2f);
    PVector dir = new PVector(0, 1).normalize();
    foodCharacter = new FoodCharacter(pos, dir, window);
    FC = new FoodCharacter(pos, dir, window);
  }

  ;

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

  // Collide behavior
  @Test
  void collideNorhtSouth() {
    PVector dirN = new PVector(0, -1);
    PVector dirS = new PVector(0, 1);
    foodCharacter.setDirection(dirN);
    FC.setDirection(dirS);
// Setting Position
    PVector posS = new PVector(window.width / 2f, pos.y - foodCharacter.getHeight());
    FC.setPosition(posS);


// Move behavior
    try {
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }


// collide Behaviour
    foodCharacter.collideBehaviour(FC);
    FC.collideBehaviour(foodCharacter);
//  Calling it twice
    try {
      foodCharacter.move(window);
      FC.move(window);
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }


    // May need to rewrite the expected condition
    assertEquals(new PVector(0, 1), foodCharacter.getDirection());
    assertEquals(new PVector(0, -1), FC.getDirection());
  }

  @Test
  void collideEastWest() {
    PVector dirW = new PVector(-1, 0);
    PVector dirE = new PVector(1, 0);
    foodCharacter.setDirection(dirW);
    FC.setDirection(dirE);

    PVector posW = new PVector(pos.x - foodCharacter.getWidth() / 2, window.height / 2f);
    PVector posE = new PVector(pos.x + foodCharacter.getWidth() / 2, window.height / 2f);
    foodCharacter.setPosition(posE);
    FC.setPosition(posW);


    // Moving behavior
    try {
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }
    // collide Behaviour
    foodCharacter.collideBehaviour(FC);
    FC.collideBehaviour(foodCharacter);

//  Calling it twice
    try {
      foodCharacter.move(window);
      FC.move(window);
      foodCharacter.move(window);
      FC.move(window);

    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }

    // May need to rewrite the expected condition
    assertEquals(new PVector(1, 0), foodCharacter.getDirection());
    assertEquals(new PVector(-1, 0), FC.getDirection());
  }


  // TO-DO 6
  @Test
  void collideNorthEast() {
    PVector dirN = new PVector(0, -1);
    PVector dirE = new PVector(1, 0);
    foodCharacter.setDirection(dirN);
    FC.setDirection(dirE);

    PVector posN = new PVector(50, 45);
    PVector posE = new PVector(45, 50);

    foodCharacter.setPosition(posN);
    FC.setPosition(posE);

// Moving behavior
    try {
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }

//  Collide behavior
    foodCharacter.collideBehaviour(FC);
    FC.collideBehaviour(foodCharacter);

//  Calling it twice
    try {
      foodCharacter.move(window);
      FC.move(window);
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }

    assertEquals(new PVector(0, 1), foodCharacter.getDirection());
    assertEquals(new PVector(-1, 0), FC.getDirection());
  }

  @Test
  void collideNorthWest() {
    PVector dirN = new PVector(0, -1);
    PVector dirW = new PVector(-1, 0);
    foodCharacter.setDirection(dirN);
    FC.setDirection(dirW);

    PVector posW = new PVector(55, 50);
    PVector posN = new PVector(50, 45);
    foodCharacter.setPosition(posW);
    FC.setPosition(posN);

    // Moving behavior
    try {
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }

//  Collide behavior
    foodCharacter.collideBehaviour(FC);
    FC.collideBehaviour(foodCharacter);

//  Calling it twice
    try {
      foodCharacter.move(window);
      FC.move(window);
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }

    assertEquals(new PVector(0, 1), foodCharacter.getDirection());
    assertEquals(new PVector(1, 0), FC.getDirection());
  }

  @Test
  void collideSouthEast() {
    PVector dirS = new PVector(0, 1);
    PVector dirE = new PVector(1, 0);
    foodCharacter.setDirection(dirS);
    FC.setDirection(dirE);

    PVector posS = new PVector(50, 50);
    PVector posE = new PVector(45, 55);

    foodCharacter.setPosition(posS);
    FC.setPosition(posE);


    // Moving behavior
    try {
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }
//  Collide behavior
    foodCharacter.collideBehaviour(FC);
    FC.collideBehaviour(foodCharacter);

//  Calling it twice
    try {
      foodCharacter.move(window);
      FC.move(window);
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }

    assertEquals(new PVector(0, -1), foodCharacter.getDirection());
    assertEquals(new PVector(-1, 0), FC.getDirection());
  }

  @Test
  void collideSouthWest() {
    PVector dirS = new PVector(0, 1);
    PVector dirW = new PVector(-1, 0);
    foodCharacter.setDirection(dirS);
    FC.setDirection(dirW);

    PVector posS = new PVector(50, 50);
    PVector posE = new PVector(55, 55);

    foodCharacter.setPosition(posS);
    FC.setPosition(posE);

    // Moving behavior
    try {
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }
//  Collide behavior
    foodCharacter.collideBehaviour(FC);
    FC.collideBehaviour(foodCharacter);

//  Calling it twice
    try {
      foodCharacter.move(window);
      FC.move(window);
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }

    assertEquals(new PVector(0, -1), foodCharacter.getDirection());
    assertEquals(new PVector(1, 0), FC.getDirection());
  }


  @Test
  void collideNorthEastNorhtWest() {
    PVector dirNW = new PVector(-1, -1);
    PVector dirNE = new PVector(1, -1);


    foodCharacter.setDirection(dirNW);
    FC.setDirection(dirNE);

    PVector posNW = new PVector(55, 50);
    foodCharacter.setPosition(posNW);
    PVector posNE = new PVector(45, 50);
    FC.setPosition(posNE);

    // Moving behavior
    try {
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }
//  Collide behavior
    foodCharacter.collideBehaviour(FC);
    FC.collideBehaviour(foodCharacter);

//  Calling it twice
    try {
      foodCharacter.move(window);
      FC.move(window);
      foodCharacter.move(window);
      FC.move(window);
    } catch (CharacterOutOfBoundsException e) {
      throw new RuntimeException(e);
    }

    assertEquals(new PVector(1, 1), foodCharacter.getDirection());
    assertEquals(new PVector(-1, 1), FC.getDirection());

  }

}
