package org.bcit.comp2522.exam.answers;

import java.util.concurrent.Semaphore;

public class Q3_MashMapThread {
  private static Semaphore mutex = new Semaphore(1);

  static class Q3_Thread extends Thread {
    public static Q3_MashMap<String, Integer> mashMap = new Q3_MashMap<>();
    private String name = "";
    public Q3_Thread(String n) {
      name = n;
    }

    @Override
    public void run() {
      try {
        mutex.acquire();
        try {
          Thread.sleep(500);
      //    System.out.println("Available Permits: " + mutex.availablePermits());
        } catch (Exception e) {
          System.out.println(e.getMessage());
        } finally {
          mutex.release(); // release for other threads to perform
          for (int i = 0; i < 100; i++) {
            mashMap.push(String.valueOf(i), i);
          }

          for (int i = 0; i < 100; i++) {
              System.out.println(mashMap.get(String.valueOf(i)));
          }
        //  System.out.println("Available Permits: " + mutex.availablePermits());
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
