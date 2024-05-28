package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

/**
 * This power class for enemy.
 * Measured and start with 1 int.
 */
public class EnemyPower extends AbstractPower {

 private int count = 1;

 @Override
 public int getCount() {
  return this.count;
 }

 @Override
 public void incrementCount(int add) {this.count = this.count + add; }

 @Override
 public int compareTo(AbstractPower o) {
  if (this.count > o.getCount()) {
   return 1;
  } else if ( this.count < o.getCount()) {
   return -1;
  } else {
   return 0;
  }
 }
}
