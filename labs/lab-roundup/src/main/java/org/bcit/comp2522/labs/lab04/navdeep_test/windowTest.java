package org.bcit.comp2522.labs.lab04.navdeep_test;

import org.bcit.comp2522.labs.lab04.Window;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class windowTest {

  Window window;

  @Test
  void checkWindowSize(){
    window.setSize(569, 789);
    assertFalse(window.checkWindowSize(window));

    window.setSize(640, 360);
    assertTrue(window.checkWindowSize(window));
  }
}