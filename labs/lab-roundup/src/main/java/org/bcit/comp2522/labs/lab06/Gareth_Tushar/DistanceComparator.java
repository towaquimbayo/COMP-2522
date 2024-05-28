package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

import java.util.Comparator;

/**
 * This is a utility class that helps sort the distance
 * between two enemy character.
 */
public class DistanceComparator implements Comparator<EnemyCharacter> {
 @Override
 public int compare(EnemyCharacter o1, EnemyCharacter o2) {
  if(o1.getDistance() > o2.getDistance()) {
   return 1;
  } else if(o1.getDistance() < o2.getDistance()) {
   return -1;
  } else {
   return 0;
  }
 }
}
