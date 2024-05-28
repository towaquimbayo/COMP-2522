package org.bcit.comp2522.exam.answers;

public class Q1_Main {
  public static void main(String[] args) {
    Q1_CostlyLinkedList<String, Integer> cllist = new Q1_CostlyLinkedList<String, Integer>(100);
    cllist.push("1", 1, 1); // budget = 100 - 1 = 99
    cllist.push("2", 2, 2); // budget = 99 - 1 - 2 = 96  : why? because push would have called next once
    cllist.push("3", 3, 2); // budget = 96 - 1 - 2 - 3 = 90 : why? because push would have called next twice
    cllist.getBudget(); // 90
    cllist.get("2"); // budget = 90 - 1 - 2 = 87
   // cllist.forEach((n) -> System.out.println(n.value)); // budget = 87 - 1 - 2 - 3 = 81
  }
}
