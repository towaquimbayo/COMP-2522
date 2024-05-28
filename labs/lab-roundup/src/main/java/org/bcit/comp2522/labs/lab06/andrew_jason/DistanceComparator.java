package org.bcit.comp2522.labs.lab06.andrew_jason;

import org.bcit.comp2522.labs.lab06.Enemy;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Object> {
  @Override
  public int compare(Object o1, Object o2) {
    org.bcit.comp2522.labs.lab06.Enemy e1 = (org.bcit.comp2522.labs.lab06.Enemy) o1;
    org.bcit.comp2522.labs.lab06.Enemy e2 = (Enemy) o2;
    if (e1.getDistance() > e2.getDistance()) {
      return 1;
    } else if (e1.getDistance() < e2.getDistance()) {
      return -1;
    } else {
      return 0;
    }
  }
}
