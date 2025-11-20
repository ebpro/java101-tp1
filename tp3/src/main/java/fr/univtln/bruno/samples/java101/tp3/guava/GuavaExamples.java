package fr.univtln.bruno.samples.java101.tp3.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;

/**
 * Small Guava Multimap example used for demonstration in TP3.
 * A multimap allows multiple values per key.
 *
 * Student notes:
 * - Guava Multimap is convenient when you need multiple values per key without managing lists yourself.
 * - Compared to Map<K, List<V>> it provides cleaner semantics and utility methods.
 * - If Guava isn't available, consider using Map<K, List<V>> with computeIfAbsent.
 */
@Slf4j
public class GuavaExamples {
  /**
   * Private constructor to prevent instantiation.
   */
  private GuavaExamples() {
  }

  /**
   * Small Guava Multimap example used for demonstration in TP3.
   *
   * @param args ignored
   */
  public static void main(String[] args) {
    Multimap<String, String> mm = ArrayListMultimap.create();
    mm.put("key1", "v1");
    mm.put("key1", "v2");
    mm.put("key2", "v3");

    for (String k : mm.keySet()) {
      log.info("{} -> {}", k, mm.get(k));
    }

    // Student note (commented): equivalent using Map<K, List<V> > with computeIfAbsent
    // Map<String, List<String>> map = new HashMap<>();
    // map.computeIfAbsent("key1", k -> new ArrayList<>()).add("v1");
    // map.computeIfAbsent("key1", k -> new ArrayList<>()).add("v2");
  }
}
