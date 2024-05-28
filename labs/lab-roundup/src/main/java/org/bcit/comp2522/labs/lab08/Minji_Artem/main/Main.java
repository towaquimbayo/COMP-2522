package org.bcit.comp2522.labs.lab08.Minji_Artem.main;

import java.util.HashMap;

/**
 * Contains main method.
 *
 * @author Paul
 * @author Artem Khan and Wendy
 * @version 1.0
 */
public class Main {

  /**
   * Runs the program.
   *
   * @param args is the passed arguments
   *
   */
  public static void main(String[] args) {
    MyRecencyDict<Integer, String> rd = new MyRecencyDict<Integer, String>();
    MyBinarySearchTree<Integer, String> bst = new MyBinarySearchTree<Integer, String>();
    MyDataStructure<Integer, String> ds = new MyDataStructure<Integer, String>();
    HashMap<Integer, String> hmap = new HashMap<Integer, String>();

    int maxN = 100001;
    String uname = "artem-and-wendy";
    for (int n = 99000; n < maxN; n += 1000) {
      System.out.format("# Starting round for n=%d\n", n);
      // Hashmap
      long start = System.nanoTime();
      for (int i = 0; i < n; i++) {
        hmap.put(i, String.format("%d", i));
      }
      long elapsedTime = System.nanoTime() - start;
      System.out.format("Time elapsed for HashMap put: %d at n=%d.\n", elapsedTime, n);

      start = System.nanoTime();
      for (int i = 0; i < n; i++) {
        hmap.get(i);
      }
      elapsedTime = System.nanoTime() - start;
      System.out.format("Time elapsed for HashMap get: %d at n=%d.\n", elapsedTime, n);

      // BST
      start = System.nanoTime();
      for (int i = 0; i < n; i++) {
        bst.push(i, String.format("%d", i));
      }
      elapsedTime = System.nanoTime() - start;
      System.out.format("Time elapsed for BST put: %d at n=%d.\n", elapsedTime, n);

      start = System.nanoTime();
      for (int i = 0; i < n; i++) {
        bst.get(i);
      }
      bst.clear();
      elapsedTime = System.nanoTime() - start;
      System.out.format("Time elapsed for BST get: %d at n=%d.\n", elapsedTime, n);

      // RecencyDict
      start = System.nanoTime();
      for (int i = 0; i < n; i++) {
        rd.push(i, String.format("%d", i));
      }
      elapsedTime = System.nanoTime() - start;
      System.out.format("Time elapsed for RecencyDict put: %d at n=%d.\n", elapsedTime, n);

      start = System.nanoTime();
      for (int i = 0; i < n; i++) {
        rd.get(i);
      }
      elapsedTime = System.nanoTime() - start;
      System.out.format("Time elapsed for RecencyDict get: %d at n=%d.\n", elapsedTime, n);;

      start = System.nanoTime();
      for (int i = 0; i < n; i++) {
        rd.get(i % 10);
      }
      rd.clear();
      elapsedTime = System.nanoTime() - start;
      System.out.format("Time elapsed for RecencyDict get recent: %d at n=%d.\n", elapsedTime, n);;

      // MyDataStructure
      start = System.nanoTime();
      for (int i = 0; i < n; i++) {
        ds.push(i, String.format("%d", i));
      }
      elapsedTime = System.nanoTime() - start;
      System.out.format("Time elapsed for MyDataStructure put: %d at n=%d.\n", elapsedTime, n);

      start = System.nanoTime();
      for (int i = 0; i < n; i++) {
        ds.get(i);
      }
      elapsedTime = System.nanoTime() - start;
      System.out.format("Time elapsed for %s "
              + "MyDataStructure get: %d at n=%d.\n", uname, elapsedTime, n);

      start = System.nanoTime();
      for (int i = 0; i < n; i++) {
        ds.get(i % 10);
      }
      ds.clear();
      elapsedTime = System.nanoTime() - start;
      System.out.format("Time elapsed for "
              + "MyDataStructure get recent: %d at n=%d.\n", elapsedTime, n);;

    }
  }
}
