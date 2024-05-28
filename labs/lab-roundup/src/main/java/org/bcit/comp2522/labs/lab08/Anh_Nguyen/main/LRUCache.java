package org.bcit.comp2522.labs.lab08;

import java.util.*;
import java.util.HashMap;
import org.bcit.comp2522.labs.lab08.Entry;

/**
 * Least Recently Used Cache is to provide fast and efficient way of retrieving data.
 * If the data is saved on cache, it will return the value with fast time O(1) time complexity.
 *
 * @author Ryan Lee + Anh Nguyen and referenced from leetcode discussion
 * @version 1 Nove 5 2022
 *
 * @param <K>  key
 *
 * @param <V>  value
 *
 */
public class LRUCache<K, V> {
  HashMap<K, Entry> hashmap;
  Entry start;
  Entry end;
  int LRU_SIZE = 100;

  public LRUCache() {
    hashmap = new HashMap<K, Entry>();
  }

  /**
   * Function that accept the key to check if it exists
   * on hashmap and return value.
   *
   * @param key key
   *
   * @param <V> value
   *
   * @return value
   */
  public <V> V getEntry(K key) {
    // Key Already Exist, just update the
    if (hashmap.containsKey(key)) {
      Entry entry = hashmap.get(key);
      removeNode(entry);
      addAtTop(entry);
      return (V) entry.value;
    }
    return null;
  }

  /**
   *  Function that put the value to cache.
   *
   * @param key key
   *
   * @param value value
   *
   * @param <V> value
   *
   */
  public <V> void putEntry(K key, V value) {
    // Key Already Exist, just update the value and move it to top
    if (hashmap.containsKey(key)) {
      Entry entry = hashmap.get(key);
      entry.value = value;
      removeNode(entry);
      addAtTop(entry);
    } else {
      Entry newnode = new Entry();
      newnode.left = null;
      newnode.right = null;
      newnode.value = value;
      newnode.key = key;
      // We have reached maxium size so need to make room for new element.
      if (hashmap.size() > LRU_SIZE) {
        hashmap.remove(end.key);
        removeNode(end);
        addAtTop(newnode);
      } else {
        addAtTop(newnode);
      }
      hashmap.put(key, newnode);
    }
  }

  /**
   * Function that add the node to the top of cache.
   *
   * @param node node
   *
   */
  public void addAtTop(Entry node) {
    node.right = start;
    node.left = null;
    if (start != null) start.left = node;
    start = node;
    if (end == null) {
      end = start;
    }
  }

  /**
   * Function that remove node when it over 1000 elements.
   *
   * @param node node
   *
   */
  public void removeNode(Entry node) {
    if (node.left != null) {
      node.left.right = node.right;
    } else {
      start = node.right;
    }
    if (node.right != null) {
      node.right.left = node.left;
    } else {
      end = node.left;
    }
  }
}

