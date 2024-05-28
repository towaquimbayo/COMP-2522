import org.bcit.comp2522.labs.lab04.FoodCharacter;
import org.bcit.comp2522.labs.lab04.Window;
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

}