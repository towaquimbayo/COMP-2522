package org.bcit.midterm.answers;

public class Advanced_01_Main {
  public static void main(String[] args) {
    Advanced_01_Towa<String> av01 = new Advanced_01_Towa<String>();
    av01.add("Hello", "Towa");
    av01.add("Good Morning", "Paul");
    av01.add("Good Night", "Josh");
    for (String s : av01) {
      System.out.println(s);
    }
  }
}
