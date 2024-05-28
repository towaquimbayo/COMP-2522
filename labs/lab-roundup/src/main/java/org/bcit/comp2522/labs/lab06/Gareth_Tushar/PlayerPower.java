package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

public class PlayerPower extends AbstractPower {
  private int count = 30;
  @Override
  public int getCount() {
  return this.count;
 }

  @Override
  public void incrementCount(int add) {
  this.count = this.count + add;
 }

  public void setCount(int count) {
  this.count = count;
 }

  @Override
  public int compareTo(AbstractPower o) {
  return 0;
 }
}
