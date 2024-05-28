package org.bcit.midterm.answers;

import org.bcit.midterm.questions.Advanced_03;

import java.util.ArrayList;
import java.util.Collections;

public class Advanced_03_Towa implements Comparable {
  // Advanced Question 03 (15 estimated points, 2 TODOs)
  // Idea: tests your ability to create and use Comparables.

  // README FIRST
  // I will ONLY be reading from the org.bcit.midterm.answers package.
  // TODO 0: Create a copy of this file in the answers package.
  // Put your answers in new classes that are labeled with the
  // question label, e.g., Advanced_03_myClass.
  public int size;
  public Advanced_03_Towa(int size) {
    this.size = size;
  }

  // TODO 1: Implement the Comparable interface by completing
  // compareTo and any other helper functions you need.
  /**
   * Compares this object with the specified object for order.  Returns a
   * negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object.
   *
   * <p>The implementor must ensure {@link Integer#signum
   * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
   * all {@code x} and {@code y}.  (This implies that {@code
   * x.compareTo(y)} must throw an exception if and only if {@code
   * y.compareTo(x)} throws an exception.)
   *
   * <p>The implementor must also ensure that the relation is transitive:
   * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
   * {@code x.compareTo(z) > 0}.
   *
   * <p>Finally, the implementor must ensure that {@code
   * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
   * == signum(y.compareTo(z))}, for all {@code z}.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object
   * is less than, equal to, or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it
   *                              from being compared to this object.
   * @apiNote It is strongly recommended, but <i>not</i> strictly required that
   * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
   * class that implements the {@code Comparable} interface and violates
   * this condition should clearly indicate this fact.  The recommended
   * language is "Note: this class has a natural ordering that is
   * inconsistent with equals."
   */
  @Override
  public int compareTo(Object o) {
    if (o instanceof Advanced_03_Towa) {
      if (((Advanced_03_Towa) o).size > this.size) {
        return -1;
      } else if (((Advanced_03_Towa) o).size < this.size) {
        return 1;
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    Advanced_03_Towa one = new Advanced_03_Towa(1);
    Advanced_03_Towa two = new Advanced_03_Towa(2);
    Advanced_03_Towa three = new Advanced_03_Towa(3);
    ArrayList<Advanced_03_Towa> list = new ArrayList<Advanced_03_Towa>();
    list.add(three);
    list.add(two);
    list.add(one);
    // Should be in order one, two, three after the sort
    Collections.sort(list);

    // Uncomment this for test
//    for (Advanced_03_Towa towa : list) {
//      System.out.println(towa.size);
//    }
  }
}
