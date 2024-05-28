package org.bcit.comp2522.labs.lab04.navdeep_test;

import org.bcit.comp2522.labs.lab04.FoodCharacter;
import org.bcit.comp2522.labs.lab04.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

public class MakeYourOwnTestTest {

  Window window;

  FoodCharacter foodCharacter;
  FoodCharacter foodCharacter2;

  @BeforeEach
  void setUp() {
    window = new Window();
    PVector pos = new PVector(window.width / 2f, window.height / 2f);
    PVector dir = new PVector(0, 1).normalize();
    foodCharacter = new FoodCharacter(pos, dir, window);
    foodCharacter2 = new FoodCharacter(pos, dir, window);
  }

  // todo7 testing others below
  @Test
  void headingNorthThenSouth() {
    foodCharacter.setDirection(new PVector(0, -1));
    assertTrue(foodCharacter.goNorth(window));

    foodCharacter.setDirection(new PVector(0, 1));
    assertTrue(foodCharacter.goSouth(window));
  }

  @Test
  void headingSouthThenNorth() {
    foodCharacter.setDirection(new PVector(0,1));
    assertTrue(foodCharacter.goSouth(window));

    foodCharacter.setDirection(new PVector(0, -1));
    assertTrue(foodCharacter.goNorth(window));
  }

  @Test
  void headingEastThenWest() {
    foodCharacter.setDirection(new PVector(-1, 0));
    assertTrue(foodCharacter.goEast(window));

    foodCharacter.setDirection(new PVector(1, 0));
    assertTrue(foodCharacter.goWest(window));
  }

  @Test
  void headingWestThenEast() {
    foodCharacter.setDirection(new PVector(1, 0));
    assertTrue(foodCharacter.goWest(window));

    foodCharacter.setDirection(new PVector(-1, 0));
    assertTrue(foodCharacter.goEast(window));
  }

  // todo6 below

  @Test
  void OneNorthSecondEast() {
    foodCharacter.setDirection(new PVector(0, -1));
    assertTrue(foodCharacter.goNorth(window));

    foodCharacter2.setDirection(new PVector(-1, 0));
    assertTrue(foodCharacter2.goEast(window));
  }

  @Test
  void OneNorthSecondWest() {
    foodCharacter.collideBehaviour(foodCharacter2);

    foodCharacter.setDirection(new PVector(0, -1));
    assertTrue(foodCharacter.goNorth(window));

    foodCharacter2.setDirection(new PVector(1,0));
    assertTrue(foodCharacter2.goWest(window));
  }

  @Test
  void OneSouthSecondEast() {
    foodCharacter.collideBehaviour(foodCharacter2);

    foodCharacter.setDirection(new PVector(0,1));
    assertTrue(foodCharacter.goSouth(window));

    foodCharacter2.setDirection(new PVector(-1, 0));
    assertTrue(foodCharacter2.goEast(window));
  }

  @Test
  void OneSouthSecondWest() {
    foodCharacter.collideBehaviour(foodCharacter2);

    foodCharacter.setDirection(new PVector(0,1));
    assertTrue(foodCharacter.goSouth(window));

    foodCharacter2.setDirection(new PVector(1, 0));
    assertTrue(foodCharacter2.goWest(window));
  }

  @Test
  void OneNorthEastSecondNorthWest() {
    foodCharacter.collideBehaviour(foodCharacter2);

    foodCharacter.setDirection(new PVector(-1,-1));
    assertTrue(foodCharacter.goNorthEast(window));

    foodCharacter2.setDirection(new PVector(1, -1));
    assertTrue(foodCharacter2.goNorthWest(window));
  }

  @Test
  void isWindowSizeCorrect(){
    window.setSize(1653, 6774);
    assertFalse(window.checkWindowSize(window));

    window.setSize(640, 360);
    assertTrue(window.checkWindowSize(window));
  }

}
