package fr.univtln.bruno.samples.java101.tp3.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Stream-based deduplication examples: utility class with static methods.
 */
@Slf4j
public final class DedupExamples {
  // Prevent instantiation

  /**
   * Private constructor for utility class.
   */
  private DedupExamples() {
  }

  /**
   * Show deduplication using stream distinct(). This is the preferred functional style when you already have a stream-based pipeline.
   *
   * @param withDup list containing duplicated elements
   */
  public static void dedupWithStreamDistinct(List<String> withDup) {
    log.info("[functional] Unique (Stream.distinct): {}", withDup.stream().distinct().toList());
  }

  /**
   * Show collecting into a LinkedHashSet via stream then LinkedHashSet constructor.
   *
   * @param withDup list containing duplicated elements
   */
  public static void collectToLinkedHashSet(List<String> withDup) {
    var ordered = new LinkedHashSet<>(withDup.stream().distinct().toList());
    log.info("[functional] Collected LinkedHashSet (from stream): {}", ordered);
  }
}
