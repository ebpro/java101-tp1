package fr.univtln.bruno.samples.java101.tp3.set;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Collection-based deduplication examples: show how to remove duplicates while preserving insertion order
 * using LinkedHashSet and why this is a collections solution (not a stream transform idiom).
 *
 * Student notes:
 * - Deduplication with HashSet/LinkedHashSet relies on correct equals()/hashCode() implementations.
 *   If equals/hashCode are inconsistent, deduplication will behave incorrectly.
 * - HashCode collisions are possible; they degrade performance but do not by themselves break correctness
 *   because equals() is used to disambiguate entries.
 * - If you need identity-based semantics (object identity rather than equals), use IdentityHashMap or
 *   another identity-based structure — but this is seldom desired for domain objects.
 */
@Slf4j
public class DedupCollections {

  /**
   * Private constructor to prevent instantiation of this utility/example class.
   */
  private DedupCollections() {
  }

  /**
   * Remove duplicates while preserving order using a LinkedHashSet (collection-based approach).
   * This method is intentionally placed in the `set` package because it uses a Set implementation.
   *
   * @param withDup list containing duplicate elements; may be null (handled by caller)
   */
  public static void removeDuplicatesPreserveOrder(List<String> withDup) {
    List<String> uniqueOrder = new ArrayList<>(new LinkedHashSet<>(withDup));
    log.info("[collections] Unique (LinkedHashSet): {}", uniqueOrder);
  }

  /**
   * Show LinkedHashSet insertion order behavior.
   *
   * @param values sample values to demonstrate insertion order
   */
  public static void linkedHashSetOrder(List<String> values) {
    var ordered = new LinkedHashSet<>(values);
    log.info("[collections] LinkedHashSet order: {}", ordered);
  }

  // Student example (commented): identity vs equality
  // IdentityHashMap uses reference equality (==) instead of equals(). This can surprise students
  // who expect equals-based lookup. Uncomment and run separately to experiment.
  //
  // import java.util.IdentityHashMap;
  // IdentityHashMap<Object, String> im = new IdentityHashMap<>();
  // im.put(new String("a"), "first");
  // // different String instance with same content is not considered equal by identity map
  // log.info("Identity lookup returns null: {}", im.get(new String("a")));
}
