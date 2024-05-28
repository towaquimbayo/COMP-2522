package org.bcit.comp2522.labs.lab08.Gabriel_Sarvenaz;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyRecencyDictTest {
  MyRecencyDict mrd;
  BinaryHeap mine;

  @BeforeEach
  void setup() {
    mrd = new MyRecencyDict();
  }

  @BeforeEach
  void setupHeap() {
    mine = new BinaryHeap();
  }

  @AfterEach
  void teardown() {
    mrd.clear();
  }

  @AfterEach
  void tearDownHeap() { mine.clear();}

  @Test
  void addGetTest() {
    mrd.push(1, "1");
    assertEquals("1", mrd.get(1));
  }

  @Test
  void addGetTest2() {
    mine.push(1, "1");
    assertEquals("1", mine.heapGet(1));
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


  @Test
  void addGetManyTest2() {
    for (int i = 0; i < 99; i++) {
      mine.push(i, String.format("%d", i));
    }
    for (int i = 0; i < 99; i++) {
      assertEquals(String.format("%d", i), mine.heapGet(i));
    }
  }


}