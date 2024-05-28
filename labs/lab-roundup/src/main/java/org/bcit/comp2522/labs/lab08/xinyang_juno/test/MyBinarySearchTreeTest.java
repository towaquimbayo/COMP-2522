package org.bcit.comp2522.labs.lab08.xinyang_juno.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyBinarySearchTreeTest {
  MyBinarySearchTree mbst;

  @BeforeEach
  void setup() {
    mbst = new MyBinarySearchTree<String, Integer>();
  }

  @AfterEach
  void teardown() {
    mbst.clear();
  }

  @Test
  void addGetTest() {
    mbst.push("1", 1);
    assertEquals(1, mbst.get("1"));
  }

  @Test
  void addGetManyTest() {
    for (int i = 0; i < 100; i++) {
      mbst.push(String.format("%d", i), 1);
    }
    for (int i = 0; i < 100; i++) {
      assertEquals(1, mbst.get(String.format("%d", i)));
    }
  }
}