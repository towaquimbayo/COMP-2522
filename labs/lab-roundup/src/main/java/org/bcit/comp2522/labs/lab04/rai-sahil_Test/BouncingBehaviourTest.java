import org.bcit.comp2522.labs.lab04.FoodCharacter;
import org.bcit.comp2522.labs.lab04.Window;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import java.util.ArrayList;

public class BouncingBehaviourTest {

    FoodCharacter firstFoodCharacter;
    FoodCharacter secondFoodCharacter;
    Window window;
    PVector position;
    PVector direction;


    @BeforeEach
    void setUp() {
        window = new Window();
        position = new PVector(window.width / 2f, window.height / 2f);
        direction = new PVector(0, 1).normalize();
        firstFoodCharacter = new FoodCharacter(position, direction, window);
        secondFoodCharacter = new FoodCharacter(position, direction, window);
    }

    @Test
    void NorthSouthDirectionCheck() {
        firstFoodCharacter.setDirection(
                new PVector(
                        0, 1
                )
        );

        firstFoodCharacter.setPosition(
                new PVector(
                        170, 180
                )
        );

        secondFoodCharacter.setDirection(
                new PVector(
                        0, -1
                )
        );

        secondFoodCharacter.setPosition(
                new PVector(
                        170, 180
                )
        );
        assertTrue(true);

        assertTrue(firstFoodCharacter.towardsSouth(window));
        assertTrue(secondFoodCharacter.towardsNorth(window));
        firstFoodCharacter.collideBehaviour(secondFoodCharacter);
        PVector direction1 = new PVector(0, -1);
        PVector direction2 = new PVector(-0.70710677f,  0.70710677f);
        assertEquals(firstFoodCharacter.getDirection(), direction2);
        assertEquals(secondFoodCharacter.getDirection(), direction1);

    }
}

//    @Test
//    void EastWestDirectionCheck() {
//        firstFoodCharacter.collideBehaviour(secondFoodCharacter);
//
//        firstFoodCharacter.setDirection(
//                new PVector(
//                        1,
//                        0
//                )
//        );
//        assertTrue(firstFoodCharacter.towardsWest(window));
//
//        secondFoodCharacter.setDirection(
//                new PVector(
//                        -1,
//                        0
//                )
//        );
//        assertTrue(secondFoodCharacter.towardsEast(window));
//    }
//
//    @Test
//    void NorthEastDirectionCheck() {
//        firstFoodCharacter.collideBehaviour(secondFoodCharacter);
//
//        firstFoodCharacter.setDirection(
//                new PVector(
//                        0,
//                        -1
//                )
//        );
//        assertTrue(firstFoodCharacter.towardsNorth(window));
//
//        secondFoodCharacter.setDirection(
//                new PVector(
//                        -1,
//                        0
//                )
//        );
//        assertTrue(secondFoodCharacter.towardsEast(window));
//    }
//
//    @Test
//    void NorthWestDirectionCheck() {
//        firstFoodCharacter.collideBehaviour(secondFoodCharacter);
//
//        firstFoodCharacter.setDirection(
//                new PVector(
//                        0,
//                        -1
//                )
//        );
//        assertTrue(firstFoodCharacter.towardsNorth(window));
//
//        secondFoodCharacter.setDirection(
//                new PVector(
//                        1,
//                        0
//                )
//        );
//        assertTrue(secondFoodCharacter.towardsWest(window));
//    }
//
//    @Test
//    void SouthWestDirectionCheck() {
//        firstFoodCharacter.setDirection(
//                new PVector(
//                        0,
//                        1
//                )
//        );
//        assertTrue(firstFoodCharacter.towardsSouth(window));
//
//        secondFoodCharacter.setDirection(
//                new PVector(
//                        1,
//                        0
//                )
//        );
//        assertTrue(secondFoodCharacter.towardsWest(window));
//    }
//
//    @Test
//    void SouthEastDirectionCheck() {
//        firstFoodCharacter.setDirection(
//                new PVector(
//                        0,
//                        1
//                )
//        );
//        assertTrue(firstFoodCharacter.towardsSouth(window));
//
//        secondFoodCharacter.setDirection(
//                new PVector(
//                        -1,
//                        0
//                )
//        );
//        assertTrue(secondFoodCharacter.towardsEast(window));
//    }
//
//
//}
