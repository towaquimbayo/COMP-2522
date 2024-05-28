package org.bcit.comp2522.labs.lab08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyRecencyDictTest {
  MyRecencyDict mrd;

  @BeforeEach
  void setup() {
    mrd = new MyRecencyDict();
  }

  @AfterEach
  void teardown() {
    mrd.clear();
  }

  @Test
  void addGetTest() {
    mrd.push(1, "1");
    assertEquals("1", mrd.get(1));
  }

  @Test
  void addGetManyTest() {
    for (int i = 0; i < 100; i++) {
      mrd.push(i, String.format("%d", i));
    }
    for (int i = 0; i < 100; i++) {
      assertEquals(String.format("%d", i), mrd.get(i));
    }
  }
}