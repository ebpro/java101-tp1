package fr.univtln.bruno.samples.java101.tp3.eclipse;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

/**
 * Minimal example showing usage of Eclipse Collections primitives (IntList).
 *
 * Student notes:
 * - Eclipse Collections provides primitive collections (IntList, LongList...) to avoid boxing overhead.
 * - Useful when processing large numeric datasets where boxing/unboxing is costly.
 * - Conversions to/from standard Java collections are possible; prefer primitives when performance matters.
 */
@Slf4j
public class EclipseCollectionsExamples {
  /**
   * Private constructor to prevent instantiation.
   */
  private EclipseCollectionsExamples() {
  }

  /**
   * Simple demo for Eclipse Collections primitives.
   *
   * @param args ignored
   */
  public static void main(String[] args) {
    IntList list = IntArrayList.newListWith(1, 2, 3, 4, 5);
    long sum = list.sum();
    log.info("sum={}", sum);

    // Student note (commented): convert to standard list if needed (boxes values):
    // List<Integer> boxed = list.collect(i -> Integer.valueOf(i)).toList();
  }
}
