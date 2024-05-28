package org.bcit.comp2522.labs.lab08;

/**
 * Entry Class.
 *
 * @author Ryan Lee + Anh Nguyen *
 * @version 1 Nov 5 2022
 *
 * @param <K> K
 *
 * @param <V> V
 *
 */

class Entry<K, V> {
  V value;
  K key;
  Entry left;
  Entry right;
}

