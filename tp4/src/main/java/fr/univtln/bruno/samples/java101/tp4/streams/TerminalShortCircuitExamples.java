package fr.univtln.bruno.samples.java101.tp4.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Demonstrates short-circuiting and terminal stream operations.
 */
@Slf4j
public class TerminalShortCircuitExamples {
  private TerminalShortCircuitExamples() {}

  public static void terminalDemo() {
    log.info("=== Terminal / Short-circuit Examples ===");

    List<Integer> nums = IntStream.rangeClosed(1, 100).boxed().toList();

    boolean anyEven = nums.stream().anyMatch(n -> n % 2 == 0);
    boolean allPositive = nums.stream().allMatch(n -> n > 0);
    boolean noneNegative = nums.stream().noneMatch(n -> n < 0);

    log.info("anyEven={}, allPositive={}, noneNegative={}", anyEven, allPositive, noneNegative);

    Optional<Integer> firstOver50 = nums.stream().filter(n -> n > 50).findFirst();
    Optional<Integer> anyOver50Parallel = nums.parallelStream().filter(n -> n > 50).findAny();

    log.info("firstOver50={}, anyOver50Parallel={}", firstOver50, anyOver50Parallel);

    // forEachOrdered demonstration with parallel stream (prints ordered)
    nums.parallelStream()
        .map(Object::toString)
        .forEachOrdered(s -> log.debug("val: {}", s));
  }
}

