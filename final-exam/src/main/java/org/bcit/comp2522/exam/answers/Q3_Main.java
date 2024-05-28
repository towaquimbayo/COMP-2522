package org.bcit.comp2522.exam.answers;

import java.util.concurrent.Semaphore;

public class Q3_Main {

  public static void main(String[] args) {
    Q3_MashMapThread.Q3_Thread thread1 = new Q3_MashMapThread.Q3_Thread("Test 1");
    Q3_MashMapThread.Q3_Thread thread2 = new Q3_MashMapThread.Q3_Thread("Test 2");
    Q3_MashMapThread.Q3_Thread thread3 = new Q3_MashMapThread.Q3_Thread("Test 3");

    // Test Run
    thread1.start();
    thread2.start();
    thread3.start();
  }
}
