package org.bcit.comp2522.labs.lab08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyDataStructureTest {
  MyDataStructure myDs;

  @BeforeEach
  void setup() {
    myDs = new MyDataStructure();
  }

  @AfterEach
  void teardown() {
    myDs.clear();
  }

  @Test
  void addGetManyTest() {
    for (int i = 0; i < 100; i++) {
      myDs.push(i, String.format("%d", i));
    }
    for (int i = 0; i < 100; i++) {
      assertEquals(String.format("%d", i), myDs.get(i));
    }
  }
}
