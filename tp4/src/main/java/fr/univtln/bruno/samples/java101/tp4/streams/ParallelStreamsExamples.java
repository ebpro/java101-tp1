package fr.univtln.bruno.samples.java101.tp4.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Illustrates common parallel stream pitfalls and correct patterns.
 */
@Slf4j
public class ParallelStreamsExamples {
  private ParallelStreamsExamples() {}

  public static void parallelPitfallDemo() {
    log.info("=== Parallel Streams Examples ===");

    List<Integer> nums = IntStream.range(0, 1000).boxed().toList();

    // BAD: mutating a shared, non-thread-safe collection from parallel stream
    List<Integer> bad = new ArrayList<>();
    nums.parallelStream().forEach(bad::add);
    log.info("Bad size (likely < 1000): {}", bad.size());

    // GOOD: use collect which is thread-safe for parallel streams
    List<Integer> good = nums.parallelStream().collect(Collectors.toList());
    log.info("Good size: {}", good.size());

    // Concurrent collector / partitioning example
    Map<Boolean, Long> counts = nums.parallelStream()
        .collect(Collectors.partitioningBy(n -> n % 2 == 0, Collectors.counting()));
    log.info("Counts parity: {}", counts);

    // Summary statistics
    IntSummaryStatistics stats = nums.parallelStream().mapToInt(Integer::intValue).summaryStatistics();
    log.info("Stats: {}", stats);
  }
}

