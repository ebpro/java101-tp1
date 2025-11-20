package fr.univtln.bruno.samples.java101.tp3.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Examples demonstrating parallel streams usage and common pitfalls.
 *
 * <p>Shows performance measurement and the dangers of shared mutable state when using
 * parallel streams; recommends reduction operations as the correct pattern.</p>
 */
@Slf4j
public class ParallelStreamsExamples {
  /**
   * Compare sequential vs parallel stream performance for a simple summation.
   * This is illustrative only — real benchmarking requires JMH and controlling for warmup.
   */
  public static void parallelSumExample() {
    log.info("=== Parallel sum example (measure) ===");
    List<Integer> data = IntStream.rangeClosed(1, 1_000_000).boxed().toList();

    long t0 = System.currentTimeMillis();
    long sumSeq = data.stream().mapToLong(Integer::longValue).sum();
    long t1 = System.currentTimeMillis();

    log.debug("Sequential sum took {} ms", (t1 - t0));

    long sumParStart = System.currentTimeMillis();
    long sumPar = data.parallelStream().mapToLong(Integer::longValue).sum();
    long sumParEnd = System.currentTimeMillis();

    log.info("seq sum={} time={}ms", sumSeq, (t1 - t0));
    log.info("par sum={} time={}ms", sumPar, (sumParEnd - sumParStart));
    log.debug("Parallel sum took {} ms", (sumParEnd - sumParStart));
  }

  /**
   * Demonstrates the pitfall of mutating shared state (AtomicInteger used here still risks
   * producing confusing results and should be avoided). Shows the correct pattern using reduction.
   */
  public static void sharedMutablePitfall() {
    log.info("=== Shared mutable state pitfall ===");
    List<Integer> data = IntStream.rangeClosed(1, 1000).boxed().toList();

    AtomicInteger counter = new AtomicInteger(0);
    // WRONG: side-effect in parallel stream leads to race / unpredictable result
    data.parallelStream().forEach(counter::addAndGet);
    log.info("Counter (with parallel forEach) = {} (should be {})", counter.get(), data.stream().mapToInt(Integer::intValue).sum());

    int reduced = data.parallelStream().mapToInt(Integer::intValue).reduce(0, Integer::sum);
    log.info("Reduced (parallel) = {}", reduced);

    // Student note: prefer stateless reduction or collectors. For example, using a thread-safe collector:
    // int sum = data.parallelStream().mapToInt(Integer::intValue).sum(); // built-in is safe
    // Or: data.parallelStream().collect(Collectors.summingInt(Integer::intValue));
  }

  /**
   * Runner for parallel streams examples.
   *
   * @param args ignored
   */
  public static void main(String[] args) {
    parallelSumExample();
    sharedMutablePitfall();
  }
}
